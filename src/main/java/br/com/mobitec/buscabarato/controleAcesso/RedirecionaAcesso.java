/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controleAcesso;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import br.com.mobitec.buscabarato.controller.UsuarioController;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@RequestScoped 
public class RedirecionaAcesso implements RuleHandler {
    
    private final Result result;

    /**
     * @deprecated CDI eyes only
     */
    protected RedirecionaAcesso() {
        this(null);
    }

    @Inject
    public RedirecionaAcesso(Result result) {
        this.result = result;
    }

    @Override
    public void handle() {
        result.redirectTo(UsuarioController.class).login();
    }
}
