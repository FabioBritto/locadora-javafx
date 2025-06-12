/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.CampoVazioException;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.TipoSeguroDAO;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import com.fatec.atendimentolocalhost.util.Verificar;
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
    
    public void cadastrarSeguro(TipoSeguro seguro) throws DBException {
        try {
            if(!Verificar.todosAtributosPreenchidos(seguro, "getId")){
                throw new CampoVazioException("Atenção. Preencha todos os dados necessários");
            } 
            database = new Database();
            TipoSeguroDAO seguroDAO = new TipoSeguroDAO(database);
            seguroDAO.create(seguro);
        }
        catch(SQLException e){
            throw new DBException("Erro ao criar novo seguro");       
        }
    }
    
    public void atualizarSeguro(TipoSeguro seguro) throws DBException {
        try {
            if(!Verificar.todosAtributosPreenchidos(seguro)){
                throw new CampoVazioException("Atenção. Preencha todos os dados necessários");
            } 
            database = new Database();
            TipoSeguroDAO seguroDAO = new TipoSeguroDAO(database);
            seguroDAO.update(seguro);
        }
        catch(SQLException e){
            throw new DBException("Erro ao atualizar seguro: " + e.getMessage());       
        }
    }
}
