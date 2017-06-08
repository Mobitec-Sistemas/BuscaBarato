/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.mobitec.buscabarato.model.Marca;
import br.com.mobitec.buscabarato.model.Produto;
import br.com.mobitec.buscabarato.model.service.facade.MarcaFacade;
import br.com.mobitec.buscabarato.model.service.facade.ProdutoFacade;
import br.com.mobitec.buscabarato.validacao.DeleteRestricValidator;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
    private MarcaFacade marcaFacade;
    
    @Inject
    private DeleteRestricValidator validator;
        
    /**
     * @deprecated CDI eyes only
     */
    protected ProdutoController() {
    }
    
    
    /**
     * Lista os produtos.
     * Se usar no Header o Accept=application/json, irá retornar JSon,
     * Sesão irá redirecionar para a página HTML
     */
    //@Public
    @Get("/produto")
    @Transactional
    public void lista() {
        
        List<Produto> lista = produtoFacade.findAll();
        //lista.sort((p1, p2) -> p1.getDescricao().compareToIgnoreCase(p2.getDescricao()) );
        
        result.use(Results.representation()).from(lista, "produtoList").include("marca").include("imagem").serialize();
    }
    
    /**
     * Lista os produtos separados por página.
     * Cara página terá 100 produtos
     * @param pagina 
     */
    @Get("/produto/{pagina}")
    @Transactional
    public void lista(int pagina) {
                
        List<Produto> lista = produtoFacade.listar((pagina - 1)*100, 100); // Retira 1 pois as páginas são iniciados com zero
        int totalPagina = produtoFacade.count();
        int totalPag = (totalPagina / 100);
        if(totalPag < (double)totalPagina / 100)
            totalPag += 1;

        result.include("pagina", pagina);
        result.include("total_pagina", totalPag);
        result.use(Results.representation()).from(lista, "produtoList").include("imagem").serialize();
    }
    
    @Get()
    @Path(value = "/produto/{descProd}", priority = Path.LOW )
    @Public
    public void list(String descProd) {
        List<Produto> lista = produtoFacade.listar(descProd);
        
        result.use(Results.representation()).from(lista, "produtoList").include("imagem").serialize();
    }
            
    @Get("/produto/cadastro")
    public Produto cadastro() {
        
        Produto produto = new Produto();
        
        List<Marca> listaMarca = marcaFacade.findAll();
        result.include("marcaList",listaMarca);
        
        return produto;
    }
    
    @Get("/produto/cadastro/{codigo}")
    //@Transacional
    public Produto cadastro(Integer codigo) {
        
        Produto produto = produtoFacade.find(codigo);
        
        List<Marca> listaMarca = marcaFacade.findAll();
        result.include("marcaList", listaMarca);
        
        if (produto == null) {
            result.notFound();
        }
        
        return produto;
    }
    
    @Post("/produto/cadastro")
    @Transactional
    public void cadastro(Produto produto, UploadedFile imagem) throws IOException {
        
        if(imagem != null) {
            validator.addIf( !imagem.getContentType().equals("image/jpeg") && 
                             !imagem.getContentType().equals("image/jpg") && 
                             !imagem.getContentType().equals("image/png"), 
                    new SimpleMessage("imagem", "A imagem deve ser no formato JPG ou PNG"));

            produto.setImagem(ByteStreams.toByteArray(imagem.getFile()));
        }
         
        validator.validate(produto);
        
        //validator.onErrorRedirectTo(this).cadastro();
        validator.onErrorForwardTo(this).cadastro();

        if(produto.getCodigo() == null)
            produtoFacade.create(produto);
        else
            produtoFacade.edit(produto);
        
        result.redirectTo(this).lista(1);
        //result.redirectTo(this).cadastro();
    }
    
}
