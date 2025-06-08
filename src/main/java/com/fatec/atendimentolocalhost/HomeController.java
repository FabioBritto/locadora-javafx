/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Christian
 */
public class HomeController {
    
    @FXML
    private Button btnNovoPedido;
    
    @FXML
    private Button btnUsuarios;
    
    @FXML
    private Button btnClientes;
    
 
    @FXML
    public BorderPane borderPane;
    

    //O método abaixo foi criados somente para teste, alterar na hora da criação das telas reais do sistema.
    //Mas a ideia de troca de tela usando borderPane.setCenter() continua a mesma
    @FXML
    private void novoPedidoClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("novalocacao.fxml"));
        VBox v = loader.load();
        
        borderPane.setCenter(v);
    }
    
    @FXML
    private void usuariosClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("usuarios.fxml"));
        VBox v = loader.load();
        
        borderPane.setCenter(v);
    }
    
    @FXML
    private void clientesClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaClientes.fxml"));
        VBox v = loader.load();
        
        borderPane.setCenter(v);
    }
}
