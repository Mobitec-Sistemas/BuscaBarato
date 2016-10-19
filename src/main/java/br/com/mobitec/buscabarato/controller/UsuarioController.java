/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.mobitec.buscabarato.controleAcesso.UsuarioLogado;
import br.com.mobitec.buscabarato.model.Usuario;
import br.com.mobitec.buscabarato.model.service.facade.UsuarioFacade;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
    
    @Inject
    private HttpServletRequest request;
    
    @Inject
    private UsuarioLogado usuarioLogado;
    
    /**
     * Contrutor padrão para o CDI
     */
    public UsuarioController() {
        
    }
    
    /**
     * Carrega os dados do usuário para o login
     * @return 
     */
    @Public
    @Get("/usuario")
    public Usuario login() {
        Usuario usuario = new Usuario();
        
        return usuario;
    }
    
    /**
     * Executa o login do usuario
     * @param usuario
     */
    @Public
    @Consumes( value = {"application/json", "application/x-www-form-urlencoded"} )
    @Post("/usuario")
    public void login(Usuario usuario) {
                
        Usuario usuarioCarregado = usuarioFacade.carrega(usuario);
        
        usuarioLogado.setUsuario(usuarioCarregado);
        
        // Obtém o cabecalho para verificar se os dados vieram por formulário ou JSON
        if( request.getHeader("Content-Type") != null && request.getHeader("Content-Type").equalsIgnoreCase("application/json") )
        {
            // Trata como aplicativo
            if(usuarioCarregado != null)
            {
                result.use(Results.http()).body(usuarioCarregado.getNome()).setStatusCode(202);
                //result.use(Results.http()).setStatusCode( 202 );
            }
            else
            {
                result.use(Results.http()).body("Usuario ou senha invalido").setStatusCode(401);
                //result.use(Results.http()).setStatusCode( 401 );
            }
        }
        else
        {
            // Trata como formulário HTML
            
            if(usuarioCarregado == null) {
                validator.add(
                        new SimpleMessage("usuario.login", "Login e/ou senha inválidos"));
            }

            validator.onErrorRedirectTo(this).login();

            result.redirectTo(InicioController.class).index();
        }
    }

    /**
     * Faz logoff da aplicação
     */
     @Get("/usuario/logoff")
    public void logoff() {
        
        usuarioLogado.setUsuario(null);
     
        result.redirectTo(InicioController.class).index();
    }
}
