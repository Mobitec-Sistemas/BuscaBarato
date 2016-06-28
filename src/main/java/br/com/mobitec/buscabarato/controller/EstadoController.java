/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.mobitec.buscabarato.model.Estado;
import br.com.mobitec.buscabarato.model.service.facade.EstadoFacade;
import java.util.List;
import javax.inject.Inject;

/**
 * Contra o Estado
 * 
 * @author Sensum
 */
@Controller
public class EstadoController {
    @Inject
    private Result result;
    
    @Inject
    private EstadoFacade estadoFacade;

    /**
     * @deprecated CDI eyes only
     */
    public EstadoController() {
    }
    
    /**
     * Lista os estados no formato json
     */
    @Get("/estado")
    public void lista() {
        List<Estado> retorno = estadoFacade.findAll();
        
        result.use(json()).from(retorno).serialize();
    }
    
    /**
     * Retorna o estado solicitado
     * @param estado Estado a ser pesquisado
     */
    @Get("/estado/{estado.codigo}")
    public void lista(Estado estado) {
        Estado retorno = estadoFacade.find(estado.getCodigo());
        
        result.use(json()).from(retorno).serialize();
    }
    
}
