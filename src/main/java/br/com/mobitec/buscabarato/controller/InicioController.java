/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import javax.inject.Inject;

/**
 *
 * @author Fabio
 */
@Controller
public class InicioController {
    
    @Inject
    private Result result;
    
    public InicioController() {
    }
    
    /**
     * Controla a página inicial
     */
    @Get("/")
    public void index() {
        result.use(Results.http()).body("Olá vRaptor.").setStatusCode(200);
    }
    
    /*public void ola() {
        result.use(Results.http()).body("Olá vRaptor").setStatusCode(200);
    }*/
}
