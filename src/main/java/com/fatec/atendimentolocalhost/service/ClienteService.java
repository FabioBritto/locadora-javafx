/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.service;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.CampoVazioException;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.dao.ClienteDAO;
import com.fatec.atendimentolocalhost.model.entities.Cliente;
import com.fatec.atendimentolocalhost.util.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Fabio
 */
public class ClienteService {

    private Database database;

    public ClienteService() {
        database = new Database();
    }

    public List<Cliente> buscarClientes() throws DBException {
        try {

            ClienteDAO clienteDAO = new ClienteDAO(database);
            List<Cliente> clientes = clienteDAO.findAll();
            return clientes;
        } catch (SQLException e) {
            throw new DBException("Erro ao buscar lista de Clientes");
        }
    }
    
     public Optional<Cliente> buscarClientePorCPF(String cpf) throws DBException {
        try {
            ClienteDAO clienteDAO = new ClienteDAO(database);
            return clienteDAO.findByCpf(cpf);
        } catch (SQLException e) {
            throw new DBException("Erro ao buscar Cliente");
        }
    }
    

    public void cadastrarCliente(Cliente cliente) throws DBException {
        try {
            if (!Verificar.todosAtributosPreenchidos(cliente, "getId")) {
                throw new CampoVazioException("Atenção. Preencha todos os dados de Cliente");
            }

            ClienteDAO clienteDAO = new ClienteDAO(database);
            clienteDAO.createCliente(cliente);
        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar Cliente");
        }
    }

    public void atualizarCliente(Cliente cliente) throws DBException {
        try {
            if (!Verificar.todosAtributosPreenchidos(cliente, "getId", "getComplemento")) {
                throw new CampoVazioException("Atenção. Preencha todos os dados de Cliente");
            }

            ClienteDAO clienteDAO = new ClienteDAO(database);
            clienteDAO.updateCliente(cliente);
        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar Cliente");
        }
    }
}
