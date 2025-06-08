/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Fabio
 */
public class Alerts {

    public static void mostrarAlerta(String titulo, String cabecalho, String conteudo, AlertType type) {
            Alert alert = new Alert(type);
            alert.setTitle(titulo);
            alert.setHeaderText(cabecalho);
            alert.setContentText(conteudo);
            alert.show();
    }

    public static Optional<ButtonType> pedirConfirmacao(String titulo, String conteudo) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(conteudo);
            return alert.showAndWait();
    }
}