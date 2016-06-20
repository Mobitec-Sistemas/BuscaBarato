/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;

/**
 *
 * @author Fabio
 */
@Controller
public class InicioController {
    
    //@Inject
    //private Result result;
    
    public InicioController() {
    }
    
    /**
     * Controla a página inicial
     */
    @Path("/")
    public void index() {
        
    }
}
