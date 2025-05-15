/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.model.dao.CategoriaVeiculoDAO;
import com.fatec.atendimentolocalhost.model.dao.ClienteDAO;
import com.fatec.atendimentolocalhost.model.dao.PedidoLocacaoDAO;
import com.fatec.atendimentolocalhost.model.dao.TipoSeguroDAO;
import com.fatec.atendimentolocalhost.model.dao.UsuarioDAO;
import com.fatec.atendimentolocalhost.model.dao.VeiculoDAO;

/**
 *
 * @author Fabio 
 */
public class Teste {
    
    private static Database database = new Database(true);
    private CategoriaVeiculoDAO categoriaVeiculoDAO = new CategoriaVeiculoDAO(database);
    private ClienteDAO clienteDAO = new ClienteDAO(database);
    private PedidoLocacaoDAO pedidoLocacaoDAO = new PedidoLocacaoDAO(database);
    private TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO(database);
    private UsuarioDAO usuarioDAO = new UsuarioDAO(database);
    private VeiculoDAO veiculoDAO = new VeiculoDAO(database);
    
    public static void main(String[] args){
        
    }

    public static void criar(){
       // Usuario usuario = new Usuario("")
    }
}
