/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.UsuarioDAO;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
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
            if (usuario != null) {
                database = new Database();
                UsuarioDAO usuarioDAO = new UsuarioDAO(database);
                usuarioDAO.create(usuario);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    public List<Usuario> buscarUsuarios() throws DBException {
        try {
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            return usuarioDAO.findAll();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    public void atualizarUsuario(Usuario usuario) throws DBException {
        try {
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
            usuarioDAO.update(usuario);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }
}
