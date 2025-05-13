/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dao;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.exceptions.PedidoLocacaoValidacaoException;
import com.fatec.atendimentolocalhost.model.entities.Cliente;
import com.fatec.atendimentolocalhost.model.entities.PedidoLocacao;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
import com.fatec.atendimentolocalhost.model.entities.Veiculo;
import com.fatec.atendimentolocalhost.model.enums.MeioPagamento;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pelo acesso ao Banco de Dados pela classe de PedidoLocacao.
 * 
 * @author Fabio
 */
public class PedidoLocacaoDAO {
    
    private Database database;
    
    public PedidoLocacaoDAO(Database database){
        this.database = database;
    }
    
    public List<PedidoLocacao> findAll() throws DBException {
        List<PedidoLocacao> pedidos = new LinkedList<>();
        try{
            String sql = "SELECT * FROM pedidos_locacao";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            ClienteDAO clienteDAO = new ClienteDAO(database);
            TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
            VeiculoDAO veiculoDAO = new VeiculoDAO(database);
            
            while(rs.next()){
                PedidoLocacao pedido = new PedidoLocacao();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setIdSaida(rs.getInt("id_saida"));
                pedido.setIdDevolucao(rs.getInt("id_devolucao"));
                pedido.setDevolucaoEsperada(rs.getDate("devolucao_esperada").toLocalDate());
                pedido.setFinalizado(rs.getBoolean("finalizado"));
                pedido.setValorTotal(new BigDecimal(rs.getDouble("valor_total")));
                pedido.setMeioPagamento(MeioPagamento.setInteiro(rs.getInt("forma_de_pagamento")));
                
                Veiculo veiculo = veiculoDAO.findByPlaca(rs.getString("placa")).get();
                TipoSeguro seguro = tipoSeguroDAO.findById(rs.getInt("id_seguro")).get();
                Cliente cliente = clienteDAO.findById(rs.getInt("id_cliente")).get();
                Usuario usuario = usuarioDAO.findById(rs.getInt("id_atendente")).get();
                pedido.setVeiculo(veiculo);
                pedido.setTipoSeguro(seguro);
                pedido.setCliente(cliente);
                pedido.setAtendente(usuario);
                pedidos.add(pedido);
            }
            rs.close();
            st.close();
        }
        catch(SQLException | PedidoLocacaoValidacaoException e){
            throw new DBException("Não foi possível recuperar a lista de Pedidos de Locação: " + e.getMessage());
        }
        return pedidos;
    }
    
    public List<PedidoLocacao> findByCliente(Cliente cliente) throws DBException {
        List<PedidoLocacao> pedidos = new LinkedList<>();
        try{
            String sql = "SELECT FROM pedidos_locacao WHERE id_cliente = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, cliente.getId());
            ResultSet rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
            VeiculoDAO veiculoDAO = new VeiculoDAO(database);
            
            while(rs.next()){
                PedidoLocacao pedido = new PedidoLocacao();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setIdSaida(rs.getInt("id_saida"));
                pedido.setIdDevolucao(rs.getInt("id_devolucao"));
                pedido.setDevolucaoEsperada(rs.getDate("devolucao_esperada").toLocalDate());
                pedido.setFinalizado(rs.getBoolean("finalizado"));
                pedido.setValorTotal(new BigDecimal(rs.getDouble("valor_total")));
                pedido.setMeioPagamento(MeioPagamento.setInteiro(rs.getInt("forma_de_pagamento")));
                
                Veiculo veiculo = veiculoDAO.findByPlaca(rs.getString("placa")).get();
                TipoSeguro seguro = tipoSeguroDAO.findById(rs.getInt("id_seguro")).get();
                Usuario usuario = usuarioDAO.findById(rs.getInt("id_atendente")).get();
                pedido.setVeiculo(veiculo);
                pedido.setTipoSeguro(seguro);
                pedido.setCliente(cliente);
                pedido.setAtendente(usuario);
                pedidos.add(pedido);
            }
            rs.close();
            st.close();
        }
        catch(SQLException | PedidoLocacaoValidacaoException e){
            throw new DBException("Não foi possível recuperar a lista de Pedidos de Locação: " + e.getMessage());
        }
        return pedidos;
    }
    
