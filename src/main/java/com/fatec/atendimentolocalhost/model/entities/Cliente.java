/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import com.fatec.atendimentolocalhost.exceptions.ClienteValidacaoException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Fabio
 */
public class Cliente {
    
    
    private Integer id;
    private String cpf;
    private String nome;
    private String email;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private LocalDate dataNascimento;
    private String telefone;
    private Boolean ativo;
    
    List<PedidoLocacao> locacoes;
    
    
    public Cliente(){
    }

    public Cliente(String cpf, String nome, String email, 
            String cep, String numero, String complemento, String bairro, 
            String estado, String rua, LocalDate dataNascimento, String telefone) throws ClienteValidacaoException {
        id = null;
        setCpf(cpf);
        this.nome = nome;
        setEmail(email);
        setCep(cep);
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.rua = rua;
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
        this.ativo = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ClienteValidacaoException {
        if(cpf.length() != 11){
            throw new ClienteValidacaoException("O CPF informado é inválido");
        }
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ClienteValidacaoException {
        if(!email.contains("@") || !email.contains(".com")){
            throw new ClienteValidacaoException("Email inválido");
        }
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) throws ClienteValidacaoException {
        if(cep.length() != 8){
            throw new ClienteValidacaoException("CEP inválido");
        }
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) throws ClienteValidacaoException {
        if(LocalDate.now().minusYears(18).isBefore(dataNascimento)){
            throw new ClienteValidacaoException("O Cliente precisa ser maior de 18 anos");
        }
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ClienteValidacaoException {
        if(telefone.length() != 16){
            throw new ClienteValidacaoException("Número de telefone inválido");
        }
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
    public List<PedidoLocacao> getLocacoes(){
        return locacoes;
    }
    
    public void setLocacoes(List<PedidoLocacao> locacoes){
        this.locacoes = locacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", cep=" + cep + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone + ", ativo=" + ativo + '}';
    }
    
    
    
    
}
