/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.Cliente;
import com.fatec.atendimentolocalhost.service.ClienteService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alber
 */
public class TelaClientesController implements Initializable {
    
    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private TableView<Cliente> tabelaClientes;
    
    @FXML
    private TableColumn<Cliente, Integer> colunaId;
    
    @FXML
    private TableColumn<Cliente, String> colunaCPF;
    
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    
    @FXML
    private TableColumn<Cliente, String> colunaEmail;
    
    @FXML
    private TableColumn<Cliente, String> colunaTelefone;
    
    @FXML
    private TableColumn<Cliente, String> colunaSituacao;

    ClienteService service;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        abrirTelaCliente();
    }    
    
    @FXML
    private void preencherTabela() {
        
            colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            colunaSituacao.setCellValueFactory(cellData -> {
                Boolean situacao = cellData.getValue().getAtivo();
                String situation = (situacao != null && situacao) ? "ATIVO" : "INATIVO";
                return new ReadOnlyStringWrapper(situation);
            });
            
            service = new ClienteService();
        try {    
            List<Cliente> clientes = service.buscarClientes();
            ObservableList<Cliente> obsClientes = FXCollections.observableArrayList(clientes);
            tabelaClientes.setItems(obsClientes);
        } catch (DBException ex) {
           
        }
    }
    
    @FXML
    public void abrirTelaCliente() {
        tabelaClientes.setRowFactory(t -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked((e) -> {
                if(e.getClickCount() == 2 && !row.isEmpty()){
                    Cliente cliente = row.getItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAtualizaCliente.fxml"));
                    
                    try{
                        VBox v = loader.load();
                        TelaAtualizaClienteController controller = (TelaAtualizaClienteController) loader.getController();
                        controller.preencherCampos(cliente);
                        BorderPane borderPane = (BorderPane) vBoxPrincipal.getParent();
                        borderPane.setCenter(v);
                    }
                    catch(IOException ex){
                        ex.printStackTrace();
                    } 
                }
            });
            return row;
        });
    }
}
