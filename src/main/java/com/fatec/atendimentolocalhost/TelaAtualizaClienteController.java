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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alber
 */
public class TelaAtualizaClienteController implements Initializable {

    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtDataNascimento;
    @FXML
    private TextField txtNomeCompleto;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCEP;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtNumero;
    @FXML
    private Button btnBuscarCEP;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtComplemento;
    
    private void limparCampos(){
        txtCPF.setText("");
        txtNomeCompleto.setText("");
        txtDataNascimento.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtCidade.setText("");
        txtCEP.setText("");
        txtRua.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void btnSalvarClick(ActionEvent event) {
    }
    
}
