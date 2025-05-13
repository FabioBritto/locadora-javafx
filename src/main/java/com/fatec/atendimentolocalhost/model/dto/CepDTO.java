/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Classe responsável por receber os dados da API de CEP. 
 * A classe é utilizada em um dos construtores de Cliente.
 * 
 * @author Fabio
 */
public class CepDTO {
   
    @SerializedName("cep")
    private String cep;
    @SerializedName("logradouro")
    private String rua;
    @SerializedName("bairro")
    private String bairro;
    @SerializedName("localidade")
    private String cidade;
    @SerializedName("estado")
    private String estado;

    public CepDTO(String cep, String rua, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
    
    @Override
    public String toString() {
        return "CepDTO{" + "cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + '}';
    }
    
    
    
    
}
