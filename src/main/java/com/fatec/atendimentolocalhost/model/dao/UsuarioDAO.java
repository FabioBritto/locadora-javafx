/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dao;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
import com.fatec.atendimentolocalhost.model.enums.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Classe de acesso a banco para a classe Usuario.
 *
 * @author Fabio
 */
public class UsuarioDAO {

    private final Database database;

    public UsuarioDAO(Database database) {
        this.database = database;
    }

    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new LinkedList<>();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement st = database.getConnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
            usuario.setAtivo(rs.getBoolean("ativo"));
            usuarios.add(usuario);
        }
        rs.close();
        st.close();

        return usuarios;
    }

    public List<Usuario> findByNome(String nome) throws SQLException {
        List<Usuario> usuarios = new LinkedList<>();
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        PreparedStatement st = database.getConnection().prepareStatement(sql);
        st.setString(1, nome);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
            usuario.setAtivo(rs.getBoolean("ativo"));
            usuarios.add(usuario);
        }
        st.close();
        rs.close();
        return usuarios;
    }

    public Optional<Usuario> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        PreparedStatement st = database.getConnection().prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        Usuario usuario = null;
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
            usuario.setAtivo(rs.getBoolean("ativo"));
        }
        st.close();
        rs.close();
        return Optional.ofNullable(usuario);
    }

    public void create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES (?,?,?,?)";
        PreparedStatement st = database.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getSenha());
        st.setInt(4, usuario.getTipoUsuario().getNumero());

        int linhas = st.executeUpdate();

        if (linhas > 0) {
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
            rs.close();
        }
        st.close();
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, tipo_usuario = ?, ativo = ? WHERE id_usuario = ?";
        PreparedStatement st = database.getConnection().prepareStatement(sql);

        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getSenha());
        st.setInt(4, usuario.getTipoUsuario().getNumero());
        st.setBoolean(5, usuario.getAtivo());
        st.setInt(6, usuario.getId());

        int linhasAfetadas = st.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Linhas Afetadas: " + linhasAfetadas);
        }
    }

    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        PreparedStatement st = database.getConnection().prepareStatement(sql);

        st.setInt(1, id);

        int linhasAfetadas = st.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Linhas Afetadas: " + linhasAfetadas);
        }
    }
}
