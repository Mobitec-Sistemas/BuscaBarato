/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import javax.inject.Inject;

/**
 *
 * @author Fabio
 */
@Controller
public class OlaController {
  
    @Inject private Result result;
 
    @Get("/")
    public void ola() {
        result.use(Results.http()).body("Olá vRaptor").setStatusCode(200);
    }
    
}
