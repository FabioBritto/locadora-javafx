package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.CategoriaVeiculo;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import com.fatec.atendimentolocalhost.model.entities.Veiculo;
import com.fatec.atendimentolocalhost.model.enums.SituacaoVeiculo;
import com.fatec.atendimentolocalhost.service.CategoriaVeiculoService;
import com.fatec.atendimentolocalhost.service.TipoSeguroService;
import com.fatec.atendimentolocalhost.service.VeiculoService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class NovaLocacaoController implements Initializable {

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private Button btnAvancar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<CategoriaVeiculo> cmbCategoria;

    @FXML
    private DatePicker datePickerDevolucao;

    @FXML
    private RadioButton rbCategoria;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCmbCategoria();
        inicializarCmbSeguros();
        escolherVeiculo();
        preencherTabela();
        cmbSeguro.valueProperty().addListener((e) -> {
            carregarCamposSeguro();
        });
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

        veiculoService = new VeiculoService();

        try {
            List<Veiculo> veiculos = veiculoService.buscarVeiculos().stream().filter(veiculo -> veiculo.getSitucao().equals(SituacaoVeiculo.DISPONÍVEL)).collect(Collectors.toList());           
            ObservableList<Veiculo> obsVeiculos = FXCollections.observableArrayList(veiculos);
            tabelaVeiculos.setItems(obsVeiculos);
        } catch (DBException e) {
            System.out.println("OPA:" + e.getMessage());
        }
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
        txtTaxa.setText(String.valueOf(seguroEscolhido.getTaxa()));
        txtDescricaoSeguro.setText(seguroEscolhido.getDescricao());
    }

    private void limparCamposSeguro() {
        txtTaxa.setText("");
        txtDescricaoSeguro.setText("");
    }

    @FXML
    private void btnAvancarClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pagamento.fxml"));
        VBox v = loader.load();

        BorderPane borderPane = (BorderPane) vBoxPrincipal.getParent();
        borderPane.setCenter(v);
    }

    @FXML
    public void escolherVeiculo() {
        tabelaVeiculos.setRowFactory(t -> {
            TableRow<Veiculo> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    veiculoEscolhido = row.getItem();

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
