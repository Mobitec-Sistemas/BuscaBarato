/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import static br.com.caelum.vraptor.Path.LOW;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.mobitec.buscabarato.model.Bairro;
import br.com.mobitec.buscabarato.model.Empresa;
import br.com.mobitec.buscabarato.model.service.facade.EmpresaFacade;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author Sensum
 */
@Controller
public class EmpresaController {
    
    @Inject
    private EmpresaFacade empresaFacade;
    
    @Inject
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private BairroController bairroController;
    
    private static final Logger logger = Logger.getLogger(EmpresaController.class);    
    
    /**
     * @deprecated CDI eyes only
     */
    protected EmpresaController() {
        
    }
    
    /**
     * Lista os produtos.
     * Se usar no Header o Accept=application/json, irá retornar JSon,
     * Senão irá redirecionar para a página HTML
     */
    @Get("/empresa")
    public void lista() {        
        List<Empresa> lista = empresaFacade.findAll();
                        
        result.use(Results.representation()).from(lista, "empresaList").serialize();
        
    }
    
    /**
     * Formulário para cadastro de empresas.
     * 
     * @param empresa
     * @return 
     */
    @Get
    @Path(value = "/empresa/formulario", priority = LOW )
    public Empresa formulario(Empresa empresa) {
        return empresa;
    }
            
    @Get("/empresa/cadastro")
    public void cadastro() {        
        Empresa empresa = new Empresa();
        
        result.redirectTo(this).formulario(empresa);
    }
    
    @Get("/empresa/cadastro/{codigo}")
    public void cadastro(Integer codigo) {        
        Empresa empresa = empresaFacade.find(codigo);
                
        if (empresa == null) {
            result.notFound();
        }
        
        //result.redirectTo(this).formulario(empresa);
        result.forwardTo(this).formulario(empresa);
    }
    
    @Post("/empresa/cadastro")
    @Transactional
    public void cadastro(Empresa empresa) {
        
        logger.info("Latitude: "+ empresa.getLatitude());
        logger.info("Longitude: "+empresa.getLongitude());
        
        // Valida o Bairro
        List<Bairro> bairros = bairroController.lista(empresa.getBairro());
        
        // Verifica se o bairro já está cadastrado
        if(bairros != null && bairros.size() == 1 )
            empresa.setBairro(bairros.get(0));
        else 
            bairroController.cadastro(empresa.getBairro());
        
        validator.validate(empresa);
        
        validator.onErrorRedirectTo(this).formulario(empresa);

        if(empresa.getCodigo() == null)
            empresaFacade.create(empresa);
        else
            empresaFacade.edit(empresa);
        
        result.include("mensagem", "Empresa cadastrada com sucesso!");
        result.redirectTo(this).lista();
    }
    
}
