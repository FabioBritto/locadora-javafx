/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.exceptions.LoginValidacaoException;
import com.fatec.atendimentolocalhost.model.entities.Usuario;
import com.fatec.atendimentolocalhost.model.enums.TipoUsuario;
import com.fatec.atendimentolocalhost.service.UsuarioService;
import com.fatec.atendimentolocalhost.util.Alerts;
import com.fatec.atendimentolocalhost.util.Constraints;
import com.fatec.atendimentolocalhost.util.Verificar;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    private Label lblNome, lblEmail, lblSenha, lblSecao;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnNovoUsuario;

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
        inicializarConstraints();
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
                    if (usuario.getAtivo()) {
                        desabilitarInativo();
                    } else {
                        desabilitarAtivo();
                    }
                    visibleEdicao();
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
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnNovoUsuarioClick() {
        limparCampos();
        
    }

    @FXML
    private void btnCadastrarClick() {

        Usuario usuario = criarUsuario();
        if (!Verificar.todosAtributosPreenchidos(usuario, "getId")) {
            Alerts.mostrarAlerta("ERRO", null, "Para continuar, preencha todos os campos corretamente.", Alert.AlertType.ERROR);
        } else {
            Optional<ButtonType> resposta = Alerts.pedirConfirmacao("CONFIRMAÇÃO", "Deseja cadastrar este usuário?");
            if (resposta.get() == ButtonType.OK) {
                service = new UsuarioService();
                try {
                    service.cadastrarUsuario(usuario);
                    Alerts.mostrarAlerta("CONCLUÍDO", null, "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }
        tabelaUsuarios.refresh();
    }

    private Usuario criarUsuario() {
        esconderLabels();

        Usuario usuario = new Usuario();
        try {
            usuario.setNome(txtNome.getText());
        } catch (LoginValidacaoException e) {
            lblNome.setText(e.getMessage());
            lblNome.setVisible(true);
        }

        try {
            usuario.setEmail(txtEmail.getText());
        } catch (LoginValidacaoException e) {
            lblEmail.setText(e.getMessage());
            lblEmail.setVisible(true);
        }

        try {
            
            if (!txtSenha.getText().equals(txtConfirmarSenha.getText())) {
                throw new LoginValidacaoException("As senhas não coincidem. Tente novamente");
            }
            usuario.setSenha(txtSenha.getText());
        } catch (LoginValidacaoException e) {
            lblSenha.setText(e.getMessage());
            lblSenha.setVisible(true);
        }

        if (rbAtendente.isSelected()) {
            usuario.setTipoUsuario(TipoUsuario.ATENDENTE);
        } else {
            usuario.setTipoUsuario(TipoUsuario.GERENTE);
        }
        usuario.setAtivo(true);
        return usuario;
    }

    @FXML
    private void btnAtualizarClick() {
        Optional<ButtonType> resposta = Alerts.pedirConfirmacao("CONFIRMAÇÃO", "Deseja atualizar este usuário?");
        if (resposta.get() == ButtonType.OK) {
            try {
                service = new UsuarioService();
                Usuario usuario = service.buscarPorId(Integer.parseInt(txtId.getText()));
                usuario.setNome(txtNome.getText());
                usuario.setEmail(txtEmail.getText());
                usuario.setAtivo(rbAtivo.isSelected());

                service.atualizarUsuario(usuario);
                Alerts.mostrarAlerta("CONCLUÍDO", null, "Usuário atualizado com sucesso!", Alert.AlertType.INFORMATION);
            } catch (DBException e) {
                System.out.println(e.getMessage());
            }
            tabelaUsuarios.refresh();
        }
    }

    @FXML
    private void visibleCriacao() {
        lblSecao.setText("NOVO USUÁRIO");
        txtId.setVisible(false);
        txtNome.setEditable(true);
        txtEmail.setEditable(true);
        txtSenha.setVisible(true);
        txtConfirmarSenha.setVisible(true);
        rbAtivo.setVisible(false);
        rbInativo.setVisible(false);
        rbGerente.setVisible(true);
        rbAtendente.setVisible(true);
        btnCadastrar.setVisible(true);
        btnAtualizar.setVisible(false);
        btnNovoUsuario.setVisible(false);
    }

    @FXML
    private void visibleEdicao() {
        lblSecao.setText("EDITAR USUÁRIO");
        txtId.setVisible(true);
        txtNome.setEditable(false);
        txtEmail.setEditable(false);
        txtSenha.setVisible(false);
        txtConfirmarSenha.setVisible(false);
        rbAtivo.setVisible(true);
        rbInativo.setVisible(true);
        rbGerente.setVisible(false);
        rbAtendente.setVisible(false);
        btnCadastrar.setVisible(false);
        btnAtualizar.setVisible(true);
        btnNovoUsuario.setVisible(true);
    }

    @FXML
    private void limparCampos() {
        visibleCriacao();
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
        rbInativo.setSelected(true);
    }

    @FXML
    private void desabilitarInativo() {
        rbInativo.setSelected(false);
        rbAtivo.setSelected(true);
    }

    @FXML
    private void desabilitarGerente() {
        rbGerente.setSelected(false);
    }

    @FXML
    private void desabilitarAtendente() {
        rbAtendente.setSelected(false);
    }

    private void inicializarConstraints() {
        Constraints.setTextFieldWithoutSpace(txtNome);
    }
    
    private void esconderLabels(){
        lblSenha.setVisible(false);
        lblEmail.setVisible(false);
        lblNome.setVisible(false);
    }
}
