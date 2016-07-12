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
import br.com.mobitec.buscabarato.model.Marca;
import br.com.mobitec.buscabarato.model.service.facade.MarcaFacade;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@Controller
public class MarcaController {
    
    @Inject
    private Result result;
    
    @Inject
    private MarcaFacade marcaFacade;
    
    /**
     * @deprecated CDI eyes only
     */
    protected MarcaController() { 
    }
    
    /**
     * Lista as marcas no formato json
     * @param marca
     */
    @Get("/marca")
    public void lista(Marca marca) {
        List<Marca> retorno = marcaFacade.listarMarca(marca);
        
        result.use(json()).from(retorno, "marcas").serialize();
    }
    
    /**
     * Retorna o estado solicitado
     * @param marca a ser pesquisado
     */
    @Get("/marca/{codMarca}")
    public void lista(Integer marca) {
        Marca retorno = marcaFacade.find(marca);
        
        result.use(json()).from(retorno).serialize();
    }
    
}
