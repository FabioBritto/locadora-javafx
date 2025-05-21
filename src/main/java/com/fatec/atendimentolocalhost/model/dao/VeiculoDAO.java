/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dao;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.CategoriaVeiculo;
import com.fatec.atendimentolocalhost.model.entities.Veiculo;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pelo acesso ao Banco de Dados pela classe de Veículo.
 * 
 * @author Fabio
 */
public class VeiculoDAO {
    
    private final Database database;
    
    public VeiculoDAO(Database database){
        this.database = database;
    }
    
    public List<Veiculo> findAll() throws DBException {
        List<Veiculo> veiculos = new LinkedList<>();
        try{
            String sql = "SELECT * FROM veiculos";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                veiculo.setPrecoBase(BigDecimal.valueOf(rs.getDouble("preco_base")));
                CategoriaVeiculoDAO categoriaDAO = new CategoriaVeiculoDAO(database);
                Optional<CategoriaVeiculo> categoria = categoriaDAO.findById(rs.getInt("id_categoria"));
                veiculo.setCategoria(categoria.get());
                veiculos.add(veiculo);
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar os Veículos: " + e.getMessage());
        }
        return veiculos;
    }
    
    public List<Veiculo> findByMarca(String marca) throws DBException {
        List<Veiculo> veiculos = new LinkedList<>();
        try{
            String sql = "SELECT * FROM veiculos WHERE marca = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setString(1, marca);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                veiculo.setPrecoBase(BigDecimal.valueOf(rs.getDouble("preco_base")));
                CategoriaVeiculoDAO categoriaDAO = new CategoriaVeiculoDAO(database);
                Optional<CategoriaVeiculo> categoria = categoriaDAO.findById(rs.getInt("id_categoria"));
                veiculo.setCategoria(categoria.get());
                veiculos.add(veiculo);
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar os Veículos: " + e.getMessage());
        }
        return veiculos;
    }
    
    public List<Veiculo> findByModelo(String modelo) throws DBException {
        List<Veiculo> veiculos = new LinkedList<>();
        try{
            String sql = "SELECT * FROM veiculos WHERE marca = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setString(1, modelo);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                veiculo.setPrecoBase(BigDecimal.valueOf(rs.getDouble("preco_base")));
                CategoriaVeiculoDAO categoriaDAO = new CategoriaVeiculoDAO(database);
                Optional<CategoriaVeiculo> categoria = categoriaDAO.findById(rs.getInt("id_categoria"));
                veiculo.setCategoria(categoria.get());
                veiculos.add(veiculo);
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar os Veículos: " + e.getMessage());
        }
        return veiculos;
    }
    
    public List<Veiculo> findByCategoria(CategoriaVeiculo categoria) throws DBException {
        List<Veiculo> veiculos = new LinkedList<>();
        try{
            String sql = "SELECT * FROM veiculos WHERE id_categoria = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setInt(1, categoria.getId());
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                veiculo.setPrecoBase(BigDecimal.valueOf(rs.getDouble("preco_base")));
                /*
                Neste caso, eu aproveito o objeto categoria para economizar consultas ao banco
                */
                veiculo.setCategoria(categoria);
                veiculos.add(veiculo);
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar os Veículos: " + e.getMessage());
        }
        return veiculos;
    }
    
    public Optional<Veiculo> findByPlaca(String placa) throws DBException {
        try{
            String sql = "SELECT * FROM veiculos WHERE placa = ?";
            PreparedStatement st = database.getConnection().prepareStatement(sql);
            st.setString(1, placa);
            ResultSet rs = st.executeQuery();
            
            Veiculo veiculo = null;
            if(rs.next()){
                veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                veiculo.setPrecoBase(BigDecimal.valueOf(rs.getDouble("preco_base")));
                CategoriaVeiculoDAO categoriaDAO = new CategoriaVeiculoDAO(database);
                Optional<CategoriaVeiculo> categoria = categoriaDAO.findById(rs.getInt("id_categoria"));
                veiculo.setCategoria(categoria.get());
            }
            rs.close();
            st.close();
            return Optional.ofNullable(veiculo);
        }
        catch(SQLException e){
            throw new DBException("Não foi possível buscar o Veículo: " + e.getMessage());
        }
    }
}
