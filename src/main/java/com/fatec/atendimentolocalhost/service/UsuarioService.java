/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.model.dao.UsuarioDAO;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class UsuarioService {

    private Database database;

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario != null) {
            database = new Database();
            UsuarioDAO usuarioDAO = new UsuarioDAO(database);
//            usuarioDAO.create(usuario);
        }

    }

    public List<Usuario> buscarUsuarios() {
        database = new Database();
        UsuarioDAO usuarioDAO = new UsuarioDAO(database);
        return null;
        //return usuarioDAO.findAll();
    }

    public void atualizarUsuario(Usuario usuario) {;
        database = new Database();
        UsuarioDAO usuarioDAO = new UsuarioDAO(database);
//        usuarioDAO.update(usuario);
    }
}
