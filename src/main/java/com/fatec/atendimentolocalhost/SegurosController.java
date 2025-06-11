/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.exceptions.TipoSeguroValidacaoException;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import com.fatec.atendimentolocalhost.service.TipoSeguroService;
import com.fatec.atendimentolocalhost.util.Alerts;
import com.fatec.atendimentolocalhost.util.Constraints;
import com.fatec.atendimentolocalhost.util.Verificar;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Fabio
 */
public class SegurosController implements Initializable {
    
    @FXML
    private Label lblNome, lblTaxa, lblDescricao;
    
    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnNovoSeguro;

    @FXML
    private TableColumn<TipoSeguro, String> colunaDescricao;

    @FXML
    private TableColumn<TipoSeguro, Integer> colunaID;

    @FXML
    private TableColumn<TipoSeguro, String> colunaNomeSeguro;

    @FXML
    private TableColumn<TipoSeguro, BigDecimal> colunaTaxa;

    @FXML
    private Label lblSecao;

    @FXML
    private TableView<TipoSeguro> tabelaSeguros;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNomeSeguro;

    @FXML
    private TextField txtTaxa;
    
    private TipoSeguroService service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        preencherCampos();
        inicializarConstraints();
    }
    
    @FXML
    private void btnCadastrarClick(){
        
        TipoSeguro seguro = criarSeguro();
        if(!Verificar.todosAtributosPreenchidos(seguro, "getId")) {
            Alerts.mostrarAlerta("ERRO", null, "Para continuar, preencha todos os campos corretamente.", Alert.AlertType.ERROR);
        }
        else{
            Optional<ButtonType> resposta = Alerts.pedirConfirmacao("CONFIRMAÇÃO", "Desejar cadastrar este seguro?");
            if(resposta.get() == ButtonType.OK){
                service = new TipoSeguroService();
            }
            try{
                service.cadastrarSeguro(seguro);
            }
            catch(DBException e){
                System.out.println(e.getMessage());
            }
            
        }
    }
    
    private TipoSeguro criarSeguro() {
        esconderLabels();
        
        TipoSeguro seguro = new TipoSeguro();
        try{
            if(txtNomeSeguro.getText().length() < 5) throw new TipoSeguroValidacaoException("O nome do Seguro deve ter ao menos 5 caracteres");
            seguro.setNome(txtNomeSeguro.getText());
        }
        catch(TipoSeguroValidacaoException e){
            lblNome.setText(e.getMessage());
            lblNome.setVisible(true);
        }
        
        try{
           if(txtDescricao.getText().length() < 10) throw new TipoSeguroValidacaoException("Por favor, descreva o seguro com mais detalhes");
           seguro.setDescricao(txtDescricao.getText());
        }
        catch(TipoSeguroValidacaoException e){
            lblDescricao.setText(e.getMessage());
            lblDescricao.setVisible(true);
        }
        
        try{
            seguro.setTaxa(new BigDecimal(txtTaxa.getText()));
        }
        catch(TipoSeguroValidacaoException e){
            lblTaxa.setText(e.getMessage());
            lblTaxa.setVisible(true);
        }
        
        return seguro;
        
    }
    
    private void esconderLabels(){
        lblNome.setVisible(false);
        lblTaxa.setVisible(false);
        lblDescricao.setVisible(false);
    }
    
    @FXML 
    public void preencherCampos() {
        tabelaSeguros.setRowFactory(t -> {
            TableRow<TipoSeguro> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    TipoSeguro seguro = row.getItem();
                    txtId.setText(String.valueOf(seguro.getId()));
                    txtNomeSeguro.setText(seguro.getNome());
                    txtDescricao.setText(seguro.getDescricao());
                    txtTaxa.setText(seguro.getTaxa().toString());
                }
            });
            return row;
        });
    }
    
    @FXML
    private void preencherTabela() {

        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeSeguro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaTaxa.setCellValueFactory(new PropertyValueFactory<>("taxa"));

        service = new TipoSeguroService();

        try {
            List<TipoSeguro> seguros = service.buscarSeguros();
            ObservableList<TipoSeguro> tbSeguros = FXCollections.observableArrayList(seguros);
            tabelaSeguros.setItems(tbSeguros);
        } catch (DBException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void btnNovoSeguroClick() {
        limparCampos();
    }
    
     @FXML
    private void limparCampos() {
        txtId.setText(null);
        txtNomeSeguro.setText(null);
        txtDescricao.setText(null);
        txtTaxa.setText(null);
    }
    
    private void inicializarConstraints() {
        Constraints.setTextFieldInteger(txtTaxa);
    }
}
