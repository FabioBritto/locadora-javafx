/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe responsável pelo acesso ao Banco de Dados.
 * Para garantir flexibilidade entre todos os desenvolvedores, as credenciais
 * de acesso ao Banco foram configuradas em variáveis de ambiente
 * 
 * @author Fabio
 */
public class Database {
    
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USERNAME = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    
    private static Connection conn = null;
    
    public Database(){
        try{
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Conexão bem-sucedida");
        }
        catch(SQLException e){
            System.out.println("Erro ao conectar com banco.\nErro: " + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
}
