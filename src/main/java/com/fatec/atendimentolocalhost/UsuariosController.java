/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.model.entities.Usuario;
import com.fatec.atendimentolocalhost.model.enums.TipoUsuario;
import com.fatec.atendimentolocalhost.service.UsuarioService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samsung
 */
public class UsuariosController implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnLimparCampos;

    @FXML
    private RadioButton rbAtivo;

    @FXML
    private RadioButton rbInativo;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> colunaID;

    @FXML
    private TableColumn<Usuario, TipoUsuario> colunaTipoUsuario;

    @FXML
    private TableColumn<Usuario, String> colunaNome;

    @FXML
    private TableColumn<Usuario, String> colunaEmail;

    @FXML
    private TableColumn<Usuario, String> colunaSituacao;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtConfirmarSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    UsuarioService service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //preencherCampos();
        //preencherTabela();
    }

//    public void preencherCampos(){
//        tabelaUsuarios.setRowFactory(t -> {
//            TableRow<Usuario> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if(event.getClickCount() == 2 && !row.isEmpty()) {
//                    Usuario usuario = row.getItem();
//                    txtNome.setText(usuario.getNome());
//                    txtEmail.setText(usuario.getEmail());
//                    alterarCampos();
//                }
//            });
//            return row;
//        });
//    }
    
    @FXML
    private void preencherTabela() {

        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("ativo"));

        service = new UsuarioService();

        List<Usuario> usuarios = service.buscarUsuarios();
        ObservableList<Usuario> tbUsuarios = FXCollections.observableArrayList(usuarios);
        tabelaUsuarios.setItems(tbUsuarios);

    }

    @FXML
    private void btnLimparCamposClick() {
        limparCampos();
    }

    @FXML
    private void btnCadastrarClick() {
        //Usuario usuario = new Usuario(null, txtNome.getText(), txtEmail.getText(), txtSenha.getText(), TipoUsuario.ATENDENTE);
        
        
    }

    @FXML
    private void btnAtualizarClick() {
        Usuario usuario = new Usuario();
        usuario.setId(Integer.valueOf(txtId.getText()));
        
        service = new UsuarioService();
        service.cadastrarUsuario(usuario);
    }

    private void alterarCampos() {
        txtId.setVisible(!txtId.isVisible());
        txtNome.setVisible(!txtNome.isVisible());
        txtEmail.setVisible(!txtEmail.isVisible());
        txtSenha.setVisible(!txtSenha.isVisible());
        txtConfirmarSenha.setVisible(!txtConfirmarSenha.isVisible());
        rbAtivo.setVisible(!rbAtivo.isVisible());
        rbInativo.setVisible(!rbInativo.isVisible());
        btnCadastrar.setVisible(!btnCadastrar.isVisible());
        btnAtualizar.setVisible(!btnAtualizar.isVisible());
        btnLimparCampos.setVisible(!btnLimparCampos.isVisible());
    }

    private void limparCampos() {
        alterarCampos();
        txtId.setText(null);
        txtNome.setText(null);
        txtEmail.setText(null);
        txtSenha.setText(null);
        txtConfirmarSenha.setText(null);
        rbAtivo.setSelected(false);
        rbInativo.setSelected(false);
    }
}
