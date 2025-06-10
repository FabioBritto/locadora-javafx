/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import com.fatec.atendimentolocalhost.exceptions.ClienteValidacaoException;
import com.fatec.atendimentolocalhost.exceptions.LoginValidacaoException;
import com.fatec.atendimentolocalhost.model.enums.TipoUsuario;
import java.util.Objects;

/**
 * Classe de entidade responsável por armazenar os dados de um Usuário.
 * 
 * @author Fabio
 */
public class Usuario {
    
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Boolean ativo;
    
    public Usuario(){
        
    }
    
    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) throws LoginValidacaoException {
        id = null;
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.tipoUsuario = tipoUsuario;
        ativo = true;
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
        if(nome.length() < 5){
            throw new LoginValidacaoException("O nome de Usuário deve ter ao menos 5 caracteres");
        }
        this.nome = nome.replace(" ", "");
    }

    public String getEmail() {
        return email;
    }
    
    /**
     * Validação para inserção do email. Ele deve conter os caracteres especiais.
     * @param email
     * @throws LoginValidacaoException 
     */
    public void setEmail(String email) throws LoginValidacaoException {
        if(!email.contains("@") || !email.contains(".com")){
            throw new LoginValidacaoException("Email inválido");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    /**
     * Validação para inserção da senha. Ela deve conter ao menos 8 dígitos.
     * @param senha
     * @throws LoginValidacaoException 
     */
    public void setSenha(String senha) throws LoginValidacaoException {
        if(senha.length() < 8){
            throw new LoginValidacaoException("A senha deve ter ao menos 8 digitos");
        }
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Validação para inserção do Tipo de Usuário. O valor não pode ser nulo.
     * @param tipoUsuario
     * @throws LoginValidacaoException 
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) throws LoginValidacaoException {
        if(tipoUsuario == null){
            throw new LoginValidacaoException("É preciso definir um nível de acesso");
        }
        this.tipoUsuario = tipoUsuario;
    }
    
    public Boolean getAtivo(){
        return ativo;
    }
    
    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
    
}
