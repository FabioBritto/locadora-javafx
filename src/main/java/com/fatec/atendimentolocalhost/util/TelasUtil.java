/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.util;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Fabio
 */
public class TelasUtil {
    
    public static Boolean txtPerdeuFoco(TextField textField) {
        
        BooleanProperty perdeuFoco = new SimpleBooleanProperty(false);
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                System.out.println("true");
                perdeuFoco.set(true);
            }
            else{
                System.out.println("false");
                perdeuFoco.set(false);
            }
        });
        return perdeuFoco.getValue();
    }
    
    public static void alternarRadioButton(RadioButton rb){
        rb.setSelected(!rb.isSelected());
    }
}
