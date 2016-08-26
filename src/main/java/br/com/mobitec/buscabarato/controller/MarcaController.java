/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.mobitec.buscabarato.aspecto.Transacional;
import br.com.mobitec.buscabarato.model.Marca;
import br.com.mobitec.buscabarato.model.service.facade.MarcaFacade;
import br.com.mobitec.buscabarato.validacao.DeleteRestricValidator;
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
    
    @Inject
    private DeleteRestricValidator validator;
    
    /**
     * @deprecated CDI eyes only
     */
    protected MarcaController() { 
    }
    
    /**
     * Lista as marcas no formato JSon
     */
    @Get("/marcaJson")
    public void listaJson() {
        List<Marca> retorno = this.lista();
        
        result.use(json()).from(retorno, "marcas").serialize();
    }
    
    /**
     * Retorna a marca no formato JSon
     * @param marca a ser pesquisado
     */
    @Get("/marcaJson/{codMarca}")
    public void listaJson(Integer marca) {
        Marca retorno = this.edita(marca);
        
        result.use(json()).from(retorno).serialize();
    }
        
    /**
     * Lista as marcas
     * @return lista com as marcas
     */
    @Get
    @Path(value = "/marca")
    public List<Marca> lista() {
        List<Marca> retorno = marcaFacade.findAll();
        
        return retorno;
    }
    
    @Get("/marca/formulario")
    public void formulario() {
    } 
    
    /**
     * Retorna a marca informada
     * @param codMarca
     * @return 
     */
    @Get("/marca/{codMarca}")
    public Marca edita(Integer codMarca) {
        Marca marca = marcaFacade.find(codMarca);
        
        if (marca == null) {
            marca = new Marca();
                //result.notFound();
        }
        
        result.include(marca);
        result.of(this).formulario();
        
        return marca;
    }
    
    @Get
    
    @Post("/marca")
    @Transacional
    public void cadastro(Marca marca) { 
        validator.validate(marca);
        
        validator.onErrorRedirectTo(this).formulario();

        if(marca.getCodigo() == null)
            marcaFacade.create(marca);
        else
            marcaFacade.edit(marca);
        
        result.redirectTo(this).lista();
        
    }
    
    /**
     * Exclui uma Marca
     * @param codMarca 
     */
    @Delete("/marca/{codMarca}")
    @Transacional
    public void excluir(Integer codMarca) {
        Marca marca = marcaFacade.find(codMarca);
        
        validator.validate(marca); 
        
        //validator.onErrorSendErrorRequest();
        if( !validator.hasErrors() ) {
            // Remove a Marca
            marcaFacade.remove(marca);

            result.nothing(); // apenas retorna o código de sucesso (HTTP 200 OK).
        }
        else {
            result.use(http()).sendError(500, validator.getMensagem()); 
        }

    }
    
}
