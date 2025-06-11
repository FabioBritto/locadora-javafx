package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.CategoriaVeiculo;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import com.fatec.atendimentolocalhost.model.entities.Veiculo;
import com.fatec.atendimentolocalhost.model.enums.SituacaoVeiculo;
import com.fatec.atendimentolocalhost.service.CategoriaVeiculoService;
import com.fatec.atendimentolocalhost.service.TipoSeguroService;
import com.fatec.atendimentolocalhost.service.VeiculoService;
import com.fatec.atendimentolocalhost.util.PedidoHolder;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class NovaLocacaoController implements Initializable {

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private Button btnAvancar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnLimparBusca;

    @FXML
    private ComboBox<CategoriaVeiculo> cmbCategoria;

    @FXML
    private DatePicker datePickerDevolucao;

    @FXML
    private RadioButton rbModelo;

    @FXML
    private RadioButton rbFabricante;

    @FXML
    private RadioButton rbPrecoBase;

    @FXML
    private TableView<Veiculo> tabelaVeiculos;

    @FXML
    private TableColumn<Veiculo, String> colunaPlaca;

    @FXML
    private TableColumn<Veiculo, String> colunaMarca;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, String> colunaCor;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAno;

    @FXML
    private TableColumn<Veiculo, Integer> colunaKm;

    @FXML
    private TableColumn<Veiculo, BigDecimal> colunaPrecoBase;

    @FXML
    private TextField txtDataDevolucao;

    @FXML
    private TextField txtPesquisaModelo;

    @FXML
    private ComboBox<TipoSeguro> cmbSeguro;

    @FXML
    private TextField txtTaxa;

    @FXML
    private TextArea txtDescricaoSeguro;

    //Services
    private TipoSeguroService seguroService;
    private VeiculoService veiculoService;
    private CategoriaVeiculoService catService;

    private Veiculo veiculoEscolhido;
    private TipoSeguro seguroEscolhido;

    private FilteredList<Veiculo> listaFiltrada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbModelo.setSelected(true);
        inicializarCmbCategoria();
        inicializarCmbSeguros();
        escolherVeiculo();
        preencherTabela();
        cmbSeguro.valueProperty().addListener((e) -> {
            carregarCamposSeguro();
        });
        aplicarFiltroNaListaDeVeiculos(listaFiltrada);
        configurarListenersDeBusca();

        btnLimparBusca.setOnAction(e -> {
            limparCamposDeBusca();
        });

        configurarDatePicker();
    }

    @FXML
    private void preencherTabela() {

        colunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colunaKm.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
        colunaPrecoBase.setCellValueFactory(new PropertyValueFactory<>("precoBase"));

        colunaPrecoBase.setCellFactory(coluna -> new TableCell<>() {
            private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatoMoeda.format(item));
                }
            }
        });

        veiculoService = new VeiculoService();

        try {
            List<Veiculo> veiculos = veiculoService.buscarVeiculos().stream().filter(veiculo -> veiculo.getSitucao().equals(SituacaoVeiculo.DISPONÍVEL)).collect(Collectors.toList());
            ObservableList<Veiculo> obsVeiculos = FXCollections.observableArrayList(veiculos);
            listaFiltrada = new FilteredList<>(obsVeiculos, v -> true);
            tabelaVeiculos.setItems(obsVeiculos);
        } catch (DBException e) {
            System.out.println("OPA:" + e.getMessage());
        }
    }

    //Configurações de busca
    /**
     * Adiciona Listeners a todos os elementos relacionados a busca de veículos.
     *
     * @author Christian
     */
    public void configurarListenersDeBusca() {
        tabelaVeiculos.setItems(listaFiltrada);
        txtPesquisaModelo.textProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        cmbCategoria.valueProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbFabricante.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbPrecoBase.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbModelo.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

    }

    /**
     * Filtra a lista de veículos to vez que é chamado.
     *
     * @author Christian
     * @param listaFiltrada
     */
    public void aplicarFiltroNaListaDeVeiculos(FilteredList<Veiculo> listaFiltrada) {
        listaFiltrada.setPredicate(veiculo -> {

            String textoBusca = txtPesquisaModelo.getText().toLowerCase();
            CategoriaVeiculo categoria = cmbCategoria.getValue();

            Boolean textoCorresponde = true;
            if (rbModelo.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getModelo().toLowerCase().contains(textoBusca);
            } else if (rbFabricante.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getMarca().toLowerCase().contains(textoBusca);
            } else if (rbPrecoBase.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getPrecoBase().toString().toLowerCase().contains(textoBusca);
            }

            Boolean categoriaCorresponde = true;
            if (categoria != null) {
                categoriaCorresponde = veiculo.getCategoria().equals(categoria);
            }

            return textoCorresponde && categoriaCorresponde;
        });
    }

    public void limparCamposDeBusca() {
        txtPesquisaModelo.setText("");
        rbModelo.setSelected(true);
        cmbCategoria.setValue(null);
    }

    //Fim das configurações de busca
    public void configurarDatePicker() {
        datePickerDevolucao.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(javafx.scene.control.DatePicker picker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #dddddd;");
                        }
                    }
                };
            }
        });

        datePickerDevolucao.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PedidoHolder.getInstance().getPedido().setDevolucaoEsperada(newValue);
            }
        });
    }

    @FXML
    private void inicializarCmbCategoria() {
        catService = new CategoriaVeiculoService();
        try {
            List<CategoriaVeiculo> categorias = catService.buscarCategorias();
            ObservableList<CategoriaVeiculo> obsCategorias = FXCollections.observableArrayList(categorias);
            cmbCategoria.setItems(obsCategorias);
        } catch (DBException e) {
            System.out.println("opa: " + e.getMessage());
        }
    }

    @FXML
    private void inicializarCmbSeguros() {
        seguroService = new TipoSeguroService();
        try {
            List<TipoSeguro> seguros = seguroService.buscarSeguros();
            ObservableList<TipoSeguro> obsSeguros = FXCollections.observableArrayList(seguros);
            cmbSeguro.setItems(obsSeguros);
        } catch (DBException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void carregarCamposSeguro() {
        limparCamposSeguro();
        TipoSeguro seguroEscolhido = cmbSeguro.getValue();
        PedidoHolder.getInstance().getPedido().setTipoSeguro(seguroEscolhido);
        NumberFormat moedaBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        txtTaxa.setText(moedaBR.format(seguroEscolhido.getTaxa()));
        txtDescricaoSeguro.setText(seguroEscolhido.getDescricao());
    }

    private void limparCamposSeguro() {
        txtTaxa.setText("");
        txtDescricaoSeguro.setText("");
    }

    @FXML
    private void btnAvancarClick() throws IOException {
        if (PedidoHolder.getInstance().getPedido().getDevolucaoEsperada() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Data de devolução não escolhida!");
            alert.setHeaderText("Escolha uma data!");
            alert.showAndWait();
        } else if (PedidoHolder.getInstance().getPedido().getVeiculo() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Veículo não escolhido!");
            alert.setHeaderText("Escolha um Veículo!");
            alert.showAndWait();
        } else if (PedidoHolder.getInstance().getPedido().getTipoSeguro() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Seguro não escolhido!");
            alert.setHeaderText("Escolha um seguro!");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pagamento.fxml"));
            VBox v = loader.load();
            BorderPane borderPane = (BorderPane) vBoxPrincipal.getParent();
            borderPane.setCenter(v);
        }

    }

    @FXML
    public void escolherVeiculo() {
        tabelaVeiculos.setRowFactory(t -> {
            TableRow<Veiculo> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    veiculoEscolhido = row.getItem();
                    PedidoHolder.getInstance().getPedido().setVeiculo(veiculoEscolhido);
                    // Atualiza a tabela para redesenhar os estilos
                    tabelaVeiculos.refresh();
                }
            });

            // Esse trecho é executado para cada linha, inclusive ao "refresh"
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                // Estilo condicional no momento da criação da linha ou do refresh
                row.styleProperty().unbind(); // se tiver binding anterior
                row.setStyle(""); // reseta o estilo

                if (newItem != null && newItem.equals(veiculoEscolhido)) {
                    row.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white;"); // verde escuro + texto branco
                }
            });

            return row;
        });
    }
}
