/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dao;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.exceptions.TipoSeguroValidacaoException;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pelo acesso ao Banco de Dados pela classe de TipoSeguro.
 * @author Fabio
 */
public class TipoSeguroDAO {
    private final Database database;
    
    public TipoSeguroDAO(Database database){
        this.database = database;
    }
    
    public List<TipoSeguro> findAll() throws DBException {
        List<TipoSeguro> seguros = new LinkedList<>();
        try{
            String sql = "SELECT * FROM tipos_seguro";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                TipoSeguro tipoSeguro = new TipoSeguro();
                tipoSeguro.setId(rs.getInt("id_seguro"));
                tipoSeguro.setNome(rs.getString("nome"));
                tipoSeguro.setDescricao(rs.getString("descricao"));
                tipoSeguro.setTaxa(new BigDecimal(rs.getString("taxa")));
                seguros.add(tipoSeguro);
            }
            rs.close();
            st.close();
        }
        catch(SQLException | TipoSeguroValidacaoException e){
            throw new DBException("Erro ao buscar Tipos de Seguro: " + e.getMessage());
        }
        return seguros;
    }
    
    public Optional<TipoSeguro> findById(Integer id) throws DBException {
        try{
            String sql = "SELECT FROM tipos_seguro WHERE id_seguro = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            TipoSeguro tipoSeguro = null;
            if(rs.next()){
                tipoSeguro = new TipoSeguro();
                tipoSeguro.setId(rs.getInt("id_seguro"));
                tipoSeguro.setNome(rs.getString("nome"));
                tipoSeguro.setDescricao(rs.getString("descricao"));
                tipoSeguro.setTaxa(new BigDecimal(rs.getString("taxa")));
            }
            st.close();
            rs.close();
            return Optional.ofNullable(tipoSeguro);
        }
        catch(SQLException | TipoSeguroValidacaoException e){
            throw new DBException("Erro ao buscar Tipo de Seguro: " + e.getMessage());
        }
    }
    
    public void create(TipoSeguro tipoSeguro) throws DBException {
        try{
            String sql = "INSERT INTO tipos_seguro VALUES (null,?,?,?)";
            PreparedStatement st = database.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, tipoSeguro.getNome());
            st.setString(2, tipoSeguro.getDescricao());
            st.setDouble(3, tipoSeguro.getTaxa().doubleValue());
            
            
            int linhas = st.executeUpdate();
            
            if(linhas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    tipoSeguro.setId(rs.getInt(1));
                }
                rs.close();
            }
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Erro ao criar Tipo de Seguro: " + e.getMessage());
        }
    }
    
    public void update(TipoSeguro tipoSeguro) throws DBException {
        try{
            String sql = "UPDATE tipos_seguro SET id_seguro = ?, nome = ?, descricao = ?, taxa = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            
            st.setInt(1, tipoSeguro.getId());
            st.setString(2, tipoSeguro.getNome());
            st.setString(3, tipoSeguro.getDescricao());
            st.setDouble(4, tipoSeguro.getTaxa().doubleValue());
            
            int linhasAfetadas = st.executeUpdate();
            
            if(linhasAfetadas > 0){
                System.out.println("Linhas Afetadas: " + linhasAfetadas);
            }
        }
        catch(SQLException e){
            throw new DBException("Erro ao atualizar Tipo do Seguro: " + e.getMessage());
        }
    }
    
    public void removeById(Integer id) throws DBException {
        try{
            String sql = "DELETE FROM tipos_seguro WHERE id_seguro = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            
            st.setInt(1,id);
            
            int linhasAfetadas = st.executeUpdate();
            
            if(linhasAfetadas > 0){
                System.out.println("Linhas Afetadas: " + linhasAfetadas);
            }
            
        }
        catch(SQLException e){
            throw new DBException("Erro ao remover Tipo de Seguro: " + e.getMessage());
        }
    }
}
