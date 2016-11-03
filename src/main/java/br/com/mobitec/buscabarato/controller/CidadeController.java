/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.mobitec.buscabarato.model.Cidade;
import br.com.mobitec.buscabarato.model.service.facade.CidadeFacade;
import java.util.List;
import javax.inject.Inject;

/**
 * Controla a Cidade
 * @author Sensum
 */
@Controller
public class CidadeController {
    
    @Inject
    private Result result;
    
    @Inject
    private CidadeFacade cidadeFacade;
    
    /**
     * @deprecated CDI eyes only
     */
    protected CidadeController() {
    }
    
    /**
     * Lista as cidades no formato json
     * @param codCidade a ser pesquisada
     */
    /*@Get("/cidade/{codCidade}")
    public void lista(Integer codCidade) {
        Cidade retorno = cidadeFacade.find(codCidade);
        
        result.use(json()).from(retorno).serialize();
    }*/
    
    /**
     * Lista as cidades de um determinado estado
     * @param codEstado a ser pesquisada
     */
    @Get("/cidade/estado/{codEstado}")
    public void listaCidadesEstado(Integer codEstado) {
        List<Cidade> retorno = cidadeFacade.findCidadesEstado(codEstado);
        
        result.use(json()).from(retorno, "cidades").serialize();
    }
    
    
    
    /**
     * Lista a cidade solicitada no formato json
     * @param cidade 
     */
    @Get("/cidade")
    public void lista(Cidade cidade) {
        List<Cidade> retorno = cidadeFacade.listarCidade(cidade);
        
        result.use(json()).from(retorno, "cidades").include("estado").serialize();
    }
}
