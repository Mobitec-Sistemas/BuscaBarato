/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.mobitec.buscabarato.model.Produto;
import br.com.mobitec.buscabarato.model.service.facade.ProdutoFacade;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Fabio
 */
@Controller
public class ProdutoController {
    
    @Inject
    private Result result;
    
    @Inject
    private ProdutoFacade produtoFacade;
    
    /**
     * @deprecated CDI eyes only
     */
    public ProdutoController() {
    }
    
    @Get("/produto")
    public List<Produto> lista() {
        List<Produto> lista = produtoFacade.findAll();
        
        return lista;
    }
    
    @Get("/produto/cadastro")
    public Produto cadastro() {
        Produto produto = new Produto();
        
        return produto;
    }
    
}
