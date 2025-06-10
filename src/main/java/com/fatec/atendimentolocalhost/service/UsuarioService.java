/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.CampoVazioException;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.UsuarioDAO;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
import com.fatec.atendimentolocalhost.util.Verificar;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class UsuarioService {

    private Database database;

    public void cadastrarUsuario(Usuario usuario) throws DBException {
        try {
            if (!Verificar.todosAtributosPreenchidos(usuario, "getId")){
                throw new CampoVazioException("Atenção. Preencha todos os dados.");
            }   
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            usuarioDAO.create(usuario);
            
        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar usuário");
        }

    }

    public List<Usuario> buscarUsuarios() throws DBException {
        try {
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            return usuarioDAO.findAll();

        } catch (SQLException e) {
            throw new DBException("Erro ao recuperar lista de Usuários");
        }

    }

    public void atualizarUsuario(Usuario usuario) throws DBException {
        try {
            if(!Verificar.todosAtributosPreenchidos(usuario)){
                throw new CampoVazioException("Atenção. Preencha todos os dados de Usuário");
            }
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            usuarioDAO.update(usuario);
        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar Usuário");
        }
    }
    
    public Usuario buscarPorId(Integer id) throws DBException {
        try{
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            Usuario usuario = usuarioDAO.findById(id).get();
            return usuario;
        }
        catch(SQLException e){
            throw new DBException("Erro ao buscar Usuário por ID");
        }
    }
}
