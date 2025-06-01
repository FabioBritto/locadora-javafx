package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.CategoriaVeiculo;
import com.fatec.atendimentolocalhost.model.entities.Veiculo;
import com.fatec.atendimentolocalhost.service.CategoriaVeiculoService;
import com.fatec.atendimentolocalhost.service.UsuarioService;
import com.fatec.atendimentolocalhost.service.VeiculoService;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class NovaLocacaoController implements Initializable {

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

    VeiculoService service;
    CategoriaVeiculoService catService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCmbCategoria();
        preencherTabela();
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
        
        service = new VeiculoService();
        
        try{
            List<Veiculo> veiculos = service.buscarVeiculos();
            ObservableList<Veiculo> obsVeiculos = FXCollections.observableArrayList(veiculos);
            tabelaVeiculos.setItems(obsVeiculos);
        }
        catch(DBException e){
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
        }
        catch(DBException e){
            System.out.println("opa: " + e.getMessage());
        }

    }

}
