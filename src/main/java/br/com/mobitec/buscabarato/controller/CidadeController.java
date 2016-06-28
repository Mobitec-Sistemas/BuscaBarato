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
import br.com.mobitec.buscabarato.model.Estado;
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
    public CidadeController() {
        
    }
    
    /**
     * Lista as cidades no formato json
     */
    @Get("/cidade")
    public void lista() {
        List<Cidade> retorno = cidadeFacade.findAll();
        
        result.use(json()).from(retorno).serialize();
    }
    
    /**
     * Lista a cidade solicitada no formato json
     * @param cidade 
     */
    /*@Get("/cidade/{cidade.codigo}")
    public void lista(Cidade cidade) {
        Cidade retorno = cidadeFacade.find(cidade.getCodigo());
        
        result.use(json()).from(retorno).serialize();
    }*/
    
    /**
     * Lista a cidade solicitada no formato json
     * @param estado 
     */
    @Get("/cidade")
    public void lista(Estado estado) {
        List<Cidade> retorno = cidadeFacade.listarCidadeEstado(estado.getCodigo());
        
        result.use(json()).from(retorno).serialize();
    }
}
