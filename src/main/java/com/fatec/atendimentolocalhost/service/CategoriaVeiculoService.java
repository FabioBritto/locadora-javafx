/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.CategoriaVeiculoDAO;
import com.fatec.atendimentolocalhost.model.entities.CategoriaVeiculo;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class CategoriaVeiculoService {
    
    Database database;
    
    public List<CategoriaVeiculo> buscarCategorias() throws DBException {
        try {
            database = new Database();
            CategoriaVeiculoDAO categoriaDAO = new CategoriaVeiculoDAO(database);
            return categoriaDAO.findAll();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
