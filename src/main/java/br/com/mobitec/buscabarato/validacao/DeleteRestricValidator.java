/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import static br.com.caelum.vraptor.view.Results.http;
import br.com.mobitec.buscabarato.model.Marca;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@RequestScoped
public class DeleteRestricValidator {
    
    //@Inject
    private Result result;
    
    private String mensagem;
    
    @Inject
    private Validator validate;
    
    /**
     * @deprecated Apenas para o CDI
     */
    protected DeleteRestricValidator() {
    }

    @Inject
    public DeleteRestricValidator(Result result) {
        this.result = result;
        this.mensagem = null;
    }
    
    /**
     * Valida a exclusão de uma marca
     * @param marca
     * @return 
     */
    public DeleteRestricValidator validate(Marca marca) {
        if( marca == null )
            mensagem = "Marca não encontrada";
        else
            if( marca.getProdutos() != null && !marca.getProdutos().isEmpty() )
                mensagem = "Não é permitido excluir esta marca pois ela possui produtos relacionados";
        
        validate.validate(marca);
        
        return this;
    }

    public void onErrorSendErrorRequest() {
        if(mensagem != null)
            result.use(http()).sendError(500, mensagem);
    }

    /**
     * Delegate da classe Validate
     * @param <T>
     * @param controller
     * @return 
     */
    public <T> T onErrorForwardTo(Class<T> controller) {
        return validate.onErrorForwardTo(controller);
    }

    /**
     * Delegate da classe Validate
     * @param <T>
     * @param controller
     * @return 
     */
    public <T> T onErrorForwardTo(T controller) {
        return validate.onErrorForwardTo(controller);
    }

    public <T> T onErrorRedirectTo(Class<T> controller) {
        return validate.onErrorRedirectTo(controller);
    }

    public <T> T onErrorRedirectTo(T controller) {
        return validate.onErrorRedirectTo(controller);
    }
    
    
    
    public String getMensagem() {
        return this.mensagem;
    }
    
}
