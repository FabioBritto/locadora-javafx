/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import com.fatec.atendimentolocalhost.exceptions.ClienteValidacaoException;
import com.fatec.atendimentolocalhost.model.dto.CepDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Classe de entidade para armazenar dados de um Cliente. Contém também
 * ainda uma lista de todas as locações realizadas.
 * 
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
    
    /**
     * Construtor usado para criação de Usuário quando a API de CEP não estiver disponível.
     * Neste caso, os dados de endereço serão configurados manualmente.
     * Os atributos que demandam validação, são inseridos via métodos Set
     * 
     * @param cpf
     * @param nome
     * @param email
     * @param cep
     * @param cidade
     * @param numero
     * @param complemento
     * @param bairro
     * @param estado
     * @param rua
     * @param dataNascimento
     * @param telefone
     * @throws ClienteValidacaoException 
     */
    public Cliente(String cpf, String nome, String email, 
            String cep,String cidade ,String numero, String complemento, String bairro, 
            String estado, String rua, LocalDate dataNascimento, String telefone) throws ClienteValidacaoException {
        id = null;
        setCpf(cpf);
        setNome(nome);
        setEmail(email);
        setCep(cep);
        setCidade(cidade);
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.rua = rua;
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
        this.ativo = true;
    }
    
    /**
     * Construtor utilizado para criaçã de Usuário com o uso da API de CEP.
     * É passado como parte dos parâmetros, um CepDTO.
     * 
     * @param cpf
     * @param nome
     * @param email
     * @param cepDTO
     * @param numero
     * @param complemento
     * @param dataNascimento
     * @param telefone
     * @throws ClienteValidacaoException 
     */
    public Cliente(String cpf, String nome, String email,CepDTO cepDTO,
            String numero, String complemento, LocalDate dataNascimento, String telefone) throws ClienteValidacaoException {
        id = null;
        setCpf(cpf);
        setNome(nome);
        setEmail(email);
        setCep(cepDTO.getCep());
        setCidade(cepDTO.getCidade());
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = cepDTO.getBairro();
        this.estado = cepDTO.getEstado();
        this.rua = cepDTO.getRua();
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

    /**
     * Validação para inserção de CPF verificando o número de caracteres
     * 
     * @param cpf
     * @throws ClienteValidacaoException 
     */
    public void setCpf(String cpf) {
        if(cpf.length() != 11){
            throw new ClienteValidacaoException("O CPF informado é inválido");
        }
        this.cpf = cpf;
    }

    public String getNome() {
        return nome == null ? "" : nome;
    }

    public void setNome(String nome) {
        if(nome.length() < 3){
            throw new ClienteValidacaoException("O nome deve ter ao menos 5 caracteres");
        }
        this.nome = nome.replace(" ", "");
    }

    public String getEmail() {
        return email == null ? "" : email;
    }
    
    /**
     * Validação para inserção de Email verificando a existência de caracteres especiais
     * 
     * @param email
     * @throws ClienteValidacaoException 
     */
    public void setEmail(String email) throws ClienteValidacaoException {
        if(!email.contains("@") || !email.contains(".com")){
            throw new ClienteValidacaoException("Email inválido");
        }
        this.email = email;
    }

    public String getCep() {
        return cep == null ? "" : cep;
    }

    /**
     * Validação de inserção de CEP verificando o número de caracteres
     * 
     * @param cep
     * @throws ClienteValidacaoException 
     */
    public void setCep(String cep) throws ClienteValidacaoException {
        if(cep.length() != 8){
            throw new ClienteValidacaoException("CEP inválido");
        }
        this.cep = cep;
    }

    public String getRua() {
        return rua == null ? "" : rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero == null ? "" : numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento == null ? "" : complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro == null ? "" : bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade == null ? "" : cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado == null ? "" : estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Validação de inserção de Data Nascimento, garantindo
     * que o Cliente seja maior de 18 anos
     * 
     * @param dataNascimento
     * @throws ClienteValidacaoException 
     */
    public void setDataNascimento(LocalDate dataNascimento) throws ClienteValidacaoException {
        if(LocalDate.now().minusYears(18).isBefore(dataNascimento)){
            throw new ClienteValidacaoException("O Cliente precisa ser maior de 18 anos");
        }
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone == null ? "" : telefone;
    }

    /**
     * Validação de inserção de telefone verificando o número de caracteres
     * 
     * @param telefone
     * @throws ClienteValidacaoException 
     */
    public void setTelefone(String telefone) throws ClienteValidacaoException {
        if(telefone.length() > 16){
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
    
    
    public List<PedidoLocacao> recuperarLocacoes(){
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
