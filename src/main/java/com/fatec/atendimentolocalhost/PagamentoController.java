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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alber
 */
public class PagamentoController implements Initializable {

    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtValorBase;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtCategoria;
    @FXML
    private Button btnAlterarVeiculo;
    @FXML
    private TextArea txtDescricaoSeguro;
    @FXML
    private Button btnAlterarSeguro;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtDataNascimento;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtTotalSeguro;
    @FXML
    private TextField txtTotalVeiculo;
    @FXML
    private DatePicker dataPickerDevolucao;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnFinalizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAlterarVeiculoClick(ActionEvent event) {
    }

    @FXML
    private void btnAlterarSeguroClick(ActionEvent event) {
    }

    @FXML
    private void btnFinalizarClick(ActionEvent event) {
    }
    
}
