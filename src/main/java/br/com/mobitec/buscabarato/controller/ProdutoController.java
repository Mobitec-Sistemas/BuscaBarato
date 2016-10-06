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
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.mobitec.buscabarato.aspecto.Transacional;
import br.com.mobitec.buscabarato.controleAcesso.UsuarioLogado;
import br.com.mobitec.buscabarato.model.Marca;
import br.com.mobitec.buscabarato.model.Produto;
import br.com.mobitec.buscabarato.model.service.facade.MarcaFacade;
import br.com.mobitec.buscabarato.model.service.facade.ProdutoFacade;
import br.com.mobitec.buscabarato.validacao.DeleteRestricValidator;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;

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
    
    @Inject
    private UsuarioLogado usuarioLogado;
    
    private static final Logger logger = Logger.getLogger(ProdutoController.class);
    
    /**
     * @deprecated CDI eyes only
     */
    public ProdutoController() {
    }
    
    
    /**
     * Lista os produtos.
     * Se usar no Header o Accept=application/json, irá retornar JSon,
     * Sesão irá redirecionar para a página HTML
     */
    @Get("/produto")
    @Transacional
    public void lista() {
        logger.info("Iniciando o metodo Lista");
        
        List<Produto> lista = produtoFacade.findAll();
                
        // Gera o Base 64 das imagens
        //lista.forEach(l -> l.gerarBase64());
        
        result.use(Results.representation()).from(lista, "produtoList").serialize();
        //return lista;
    }
            
    @Get("/produto/cadastro")
    public Produto cadastro() {
        logger.info("Iniciando o metodo Cadastro");
        
        Produto produto = new Produto();
        
        List<Marca> listaMarca = marcaFacade.findAll();
        result.include("marcaList",listaMarca);
        
        return produto;
    }
    
    @Get("/produto/cadastro/{codigo}")
    //@Transacional
    public Produto cadastro(Integer codigo) {
        logger.info("Iniciando o metodo Cadastro com código de parâmetro");
        
        Produto produto = produtoFacade.find(codigo);
        
        List<Marca> listaMarca = marcaFacade.findAll();
        result.include("marcaList", listaMarca);
        
        if (produto == null) {
            result.notFound();
        }
        
        return produto;
    }
    
    @Post("/produto/cadastro")
    @Transacional
    public void cadastro(Produto produto, UploadedFile imagem) throws IOException {
        logger.info("Iniciando o metodo Cadastro por Post");
        
        if(imagem != null) {
            validator.addIf( !imagem.getContentType().equals("image/jpg") && 
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
        
        //result.redirectTo(this).lista();
        result.redirectTo(this).cadastro();
    }
    
}
