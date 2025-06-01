/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
import com.fatec.atendimentolocalhost.model.enums.TipoUsuario;
import com.fatec.atendimentolocalhost.service.UsuarioService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Fabio
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
    private RadioButton rbGerente;
    
    @FXML
    private RadioButton rbAtendente;

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
        preencherCampos();
        preencherTabela();
    }

    @FXML
    public void preencherCampos() {
        tabelaUsuarios.setRowFactory(t -> {
            TableRow<Usuario> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Usuario usuario = row.getItem();
                    txtId.setText(String.valueOf(usuario.getId()));
                    txtNome.setText(usuario.getNome());
                    txtEmail.setText(usuario.getEmail());
                    alterarCampos();
                }
            });
            return row;
        });
    }

    @FXML
    private void preencherTabela() {

        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        colunaSituacao.setCellValueFactory(cellData -> {
            Boolean situacao = cellData.getValue().getAtivo();
            String situation = (situacao != null && situacao) ? "ATIVO" : "INATIVO";
            return new ReadOnlyStringWrapper(situation);
        });

        service = new UsuarioService();

        try {
            List<Usuario> usuarios = service.buscarUsuarios();
            ObservableList<Usuario> tbUsuarios = FXCollections.observableArrayList(usuarios);
            tabelaUsuarios.setItems(tbUsuarios);
        } catch (DBException e) {
            System.out.println("DEU PAU KKKK: " + e.getMessage());
        }
    }

    @FXML
    private void btnLimparCamposClick() {
        limparCampos();
    }

    @FXML
    private void btnCadastrarClick() {
        Usuario usuario = new Usuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText(), TipoUsuario.ATENDENTE);
        service = new UsuarioService();

        try {
            service.cadastrarUsuario(usuario);
        } catch (DBException e) {
            System.out.println("DEU PAU KKKK: " + e.getMessage());
        }
        System.out.println("cheguei aqui");
        tabelaUsuarios.refresh();
    }

    @FXML
    private void btnAtualizarClick() {
        Usuario usuario = new Usuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText(), TipoUsuario.ATENDENTE);
        usuario.setId(Integer.valueOf(txtId.getText()));

        service = new UsuarioService();

        try {
            service.cadastrarUsuario(usuario);
        } catch (DBException e) {
            System.out.println("DEU PAU KKKK: " + e.getMessage());
        }
        System.out.println("cheguei aqui");
        tabelaUsuarios.refresh();
    }

    @FXML
    private void alterarCampos() {
        txtId.setVisible(!txtId.isVisible());
        txtNome.setEditable(!txtNome.isEditable());
        txtEmail.setEditable(!txtEmail.isEditable());
        txtSenha.setVisible(!txtSenha.isVisible());
        txtConfirmarSenha.setVisible(!txtConfirmarSenha.isVisible());
        rbAtivo.setVisible(!rbAtivo.isVisible());
        rbInativo.setVisible(!rbInativo.isVisible());
        rbGerente.setVisible(!rbGerente.isVisible());
        rbAtendente.setVisible(!rbAtendente.isVisible());
        btnCadastrar.setVisible(!btnCadastrar.isVisible());
        btnAtualizar.setVisible(!btnAtualizar.isVisible());
        btnLimparCampos.setVisible(!btnLimparCampos.isVisible());
    }

    @FXML
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

    @FXML
    private void desabilitarAtivo() {
        rbAtivo.setSelected(false);
    }

    @FXML
    private void desabilitarInativo() {
        rbInativo.setSelected(false);
    }
}
