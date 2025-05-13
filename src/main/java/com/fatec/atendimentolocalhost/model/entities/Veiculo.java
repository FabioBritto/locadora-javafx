/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe de entidade para dados de Veículo.
 * Não existe necessidade de validações para esta classe, uma vez que todos
 * os dados armazenados dela virão diretamente do Banco de Dados.
 * 
 * @author Fabio
 */
public class Veiculo {
    
    private String placa;
    private String marca;
    private String cor;
    private Integer ano;
    private String modelo;
    private Integer quilometragem;
    private BigDecimal precoBase;
    
    private CategoriaVeiculo categoria;
    
    public Veiculo(){
        
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public CategoriaVeiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVeiculo categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.placa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        return Objects.equals(this.placa, other.placa);
    }

    @Override
    public String toString() {
        return "Veiculo{" + "placa=" + placa + ", marca=" + marca + ", cor=" + cor + ", ano=" + ano + ", modelo=" + modelo + ", quilometragem=" + quilometragem + ", precoBase=" + precoBase + ", categoria=" + categoria + '}';
    }
}
