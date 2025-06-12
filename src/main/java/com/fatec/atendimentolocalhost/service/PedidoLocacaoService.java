/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.PedidoLocacaoDAO;
import com.fatec.atendimentolocalhost.model.entities.PedidoLocacao;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class PedidoLocacaoService {
    
     private Database database;

    public PedidoLocacaoService() {
        database = new Database();
    }
    
     public void criarLocacao(PedidoLocacao pedido) throws DBException {
         try{
             PedidoLocacaoDAO dao = new PedidoLocacaoDAO(database);
             dao.create(pedido);
         }catch(SQLException e){
             throw new DBException("Erro ao cadastrar pedido!" + e.getMessage());
         }
     }
}
