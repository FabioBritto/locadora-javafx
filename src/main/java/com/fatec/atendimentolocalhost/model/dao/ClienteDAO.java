/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.model.dao;

// Importações
import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.ClienteValidacaoException;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.Cliente;
import com.fatec.atendimentolocalhost.model.entities.PedidoLocacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Classe contendo o CRUD relacionado a tabela clientes
 *
 * @author Alberto
 */
public class ClienteDAO {

    private final Database database;

    public ClienteDAO(Database database) {
        this.database = database;
    }

    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new LinkedList<>();

        String sql = "SELECT * FROM clientes;";
        PreparedStatement ps = database.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        PedidoLocacaoDAO pedidoDAO = new PedidoLocacaoDAO(database);

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCep(rs.getString("cep"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getString("numero"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setAtivo(rs.getBoolean("ativo"));

            List<PedidoLocacao> pedidos = pedidoDAO.findByCliente(cliente);
            cliente.setLocacoes(pedidos);
            clientes.add(cliente);
        }
        return clientes;
    }

    public Optional<Cliente> findByCpf(String cpf) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE cpf=?;";
        PreparedStatement ps = database.getConnection().prepareStatement(sql);
        ps.setString(1, cpf);
        ResultSet rs = ps.executeQuery();

        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCep(rs.getString("cep"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getString("numero"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setAtivo(rs.getBoolean("ativo"));
        }
        return Optional.ofNullable(cliente);

    }

    public Optional<Cliente> findById(Integer id) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE id = ?";
        PreparedStatement st = database.getConnection().prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCep(rs.getString("cep"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getString("numero"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setAtivo(rs.getBoolean("ativo"));
        }
        rs.close();
        st.close();
        return Optional.ofNullable(cliente);
    }

    public void createCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (cpf, nome, email, cep, rua, numero,"
                + "complemento, bairro, cidade, estado, dataNascimento,"
                + "telefone, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = database.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, cliente.getCpf());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getEmail());
        ps.setString(4, cliente.getCep());
        ps.setString(5, cliente.getRua());
        ps.setString(6, cliente.getNumero());
        ps.setString(7, cliente.getComplemento());
        ps.setString(8, cliente.getBairro());
        ps.setString(9, cliente.getCidade());
        ps.setString(10, cliente.getEstado());
        ps.setDate(11, java.sql.Date.valueOf(cliente.getDataNascimento()));
        ps.setString(12, cliente.getTelefone());
        ps.setBoolean(13, cliente.getAtivo());

        int linhasAfetadas = ps.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }
        }
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET "
                + "cpf = ?, "
                + "nome = ?, "
                + "email = ?, "
                + "cep = ?, "
                + "rua = ?, "
                + "numero = ?, "
                + "complemento = ?, "
                + "bairro = ?, "
                + "cidade = ?, "
                + "estado = ?, "
                + "dataNascimento = ?, "
                + "telefone = ?, "
                + "ativo = ? "
                + "WHERE id_cliente = ?;";

        PreparedStatement ps = database.getConnection().prepareStatement(sql);

        ps.setString(1, cliente.getCpf());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getEmail());
        ps.setString(4, cliente.getCep());
        ps.setString(5, cliente.getRua());
        ps.setString(6, cliente.getNumero());
        ps.setString(7, cliente.getComplemento());
        ps.setString(8, cliente.getBairro());
        ps.setString(9, cliente.getCidade());
        ps.setString(10, cliente.getEstado());
        ps.setDate(11, java.sql.Date.valueOf(cliente.getDataNascimento()));
        ps.setString(12, cliente.getTelefone());
        ps.setBoolean(13, cliente.getAtivo());
        ps.setInt(14, cliente.getId());

        int linhasAfetadas = ps.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Linhas afetadas: " + linhasAfetadas);
        }
    }

    public void deleteClienteByCpf(String cpf) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cpf=?;";
        PreparedStatement ps = database.getConnection().prepareStatement(sql);
        ps.setString(1, cpf);
        int linhasAfetadas = ps.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Linhas afetadas: " + linhasAfetadas);
        }
    }
}
