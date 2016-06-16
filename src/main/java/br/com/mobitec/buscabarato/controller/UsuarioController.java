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
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.mobitec.buscabarato.model.Usuario;
import br.com.mobitec.buscabarato.model.service.facade.UsuarioFacade;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@Controller
public class UsuarioController {
    
    @Inject
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    /**
     * Contrutor padrão para o CDI
     */
    public UsuarioController() {
        
    }
    
    /**
     * Carrega os dados do usuário para o login
     * @return 
     */
    @Get("/usuario")
    public Usuario login() {
        Usuario usuario = new Usuario();
        
        return usuario;
    }
    
    /**
     * Executa o login do usuario
     * @param usuario
     */
    @Post("/usuario")
    public void login(Usuario usuario) {
        
        //validator.validate(usuario);
        
        Usuario usuarioCarregado = usuarioFacade.carrega(usuario);
        
        if(usuarioCarregado == null) {
            validator.add(
                    new SimpleMessage("usuario.login", "Login e/ou senha inválidos"));
        }
        
        validator.onErrorRedirectTo(this).login();
        
        result.redirectTo(InicioController.class).index();
    }    
}
