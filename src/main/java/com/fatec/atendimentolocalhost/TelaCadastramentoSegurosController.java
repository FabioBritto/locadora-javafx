/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alber
 */
public class TelaCadastramentoSegurosController implements Initializable {

    @FXML
    private TableView<?> tabelaSeguros;
    @FXML
    private TextField txtNome;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private TextField txtTaxa;
    @FXML
    private Button btnAdicionarSeguro;
    @FXML
    private Button btnLimparCampos;
    
    // Métodos privados da tela
    private void limparCampos(){
        txtNome.setText("");
        txtDescricao.setText("");
        txtTaxa.setText("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAdicionarSeguroClick(ActionEvent event) {
    }

    @FXML
    private void btnLimparCamposClick(ActionEvent event) {
        limparCampos();
    }
    
}
