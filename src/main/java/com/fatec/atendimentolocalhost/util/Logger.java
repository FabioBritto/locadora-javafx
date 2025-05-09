/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.util;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author Fabio
 */
public class Logger {
    
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE  = "\u001B[37m";
    public static final String RESET  = "\u001B[0m";
    
    public static DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static void showBanner(){
        
    }
}
