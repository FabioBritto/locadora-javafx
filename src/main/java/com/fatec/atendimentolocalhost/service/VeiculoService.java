/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.VeiculoDAO;
import com.fatec.atendimentolocalhost.model.entities.Veiculo;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class VeiculoService {
    
    Database database;
    
    public List<Veiculo> buscarVeiculos() throws DBException {
        try {
            database = new Database();
            VeiculoDAO veiculoDAO = new VeiculoDAO(database);
            return veiculoDAO.findAll();
        } catch (SQLException e) {
            throw new DBException("Erro ao buscar lista de Veículos");
        }
    }
}
