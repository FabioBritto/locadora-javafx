/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.exceptions;

/**
 * Classe de exceção destinada a todos os problemas relacionados a validações
 * na sessão de Cliente. Seja na criação ou atualização
 * 
 * @author Fabio
 */
public class ClienteValidacaoException extends RuntimeException {
    
    public ClienteValidacaoException(String mensagem){
        super(mensagem);
    }
}
