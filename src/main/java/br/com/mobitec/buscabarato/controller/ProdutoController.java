/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.mobitec.buscabarato.aspecto.Transacional;
import br.com.mobitec.buscabarato.model.Marca;
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
    
    @Inject
    private Validator validator;
    
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
    
    @Post("/produto/cadastro")
    @Transacional
    public void cadastro(Produto produto) {
         
        validator.validate(produto);
        
        //validator.onErrorRedirectTo(this).cadastro();
        validator.onErrorForwardTo(this).cadastro();

        if(produto.getCodigo() == null)
            produtoFacade.create(produto);
        else
            produtoFacade.edit(produto);
        
        result.redirectTo(InicioController.class).index();
    }
    
}
