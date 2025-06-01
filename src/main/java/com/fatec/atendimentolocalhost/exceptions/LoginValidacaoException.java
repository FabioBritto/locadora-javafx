/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.exceptions;

/**
 * Classe de exceção destinada a todos os problemas relacionados a validações
 * na sessão de Login. Seja na criação ou acesso
 *
 * @author Fabio
 * 
 */
public class LoginValidacaoException extends RuntimeException {
    
    public LoginValidacaoException(String mensagem){
        super(mensagem);
    }
}
