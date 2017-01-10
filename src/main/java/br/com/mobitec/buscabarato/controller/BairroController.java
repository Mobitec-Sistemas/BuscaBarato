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
import static br.com.caelum.vraptor.view.Results.json;
import br.com.mobitec.buscabarato.model.Bairro;
import br.com.mobitec.buscabarato.model.Cidade;
import br.com.mobitec.buscabarato.model.service.facade.BairroFacade;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Sensum
 */
@Controller
@RequestScoped
public class BairroController {
    
    @Inject
    private Result result;
    
    @Inject
    private BairroFacade bairroFacade;
    
    @Inject
    private Validator validator;
    
    /**
     * @deprecated CDI eyes only
     */
    protected BairroController() {    
    }
    
    /**
     * Lista os Bairros no formato json
     */
    @Get("/bairro")
    public void lista() {
        List<Bairro> retorno = bairroFacade.findAll();
        
        result.use(json()).from(retorno).serialize();
    }
    
    /**
     * Lista o bairro solicitada no formato json.
     * @param cidade
     */
    @Get("/bairro/{cidade.codigo}")
    public void lista(Cidade cidade) {
        List<Bairro> retorno = bairroFacade.listarBairroCidade(cidade.getCodigo());
        
        result.use(json()).from(retorno).serialize();
    }
    
    public List<Bairro> lista(Bairro bairro) {
        List<Bairro> bairros = bairroFacade.listarBairro(bairro);
        
        return bairros;
    }
    
    @Post("/bairro/cadastro")
    @Transactional
    public void cadastro(Bairro bairro) {
        validator.validate(bairro);
        
        if(bairro.getCodigo() == null)
            bairroFacade.create(bairro);
        else
            bairroFacade.edit(bairro);
        
    }
    
}
