/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@RequestScoped
public class DeleteRestricValidator {
    
    //@Inject
    //private Result result;
    
    private String mensagem;
    
    //@Inject
    private Validator validate;
    
    /**
     * @deprecated Apenas para o CDI
     */
    protected DeleteRestricValidator() {
    }

    @Inject
    public DeleteRestricValidator(Validator validate) {
        //this.result = result;
        this.validate = validate;
        this.mensagem = null;
    }
    
    /**
     * Valida a exclusão de uma marca
     * @param marca
     * @return 
     */
    /*public DeleteRestricValidator validate(Marca marca) {
        if( marca == null )
            mensagem = "Marca não encontrada";
        else
            if( marca.getProdutos() != null && !marca.getProdutos().isEmpty() )
                mensagem = "Não é permitido excluir esta marca pois ela possui produtos relacionados";
        
        validate.validate(marca);
        
        return this;
    }*/
    /**
     * Valida a exclusão de um registro no banco de dados
     * @param classe
     * @return 
     */
    public DeleteRestricValidator validate(Object classe) {
        if( classe == null )
            mensagem = "Registro não encontrado";
        else {
            for(Field campo : classe.getClass().getDeclaredFields()) {
                // verifica se o campo tem a anotação de delete restrito
                if( campo.isAnnotationPresent(DeleteRestrict.class) ) {
                    //Class ftClass = campo.getClass();
                    campo.setAccessible(true);
                    
                    // Verifica se o campo possui valor
                    List lista = null;
                    try {
                        lista = (List)campo.get(classe);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(DeleteRestricValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(DeleteRestricValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if( lista != null && !lista.isEmpty() ) {
                        mensagem = campo.getAnnotation(DeleteRestrict.class).message();
                    }
                }
                
            }
        }
        
        validate.validate(classe);
        
        return this;
    }

    public List<Message> getErrors() {        
        List<Message> mensagens = validate.getErrors();
        
        mensagens.add(new SimpleMessage(mensagem, mensagem));
        
        return mensagens;
    }

    public boolean hasErrors() {
        return validate.hasErrors() || mensagem != null;
    }

    /*public void onErrorSendErrorRequest() {
        if(mensagem != null) {
            validate.add(new ConversionMessage(mensagem, mensagem));
            result.use(http()).sendError(500, mensagem); 
        }
    }*/

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

    public void onErrorSendBadRequest() {
        validate.onErrorSendBadRequest();
    }

    public Validator addIf(boolean expression, Message message) {
        return validate.addIf(expression, message);
    }
    
    
    public String getMensagem() {
        return this.mensagem;
    }
    
}
