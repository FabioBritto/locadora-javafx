/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.TipoSeguroDAO;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class TipoSeguroService {
    
    Database database;
    
    public List<TipoSeguro> buscarSeguros() throws DBException {
        try{
            database = new Database();
            TipoSeguroDAO seguroDAO = new TipoSeguroDAO(database);
            List<TipoSeguro> seguros = seguroDAO.findAll();
            return seguros;
        }
        catch(SQLException e){
            throw new DBException("Erro ao buscar Lista de Seguros");
        }
    }
}
