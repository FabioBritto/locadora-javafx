package com.fatec.atendimentolocalhost;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NovaLocacaoController implements Initializable {

    @FXML
    private Button btnAvancar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<?> cmbCategoria;

    @FXML
    private DatePicker datePickerDevolucao;

    @FXML
    private RadioButton rbCategoria;

    @FXML
    private RadioButton rbFabricante;

    @FXML
    private RadioButton rbPrecoBase;

    @FXML
    private TableView<?> tabelaVeiculos;

    @FXML
    private TextField txtDataDevolucao;

    @FXML
    private TextField txtPesquisaModelo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    
}