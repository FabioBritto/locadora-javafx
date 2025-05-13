/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.exceptions;

/**
 * Classe de exceção destinada a todos os problemas relacionados a validações
 * na criação ou recuperação dos Tipos de Seguro.
 * 
 * @author Fabio
 */
public class TipoSeguroValidacaoException extends Exception {
    
    public TipoSeguroValidacaoException(String mensagem){
        super(mensagem);
    }
}
