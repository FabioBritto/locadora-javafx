/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.enums;

/**
 * Enum para escolha do Tipo de Usuário na hora da criação de um novo usuário do sistema.
 * Ele contém métodos para recuperar o índice ou para escolher uma das opções
 * a partir de um inteiro fornecido. Este método facilita a integração com o Banco de Dados.
 * 
 * @author Fabio
 */
public enum TipoUsuario {
    
    ATENDENTE(1),
    GERENTE(2);
    
    private Integer numero;
    
    TipoUsuario(Integer numero){
        this.numero = numero;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public static TipoUsuario setInteiro(Integer numero){
        for(TipoUsuario t : TipoUsuario.values()){
            if(t.getNumero() == numero){
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido");
    }
}