    public List<PedidoLocacao> findByVeiculo(Veiculo veiculo) throws DBException {
        List<PedidoLocacao> pedidos = new LinkedList<>();
        try{
            String sql = "SELECT FROM pedidos_locacao WHERE placa = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setString(1, veiculo.getPlaca());
            ResultSet rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
            ClienteDAO clienteDAO = new ClienteDAO(database);
            
            while(rs.next()){
                PedidoLocacao pedido = new PedidoLocacao();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setIdSaida(rs.getInt("id_saida"));
                pedido.setIdDevolucao(rs.getInt("id_devolucao"));
                pedido.setDevolucaoEsperada(rs.getDate("devolucao_esperada").toLocalDate());
                pedido.setFinalizado(rs.getBoolean("finalizado"));
                pedido.setValorTotal(new BigDecimal(rs.getDouble("valor_total")));
                pedido.setMeioPagamento(MeioPagamento.setInteiro(rs.getInt("forma_de_pagamento")));
                
                TipoSeguro seguro = tipoSeguroDAO.findById(rs.getInt("id_seguro")).get();
                Usuario usuario = usuarioDAO.findById(rs.getInt("id_atendente")).get();
                Cliente cliente = clienteDAO.findById(rs.getInt("id_cliente")).get();
                pedido.setVeiculo(veiculo);
                pedido.setTipoSeguro(seguro);
                pedido.setCliente(cliente);
                pedido.setAtendente(usuario);
                pedidos.add(pedido);
            }
            rs.close();
            st.close();
        }
        catch(SQLException | PedidoLocacaoValidacaoException e){
            throw new DBException("Não foi possível recuperar a lista de Pedidos de Locação: " + e.getMessage());
        }
        return pedidos;
    }
    
    public List<PedidoLocacao> findByDevolucaoEsperada(LocalDate data) throws DBException {
        List<PedidoLocacao> pedidos = new LinkedList<>();
        try{
            String sql = "SELECT FROM pedidos_locacao WHERE devolucao_esperada = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setDate(1, Date.valueOf(data));
            ResultSet rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            ClienteDAO clienteDAO = new ClienteDAO(database);
            TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
            VeiculoDAO veiculoDAO = new VeiculoDAO(database);
            
            while(rs.next()){
                PedidoLocacao pedido = new PedidoLocacao();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setIdSaida(rs.getInt("id_saida"));
                pedido.setIdDevolucao(rs.getInt("id_devolucao"));
                pedido.setDevolucaoEsperada(rs.getDate("devolucao_esperada").toLocalDate());
                pedido.setFinalizado(rs.getBoolean("finalizado"));
                pedido.setValorTotal(new BigDecimal(rs.getDouble("valor_total")));
                pedido.setMeioPagamento(MeioPagamento.setInteiro(rs.getInt("forma_de_pagamento")));
                
                Veiculo veiculo = veiculoDAO.findByPlaca(rs.getString("placa")).get();
                TipoSeguro seguro = tipoSeguroDAO.findById(rs.getInt("id_seguro")).get();
                Cliente cliente = clienteDAO.findById(rs.getInt("id_cliente")).get();
                Usuario usuario = usuarioDAO.findById(rs.getInt("id_atendente")).get();
                pedido.setVeiculo(veiculo);
                pedido.setTipoSeguro(seguro);
                pedido.setCliente(cliente);
                pedido.setAtendente(usuario);
                pedidos.add(pedido);
            }
            rs.close();
            st.close();
        }
        catch(SQLException | PedidoLocacaoValidacaoException e){
            throw new DBException("Não foi possível recuperar a lista de Pedidos de Locação: " + e.getMessage());
        }
        return pedidos;
    }
    
    public List<PedidoLocacao> findByFinalizado(Boolean finalizado) throws DBException {
        List<PedidoLocacao> pedidos = new LinkedList<>();
        try{
            String sql = "SELECT FROM pedidos_locacao WHERE finalizado = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setBoolean(1, finalizado);
            ResultSet rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            ClienteDAO clienteDAO = new ClienteDAO(database);
            TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
            VeiculoDAO veiculoDAO = new VeiculoDAO(database);
            
            while(rs.next()){
                PedidoLocacao pedido = new PedidoLocacao();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setIdSaida(rs.getInt("id_saida"));
                pedido.setIdDevolucao(rs.getInt("id_devolucao"));
                pedido.setDevolucaoEsperada(rs.getDate("devolucao_esperada").toLocalDate());
                pedido.setFinalizado(rs.getBoolean("finalizado"));
                pedido.setValorTotal(new BigDecimal(rs.getDouble("valor_total")));
                pedido.setMeioPagamento(MeioPagamento.setInteiro(rs.getInt("forma_de_pagamento")));
                
                Veiculo veiculo = veiculoDAO.findByPlaca(rs.getString("placa")).get();
                TipoSeguro seguro = tipoSeguroDAO.findById(rs.getInt("id_seguro")).get();
                Cliente cliente = clienteDAO.findById(rs.getInt("id_cliente")).get();
                Usuario usuario = usuarioDAO.findById(rs.getInt("id_atendente")).get();
                pedido.setVeiculo(veiculo);
                pedido.setTipoSeguro(seguro);
                pedido.setCliente(cliente);
                pedido.setAtendente(usuario);
                pedidos.add(pedido);
            }
            rs.close();
            st.close();
        }
        catch(SQLException | PedidoLocacaoValidacaoException e){
            throw new DBException("Não foi possível recuperar a lista de Pedidos de Locação: " + e.getMessage());
        }
        return pedidos;
    }
    
