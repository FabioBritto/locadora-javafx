/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.exceptions.ClienteValidacaoException;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.model.entities.Cliente;
import com.fatec.atendimentolocalhost.service.ClienteService;
import com.fatec.atendimentolocalhost.util.PedidoHolder;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alber
 */
public class PagamentoController implements Initializable {

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtValorBase;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtCategoria;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextArea txtDescricaoSeguro;
    @FXML
    private TextField txtCpf;
    @FXML
    private DatePicker pkDataNascimento;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtNumero;
    @FXML
    private Label lblTotalSeguro;
    @FXML
    private Label lblTotalVeiculo;
    @FXML
    private Label lblTotalGeral;
    @FXML
    private Label lblDataDevolucao;
    @FXML
    private Button btnFinalizar;
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnLimparCampos;

    @FXML
    private Button btnBuscar;

    private ClienteService clienteService = new ClienteService();

    private Boolean clienteEncontradoNaBusca = Boolean.FALSE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCampos();
        configurarBotoes();
        if (PedidoHolder.getInstance().getPedido().getCliente() != null) {
            preencherCamposCliente();
        }

    }

    public void configurarBotoes() {
        btnVoltar.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("novalocacao.fxml"));
                VBox v = loader.load();
                BorderPane borderPane = (BorderPane) vBoxPrincipal.getParent();
                borderPane.setCenter(v);
            } catch (IOException error) {
                error.printStackTrace();
            }
        });

        btnBuscar.setOnAction(e -> {
            try {
                Optional<Cliente> c = clienteService.buscarClientePorCPF(txtCpf.getText());
                if (c.isPresent()) {
                    Cliente cliente = c.get();
                    PedidoHolder.getInstance().getPedido().setCliente(cliente);
                    clienteEncontradoNaBusca = Boolean.TRUE;
                    preencherCamposCliente();
                    desabilitarCamposCliente();
                    txtCpf.setDisable(true);
                    btnBuscar.setDisable(true);
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("Cliente não encontrado!");
                    alert.showAndWait();
                    btnEditar.setDisable(true);
                }
            } catch (DBException error) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Erro ao buscar cliente!");
                alert.showAndWait();
            }

        });

        btnEditar.setOnAction(e -> {
            if (btnEditar.getText().equals("Editar")) {
                habilitarCamposCliente();
                btnEditar.setText("Cancelar Edição");
                txtCpf.setDisable(true);
                btnBuscar.setDisable(true);
                pkDataNascimento.setDisable(true);
            } else {
                btnEditar.setText("Editar");
                desabilitarCamposCliente();
                preencherCamposCliente();
            }
        });
        btnLimparCampos.setOnAction(e -> {
            limparCamposCliente();
            habilitarCamposCliente();
            btnBuscar.setDisable(false);
            txtCpf.setDisable(false);
            txtCpf.setText("");
            pkDataNascimento.setDisable(false);
            btnEditar.setDisable(true);
            btnEditar.setText("Editar");
        });

        btnFinalizar.setOnAction(e -> {
            if (!todosCamposPreenchidos()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Faltam dados do cliente!");
                alert.showAndWait();
            } else {
                try {
                    Cliente cliente = new Cliente();
                    cliente.setAtivo(Boolean.TRUE);
                    cliente.setBairro(txtBairro.getText());
                    cliente.setCep(txtCep.getText());
                    cliente.setCidade(txtCidade.getText());
                    cliente.setComplemento(txtComplemento.getText());
                    cliente.setCpf(txtCep.getText());
                    cliente.setDataNascimento(pkDataNascimento.getValue());
                    cliente.setEmail(txtEmail.getText());
                    cliente.setEstado(txtEstado.getText());
                    cliente.setNome(txtNome.getText());
                    cliente.setNumero(txtNumero.getText());
                    cliente.setRua(txtRua.getText());
                    cliente.setTelefone(txtTelefone.getText());
                    
                    
                    
                } catch (ClienteValidacaoException clErro) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("Dados do cliente inválidos, verifique\nCPF,NOME, EMAIL, TELEFONE, CEP E DATA DE NASCIMENTO!");
                    alert.showAndWait();
                }
            }
        });
    }

    public void preencherCampos() {
        NumberFormat moedaBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        txtPlaca.setText(PedidoHolder.getInstance().getPedido().getVeiculo().getPlaca());
        txtCor.setText(PedidoHolder.getInstance().getPedido().getVeiculo().getCor());
        txtModelo.setText(PedidoHolder.getInstance().getPedido().getVeiculo().getModelo());
        txtValorBase.setText(moedaBR.format(PedidoHolder.getInstance().getPedido().getVeiculo().getPrecoBase()));
        txtMarca.setText(PedidoHolder.getInstance().getPedido().getVeiculo().getMarca());
        txtAno.setText(PedidoHolder.getInstance().getPedido().getVeiculo().getAno().toString());
        txtCategoria.setText(PedidoHolder.getInstance().getPedido().getVeiculo().getCategoria().toString());

        BigDecimal totalGeral = BigDecimal.ZERO;
        if (PedidoHolder.getInstance().getPedido().getDevolucaoEsperada() != null) {
            BigDecimal totalSeguro = calcularValorTotalSeguro(PedidoHolder.getInstance().getPedido().getTipoSeguro().getTaxa(), PedidoHolder.getInstance().getPedido().getDevolucaoEsperada());
            lblTotalSeguro.setText(moedaBR.format(totalSeguro));
            totalGeral = totalGeral.add(totalSeguro);
        }

        if (PedidoHolder.getInstance().getPedido().getDevolucaoEsperada() != null) {
            BigDecimal totalVeiculo = calcularValorTotalVeiculo(PedidoHolder.getInstance().getPedido().getTipoSeguro().getTaxa(), PedidoHolder.getInstance().getPedido().getDevolucaoEsperada());
            lblTotalVeiculo.setText(moedaBR.format(totalVeiculo));
            totalGeral = totalGeral.add(totalVeiculo);
        }
        lblTotalGeral.setText(moedaBR.format(totalGeral));
        lblDataDevolucao.setText(PedidoHolder.getInstance().getPedido().getDevolucaoEsperada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        txtDescricaoSeguro.setText(PedidoHolder.getInstance().getPedido().getTipoSeguro().getDescricao());
    }

    public void preencherCamposCliente() {
        txtCpf.setText(PedidoHolder.getInstance().getPedido().getCliente().getCpf());
        if (PedidoHolder.getInstance().getPedido().getCliente().getDataNascimento() != null) {
            pkDataNascimento.setValue(PedidoHolder.getInstance().getPedido().getCliente().getDataNascimento());
        } else {
            pkDataNascimento.setValue(null);
        }
        txtCidade.setText(PedidoHolder.getInstance().getPedido().getCliente().getCidade());
        txtNome.setText(PedidoHolder.getInstance().getPedido().getCliente().getNome());
        txtCep.setText(PedidoHolder.getInstance().getPedido().getCliente().getCep());
        txtEstado.setText(PedidoHolder.getInstance().getPedido().getCliente().getEstado());
        txtEmail.setText(PedidoHolder.getInstance().getPedido().getCliente().getEmail());
        txtRua.setText(PedidoHolder.getInstance().getPedido().getCliente().getRua());
        txtComplemento.setText(PedidoHolder.getInstance().getPedido().getCliente().getComplemento());
        txtTelefone.setText(PedidoHolder.getInstance().getPedido().getCliente().getTelefone());
        txtBairro.setText(PedidoHolder.getInstance().getPedido().getCliente().getBairro());
        txtNumero.setText(PedidoHolder.getInstance().getPedido().getCliente().getNumero());
        desabilitarCamposCliente();
    }

    public BigDecimal calcularValorTotalVeiculo(BigDecimal valorBaseVeiculo, LocalDate dataDevolução) {
        LocalDate hoje = LocalDate.now();
        Double dias = (double) ChronoUnit.DAYS.between(hoje, dataDevolução);

        BigDecimal total = valorBaseVeiculo.multiply(new BigDecimal(dias));

        return total;
    }

    public BigDecimal calcularValorTotalSeguro(BigDecimal valorTaxaSeguro, LocalDate dataDevolução) {
        LocalDate hoje = LocalDate.now();
        Double dias = (double) ChronoUnit.DAYS.between(hoje, dataDevolução);

        BigDecimal total = valorTaxaSeguro.multiply(new BigDecimal(dias));

        return total;
    }

    public void habilitarCamposCliente() {
        pkDataNascimento.setDisable(false);
        txtCidade.setDisable(false);
        txtNome.setDisable(false);
        txtCep.setDisable(false);
        txtEstado.setDisable(false);
        txtEmail.setDisable(false);
        txtRua.setDisable(false);
        txtComplemento.setDisable(false);
        txtTelefone.setDisable(false);
        txtBairro.setDisable(false);
        txtNumero.setDisable(false);

    }

    public void desabilitarCamposCliente() {
        pkDataNascimento.setDisable(true);
        txtCidade.setDisable(true);
        txtNome.setDisable(true);
        txtCep.setDisable(true);
        txtEstado.setDisable(true);
        txtEmail.setDisable(true);
        txtRua.setDisable(true);
        txtComplemento.setDisable(true);
        txtTelefone.setDisable(true);
        txtBairro.setDisable(true);
        txtNumero.setDisable(true);
        btnEditar.setDisable(false);

    }

    public void limparCamposCliente() {
        pkDataNascimento.setValue(null);
        txtCidade.setText("");
        txtNome.setText("");
        txtCep.setText("");
        txtEstado.setText("");
        txtEmail.setText("");
        txtRua.setText("");
        txtComplemento.setText("");
        txtTelefone.setText("");
        txtBairro.setText("");
        txtNumero.setText("");

    }

    private boolean todosCamposPreenchidos() {
        return pkDataNascimento.getValue() != null
                && !txtCidade.getText().trim().isEmpty()
                && !txtNome.getText().trim().isEmpty()
                && !txtCep.getText().trim().isEmpty()
                && !txtEstado.getText().trim().isEmpty()
                && !txtEmail.getText().trim().isEmpty()
                && !txtRua.getText().trim().isEmpty()
                && !txtTelefone.getText().trim().isEmpty()
                && !txtBairro.getText().trim().isEmpty()
                && !txtNumero.getText().trim().isEmpty()
                && !txtCpf.getText().trim().isEmpty();
    }

}
