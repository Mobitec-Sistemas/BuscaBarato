/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Serviços com os endereços
 * @author Fabio
 */
@Controller
public class EnderecoController {
    
    // Pesquisa Endereço
    // https://maps.googleapis.com/maps/api/geocode/json?place_id=ChIJF01dd5HM2JQR6l4hlti-1bk&key=AIzaSyDPbVO3kBAlAkfzmZVWqGokiPm65loumnc
    
    // Pesquisa por estabelecimento
    // https://maps.googleapis.com/maps/api/place/autocomplete/json?input=maxxi&language=pt-BR&types=establishment&key=AIzaSyDPbVO3kBAlAkfzmZVWqGokiPm65loumnc
    
    private static final String URL_API = "https://maps.googleapis.com";
    private static final String PATH_ENDERECO = "maps/api/geocode/json";
    private static final String PATH_ESTABELECIMENTO = "maps/api/place/autocomplete/json";
    private static final String KEY = "AIzaSyDPbVO3kBAlAkfzmZVWqGokiPm65loumnc";
        
    @Inject
    private Result result;
    
    /**
     * Retorna os dados do estabelecimento 
     * 
     * @param nome do estabelecimento
     */
    @Get("/endereco/estabelecimento/{nome}")
    public void GetEstabelecimento(String nome) {
        // Comunica com a API do google para retornar o endereco correto
        Client clienteHttp = ClientBuilder.newClient();
        WebTarget googleMaps = clienteHttp.target(URL_API).path(PATH_ESTABELECIMENTO)
                .queryParam("input", nome)
                .queryParam("language", "pt-BR")
                .queryParam("types", "establishment")
                .queryParam("key", KEY);
        Response response = googleMaps.request().get();
                
        Map retorno = response.readEntity(Map.class);
        
        result.use(Results.json()).from(retorno).serialize();        
    }
    
    @Get("/endereco/id/{placeId}")
    public void GetEndereco(String placeId)
    {
        // Comunica com a API do google para retornar o endereco correto
        Client clienteHttp = ClientBuilder.newClient();
        WebTarget googleMaps = clienteHttp.target(URL_API).path(PATH_ENDERECO)
                .queryParam("key", KEY)
                .queryParam("place_id", placeId);
        Response response = googleMaps.request().get();
                
        Map retorno = response.readEntity(Map.class);
        
        result.use(Results.json()).from(retorno).serialize();
        //return retorno;
    }
    
}