    public Optional<PedidoLocacao> findById(Integer id) throws DBException {
        try{
            String sql = "SELECT FROM pedidos_locacao WHERE id_pedido = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            ClienteDAO clienteDAO = new ClienteDAO(database);
            TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
            VeiculoDAO veiculoDAO = new VeiculoDAO(database);
            
            PedidoLocacao pedido = null;
            if(rs.next()){
                pedido = new PedidoLocacao();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setIdSaida(rs.getInt("id_saida"));
                pedido.setIdDevolucao(rs.getInt("id_devolucao"));
                pedido.setDevolucaoEsperada(rs.getDate("devolucao_esperada").toLocalDate());
                pedido.setFinalizado(rs.getBoolean("finalizado"));
                pedido.setValorTotal(new BigDecimal(rs.getDouble("valor_total")));
                pedido.setMeioPagamento(MeioPagamento.setInteiro(rs.getInt("forma_de_pagamento")));
                
                Veiculo veiculo = veiculoDAO.findByPlaca(rs.getString("placa")).get();
                TipoSeguro seguro = tipoSeguroDAO.findById(rs.getInt("id_seguro")).get();
                Cliente cliente = clienteDAO.findById(rs.getInt("id_cliente")).get();
                Usuario usuario = usuarioDAO.findById(rs.getInt("id_atendente")).get();
                pedido.setVeiculo(veiculo);
                pedido.setTipoSeguro(seguro);
                pedido.setCliente(cliente);
                pedido.setAtendente(usuario);
            }
            rs.close();
            st.close();
            return Optional.ofNullable(pedido);
        }
        catch(SQLException | PedidoLocacaoValidacaoException e){
            throw new DBException("Não foi possível recuperar este Pedido de Locação: " + e.getMessage());
        }
    }
    
    public void create(PedidoLocacao pedido) throws DBException {
        try{
            String sql = "INSERT INTO pedidos_locacao VALUES(null,? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = database.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, pedido.getAtendente().getId());
            st.setInt(2, pedido.getCliente().getId());
            st.setInt(3, pedido.getTipoSeguro().getId());
            st.setString(4, pedido.getVeiculo().getPlaca());
            st.setInt(5, pedido.getIdSaida());
            st.setInt(6, pedido.getIdDevolucao());
            st.setDate(7, Date.valueOf(pedido.getDevolucaoEsperada()));
            st.setInt(8, pedido.getMeioPagamento().getNumero());
            st.setBoolean(9, pedido.getFinalizado());
            st.setDouble(10, pedido.getValorTotal().doubleValue());
            
            int linhas = st.executeUpdate();
            
            if(linhas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    pedido.setId(rs.getInt(1));
                }
                rs.close();
            }
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Não foi possível criar este Pedido de Locação: " + e.getMessage());
        }
    }
    
    public void update(PedidoLocacao pedido) throws DBException {
        try{
            String sql = "UPDATE pedidos_locacao SET "
                       + "id_pedido = ?, "
                       + "id_atendente = ?, "
                       + "id_cliente = ?, "
                       + "id_seguro = ?, "
                       + "placa = ?, "
                       + "id_saida = ?, "
                       + "id_devolucao = ?, "
                       + "devolucao_esperada = ?, "
                       + "forma_de_pagamento = ?, "
                       + "finalizado = ?, "
                       + "valor_total = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, pedido.getId());
            st.setInt(2, pedido.getAtendente().getId());
            st.setInt(3, pedido.getCliente().getId());
            st.setInt(4, pedido.getTipoSeguro().getId());
            st.setString(5, pedido.getVeiculo().getPlaca());
            st.setInt(6, pedido.getIdSaida());
            st.setInt(7, pedido.getIdDevolucao());
            st.setDate(8, Date.valueOf(pedido.getDevolucaoEsperada()));
            st.setInt(9, pedido.getMeioPagamento().getNumero());
            st.setBoolean(10, pedido.getFinalizado());
            st.setDouble(11, pedido.getValorTotal().doubleValue());
            
            int linhasAfetadas = st.executeUpdate();
            
            if(linhasAfetadas > 0){
                System.out.println("Linhas Afetadas: " + linhasAfetadas);
            }
        }
        catch(SQLException e){
            throw new DBException("Erro ao atualizar Pedido de Locação: " + e.getMessage());
        }
    }
    
    public void removeById(Integer id) throws DBException {
        try{
            String sql = "DELETE FROM pedidos_locacao WHERE id = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            
            int linhasAfetadas = st.executeUpdate();
            
            if(linhasAfetadas > 0){
                System.out.println("Linhas Afetadas: " + linhasAfetadas);
            }
        }
        catch(SQLException e){
            throw new DBException("Erro ao remover este Pedido de Locação: " + e.getMessage());
        }
    }
}
