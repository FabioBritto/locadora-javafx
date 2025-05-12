/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dao;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.CategoriaVeiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Fabio
 */
public class CategoriaVeiculoDAO {
    
    private final Database database;
    
    public CategoriaVeiculoDAO(Database database){
        this.database = database;
    }
    
    public List<CategoriaVeiculo> findAll() throws DBException {
        List<CategoriaVeiculo> categorias = new LinkedList<>();
        try {
            String sql = "SELECT * FROM categorias_veiculos";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                CategoriaVeiculo categoria = new CategoriaVeiculo();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar as Categorias de Veículos: " + e.getMessage());
        }
        return categorias;
    }
    
    public Optional<CategoriaVeiculo> findById(Integer id) throws DBException {
        try{
            String sql = "SELECT FROM categorias_veiculos WHERE id_categoria = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            CategoriaVeiculo categoria = null;
            if(rs.next()){
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            st.close();
            return Optional.ofNullable(categoria);
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar a Categoria de Veículo: " + e.getMessage());
        }
    }
}
