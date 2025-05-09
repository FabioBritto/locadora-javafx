/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import com.fatec.atendimentolocalhost.exceptions.TipoSeguroValidacaoException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Fabio
 */
public class TipoSeguro {
    
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal taxa;
    
    public TipoSeguro(){
        
    }

    public TipoSeguro(String nome, String descricao, BigDecimal taxa) throws TipoSeguroValidacaoException {
        id = null;
        this.nome = nome;
        this.descricao = descricao;
        setTaxa(taxa);
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

    public void setTaxa(BigDecimal taxa) throws TipoSeguroValidacaoException {
        if(taxa.doubleValue() <= 0.0){
            throw new TipoSeguroValidacaoException("Valor de Taxa inválido");
        }
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
