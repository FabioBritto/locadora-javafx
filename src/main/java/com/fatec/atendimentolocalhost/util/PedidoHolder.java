/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.util;

import com.fatec.atendimentolocalhost.model.entities.PedidoLocacao;

/**
 *
 * @author chris
 */
public class PedidoHolder {
    private final PedidoHolder pedidoHolder= new PedidoHolder();
    
    private PedidoLocacao pedido = new PedidoLocacao();
    
    private PedidoHolder(){
        
    }
    
    public PedidoLocacao getPedido(){
        return pedido;
    }
    
    public PedidoHolder getInstance(){
        return pedidoHolder;
    }
    
    public void restartPedido(){
        this.pedido = new PedidoLocacao();
    }
    
}
