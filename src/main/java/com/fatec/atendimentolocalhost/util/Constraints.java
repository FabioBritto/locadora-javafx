/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.util;

import javafx.scene.control.TextField;

/**
 * Classe destinada a validações relacionadas a objetos TextFields
 * com a utilização de Expressões Regulares (regex).
 *
 * @author Fabio
 */
public class Constraints {
    
    public static void setTextFieldInteger(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && !newValue.matches("\\d*")) {
                txt.setText(oldValue);
            }
        });
    }
    
    public static void setTextFieldMaxLength(TextField txt, int max) {
        txt.textProperty().addListener((obs, oldValue, newValue) ->{
            if(newValue != null && newValue.length() > max) {
                txt.setText(oldValue);
            }
        });
    }
    
    public static void setTextFieldDouble(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) ->{
            if(newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
                txt.setText(oldValue);
            }
        });
    }
}
