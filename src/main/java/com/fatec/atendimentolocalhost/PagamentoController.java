/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fatec.atendimentolocalhost;

import com.fatec.atendimentolocalhost.util.PedidoHolder;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TextField txtDataNascimento;
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
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCampos();
        configurarBotoes();
    }

   
    public void configurarBotoes(){
        btnVoltar.setOnAction(e -> {
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("novalocacao.fxml"));
            VBox v = loader.load();
            BorderPane borderPane = (BorderPane) vBoxPrincipal.getParent();
            borderPane.setCenter(v); 
            }catch(IOException error){
                error.printStackTrace();
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
        txtCpf.setText("");
        txtDataNascimento.setText("");
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

}
