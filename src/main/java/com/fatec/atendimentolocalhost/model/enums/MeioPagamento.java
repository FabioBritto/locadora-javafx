/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.enums;

/**
 *
 * @author Fabio
 */
public enum MeioPagamento {
    
    DINHEIRO(1),
    PIX(2),
    CREDITO(3),
    DEBITO(4);
    
    private Integer numero;
    
    MeioPagamento(Integer numero){
        this.numero = numero;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public static MeioPagamento setInteiro(Integer numero){
        for(MeioPagamento t : MeioPagamento.values()){
            if(t.getNumero() == numero){
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido");
    }
}
