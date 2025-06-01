/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.exceptions;

/**
 * Classe de exceção destinada a todos os problemas relacionados a validações
 * na sessão de Pedidos de Locações. 
 *
 * @author Fabio
 */
public class PedidoLocacaoValidacaoException extends RuntimeException {
    
    public PedidoLocacaoValidacaoException(String mensagem){
        super(mensagem);
    }
}
