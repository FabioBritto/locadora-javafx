/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.entities;

import com.fatec.atendimentolocalhost.exceptions.PedidoLocacaoValidacaoException;
import com.fatec.atendimentolocalhost.model.enums.MeioPagamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe de emtidade para armazenar os dados de um Pedido de Locação.
 * 
 * @author Fabio
 */
public class PedidoLocacao {
    
    private Integer id;
    private Integer idSaida;
    private Integer idDevolucao;
    private LocalDate devolucaoEsperada;
    private Boolean finalizado;
    private BigDecimal valorTotal;
    private MeioPagamento meioPagamento;
    
    private Veiculo veiculo;
    private TipoSeguro tipoSeguro;
    private Cliente cliente;
    private Usuario atendente;
    
    public PedidoLocacao(){
        finalizado = Boolean.FALSE;
        Usuario u = new Usuario();
        u.setId(2);
        atendente = u;
    }
    
    public PedidoLocacao(LocalDate devolucaoEsperada,
                        BigDecimal valorTotal, MeioPagamento meioPagamento) throws PedidoLocacaoValidacaoException {
        id = null;
        idSaida = null;
        idDevolucao = null;
        setDevolucaoEsperada(devolucaoEsperada);
        setValorTotal(valorTotal);
        setMeioPagamento(meioPagamento);
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public Integer getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(Integer idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public LocalDate getDevolucaoEsperada() {
        return devolucaoEsperada;
    }

    /**
     * Validação para data devolução esparada.
     * A data não pode ser no mesmo dia ou antes da data de locação
     * 
     * @param devolucaoEsperada
     * @throws PedidoLocacaoValidacaoException 
     */
    public void setDevolucaoEsperada(LocalDate devolucaoEsperada) throws PedidoLocacaoValidacaoException {
        if(!getFinalizado() && devolucaoEsperada.isBefore(LocalDate.now())){
            throw new PedidoLocacaoValidacaoException("A data de devolução tem de ser depois de hoje");
        }
        this.devolucaoEsperada = devolucaoEsperada;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    /**
     * Validação para Meio de Pagamento, garantindo que seu valor não seja nulo.
     * @param meioPagamento
     * @throws PedidoLocacaoValidacaoException 
     */
    public void setMeioPagamento(MeioPagamento meioPagamento) throws PedidoLocacaoValidacaoException {
        if(meioPagamento == null){
            throw new PedidoLocacaoValidacaoException("Deve ser escolhido um meio de pagamento");
        }
        this.meioPagamento = meioPagamento;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * Validação para o Valor Total de um Pedido. O valor deve ser positivo.
     * 
     * @param valorTotal
     * @throws PedidoLocacaoValidacaoException 
     */
    public void setValorTotal(BigDecimal valorTotal) throws PedidoLocacaoValidacaoException {
        if(valorTotal.doubleValue() <= 0.0){
            throw new PedidoLocacaoValidacaoException("O valor de locação não pode ser nulo ou negativo");
        }
        this.valorTotal = valorTotal;
    }
    
    

    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Validação para o Veículo para garantir que o objeto não será nulo.
     * 
     * @param veiculo
     * @throws PedidoLocacaoValidacaoException 
     */
    public void setVeiculo(Veiculo veiculo) throws PedidoLocacaoValidacaoException {
        if(veiculo == null){
            throw new PedidoLocacaoValidacaoException("O veículo precisa ser definido para a locação");
        }
        this.veiculo = veiculo;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    /**
     * Validação para o Tipo de Seguro para garantir que o objeto não será nulo.
     * 
     * @param tipoSeguro
     * @throws PedidoLocacaoValidacaoException 
     */
    public void setTipoSeguro(TipoSeguro tipoSeguro) throws PedidoLocacaoValidacaoException {
        if(tipoSeguro == null){
            throw new PedidoLocacaoValidacaoException("O Tipo de Seguro precisa ser definido para a locação");
        }
        this.tipoSeguro = tipoSeguro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Validação para o Cliente para garantir que o objeto não será nulo.
     * 
     * @param cliente
     * @throws PedidoLocacaoValidacaoException 
     */
    public void setCliente(Cliente cliente) throws PedidoLocacaoValidacaoException {
        if(cliente == null){
            throw new PedidoLocacaoValidacaoException("O Cliente precisa ser escolhido para a locação");
        }
        this.cliente = cliente;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final PedidoLocacao other = (PedidoLocacao) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "PedidoLocacao{" + "id=" + id + ", idSaida=" + idSaida + ", idDevolucao=" + idDevolucao + ", devolucaoEsperada=" + devolucaoEsperada + ", meioPagamento=" + meioPagamento + ", finalizado=" + finalizado + ", valorTotal=" + valorTotal + ", veiculo=" + veiculo + ", tipoSeguro=" + tipoSeguro + ", cliente=" + cliente + ", atendente=" + atendente + '}';
    }

    
}
