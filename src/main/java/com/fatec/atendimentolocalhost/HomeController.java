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
    private Button novoPedido;
    
 
    @FXML
    public  BorderPane borderPane;
    

    //O método abaixo foi criados somente para teste, alterar na hora da criação das telas reais do sistema.
    //Mas a ideia de troca de tela usando borderPane.setCenter() continua a mesma
    @FXML
    private void novoPedidoClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        VBox v = loader.load();
        
        borderPane.setCenter(v);
    }
    
}
