/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.ClienteValidacaoException;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dto.CepDTO;
import com.fatec.atendimentolocalhost.model.entities.Cliente;
import com.fatec.atendimentolocalhost.service.ClienteService;
import com.fatec.atendimentolocalhost.util.Alerts;
import com.fatec.atendimentolocalhost.util.CepApi;
import com.fatec.atendimentolocalhost.util.Constraints;
import com.fatec.atendimentolocalhost.util.Verificar;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alber
 */
public class TelaAtualizaClienteController implements Initializable {

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private Label lblNome, lblEmail, lblTelefone, lblCep, lblRua,
            lblCidade, lblEstado, lblBairro, lblNumero;

    @FXML
    private TextField txtId;

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
    private RadioButton rbAtivo;

    @FXML
    private RadioButton rbInativo;

    @FXML
    private Button btnBuscarCEP;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtComplemento;

    ClienteService service;

    private void limparCampos() {
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
        inicializarConstraints();
        txtCEP.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                preencherEndereco();
            }
        });
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        Optional<ButtonType> resposta = Alerts.pedirConfirmacao("CONFIRMAÇÃO", "Deseja descartar as alterações?");
        if (resposta.get() == ButtonType.OK) {
            abrirTelaClientes();
            limparCampos();
        }

    }

    @FXML
    private void btnConfirmarClick() {
        Cliente cliente = criarCliente();
        if (!Verificar.todosAtributosPreenchidos(cliente, "getComplemento")) {
            System.out.println(cliente);
            Alerts.mostrarAlerta("ERRO", null, "Para continuar, preencha todos os campos corretamente.", Alert.AlertType.ERROR);
        } else {
            Optional<ButtonType> resposta = Alerts.pedirConfirmacao("CONFIRMAÇÃO", "Deseja salvar as alterações?");
            if (resposta.get() == ButtonType.OK) {
                service = new ClienteService();
                try {
                    service.atualizarCliente(cliente);
                    Alerts.mostrarAlerta("ALTERAÇÕES SALVAS", null, "Alterações Salvas com Sucesso", Alert.AlertType.INFORMATION);
                    abrirTelaClientes();
                } catch (DBException e) {
                    Alerts.mostrarAlerta("ERRO", null, e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    private Cliente criarCliente() {
        esconderLabels();
        Cliente cliente = new Cliente();
        cliente.setId(Integer.valueOf(txtId.getText()));
        cliente.setCpf(txtCPF.getText());
        cliente.setNome(txtNomeCompleto.getText());
        cliente.setDataNascimento(LocalDate.parse(txtDataNascimento.getText()));
        try {
            cliente.setEmail(txtEmail.getText());
        } catch (ClienteValidacaoException e) {
            lblEmail.setText(e.getMessage());
            lblEmail.setVisible(true);
        }
        try {
            cliente.setTelefone(txtTelefone.getText());
        } catch (ClienteValidacaoException e) {
            lblTelefone.setText(e.getMessage());
            lblTelefone.setVisible(true);
        }
        try {
            cliente.setCep(txtCEP.getText());
        } catch (ClienteValidacaoException e) {
            lblCep.setText(e.getMessage());
            lblCep.setVisible(true);
        }
        try{
            if(txtRua.getText().isBlank()) throw new ClienteValidacaoException("Campo obrigatório");
            cliente.setRua(txtRua.getText());
        }
        catch(ClienteValidacaoException e){
            lblRua.setText(e.getMessage());
            lblRua.setVisible(true);
        }
        try{
            if(txtBairro.getText().isBlank()) throw new ClienteValidacaoException("Campo obrigatório");
            cliente.setBairro(txtBairro.getText());
        }
        catch(ClienteValidacaoException e){
            lblBairro.setText(e.getMessage());
            lblBairro.setVisible(true);
        }
        try{
            if(txtCidade.getText().isBlank()) throw new ClienteValidacaoException("Campo obrigatório");
            cliente.setCidade(txtCidade.getText());
        }
        catch(ClienteValidacaoException e){
            lblCidade.setText(e.getMessage());
            lblCidade.setVisible(true);
        }
        try{
            if(txtEstado.getText().isBlank()) throw new ClienteValidacaoException("Campo obrigatório");
            cliente.setEstado(txtEstado.getText());
        }
        catch(ClienteValidacaoException e){
            lblEstado.setText(e.getMessage());
            lblEstado.setVisible(true);
        }
        try{
            if(txtNumero.getText().isBlank()) throw new ClienteValidacaoException("Campo obrigatório");
            cliente.setNumero(txtNumero.getText());
        }
        catch(ClienteValidacaoException e){
            lblNumero.setText(e.getMessage());
            lblNumero.setVisible(true);
        }
        cliente.setComplemento(txtComplemento.getText());
        cliente.setAtivo(rbAtivo.isSelected());
        return cliente;
    }
    
    private void esconderLabels(){
        lblCep.setVisible(false);
        lblEmail.setVisible(false);
        lblNome.setVisible(false);
        lblTelefone.setVisible(false);
        lblBairro.setVisible(false);
        lblCidade.setVisible(false);
        lblEstado.setVisible(false);
        lblNumero.setVisible(false);
    }
    
//    private boolean verificaCamposMinimos(Cliente cliente){
//        if(cliente.getCep().isEmpty() || cliente.getEmail().isEmpty() ||
//                cliente.getTelefone().isEmpty() || cliente.getBairro().isEmpty() ||
//                cliente.getCidade().isEmpty() || cliente.get)
//    }

    @FXML
    public void preencherCampos(Cliente cliente) {
        txtId.setText(String.valueOf(cliente.getId()));
        txtCPF.setText(cliente.getCpf());
        txtNomeCompleto.setText(cliente.getNome());
        txtDataNascimento.setText(cliente.getDataNascimento().toString());
        txtEmail.setText(cliente.getEmail());
        txtTelefone.setText(cliente.getTelefone());
        txtCEP.setText(cliente.getCep());
        txtRua.setText(cliente.getRua());
        txtBairro.setText(cliente.getBairro());
        txtNumero.setText(cliente.getNumero());
        txtCidade.setText(cliente.getCidade());
        txtEstado.setText(cliente.getEstado());
        txtComplemento.setText(cliente.getComplemento());
        if (cliente.getAtivo()) {
            rbAtivo.setSelected(true);
        } else {
            rbInativo.setSelected(true);
        }
    }

    @FXML
    private void preencherEndereco() {
        try {
            CepDTO cep = CepApi.encontrarEndereco(txtCEP.getText());
            txtRua.setText(cep.getRua());
            txtBairro.setText(cep.getBairro());
            txtCidade.setText(cep.getCidade());
            txtEstado.setText(cep.getEstado());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void desabilitarAtivo() {
        rbAtivo.setSelected(false);
    }

    @FXML
    private void desabilitarInativo() {
        rbInativo.setSelected(false);
    }

    private void abrirTelaClientes() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaClientes.fxml"));
        try {
            VBox v = loader.load();
            BorderPane borderPane = (BorderPane) vBoxPrincipal.getParent();
            borderPane.setCenter(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarConstraints() {
        Constraints.setTextFieldMaxLength(txtCPF, 11);
        Constraints.setTextFieldMaxLength(txtCEP, 8);
        Constraints.setTextFieldMaxLength(txtTelefone, 16);
        Constraints.setTextFieldInteger(txtCPF);
        Constraints.setTextFieldInteger(txtCEP);
    }
}
