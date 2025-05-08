/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Samsung
 */
public class TipoSeguro {
    
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal taxa;
    
    public TipoSeguro(){
        
    }
    
    public TipoSeguro(Integer id, String nome, String descricao, BigDecimal taxa){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.taxa = taxa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final TipoSeguro other = (TipoSeguro) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "TipoSeguro{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", taxa=" + taxa + '}';
    }
    
    
}
