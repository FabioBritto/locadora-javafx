/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.atendimentolocalhost.util;

import com.fatec.atendimentolocalhost.model.dto.CepDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * Classe destinada à conexão com a API de CEP disponível em:
 *              https://viacep.com.br
 * O objetivo é capturar os dados da API e guardar em um objeto DTO
 * Possui duas constantes que identificam o endereço da API e o tipo de dado que quero de retorno
 * @author Fabio
 */
public class CepApi {
    
    private static final String URL = "https://viacep.com.br/ws/";
    private static final String TIPO = "/json";
    
    /**
     * Objeto que recupera o endereço a partir do CEP fornecido com o uso da biblioteca
     * GSON
     * 
     * @param cep
     * @return CepDTO
     * @throws IOException
     * @throws InterruptedException 
     */
    public static CepDTO encontrarEndereco(String cep) throws IOException, InterruptedException {
        
        String endereco = URL + cep + TIPO;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String json = response.body();
        
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        return gson.fromJson(json, CepDTO.class);
    }
}
