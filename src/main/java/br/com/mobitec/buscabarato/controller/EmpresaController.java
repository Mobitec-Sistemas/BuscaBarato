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
import br.com.mobitec.buscabarato.aspecto.Transacional;
import br.com.mobitec.buscabarato.model.Empresa;
import br.com.mobitec.buscabarato.model.service.facade.EmpresaFacade;
import java.util.List;
import javax.inject.Inject;

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
    
    /**
     * @deprecated CDI eyes only
     */
    protected EmpresaController() {
        
    }
    
    /**
     * Lista os produtos.
     * Se usar no Header o Accept=application/json, irá retornar JSon,
     * Sesão irá redirecionar para a página HTML
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
        
        //return empresa;
    }
    
    @Get("/empresa/cadastro/{codigo}")
    public void cadastro(Integer codigo) {        
        Empresa empresa = empresaFacade.find(codigo);
                
        if (empresa == null) {
            result.notFound();
        }
        
        result.redirectTo(this).formulario(empresa);
        
        //return empresa;
    }
    
    @Post("/empresa/cadastro")
    @Transacional
    public void cadastro(Empresa empresa) {
                 
        validator.validate(empresa);
        
        validator.onErrorRedirectTo(this).formulario(empresa);

        if(empresa.getCodigo() == null)
            empresaFacade.create(empresa);
        else
            empresaFacade.edit(empresa);
        
        result.redirectTo(this).lista();
    }
    
}
