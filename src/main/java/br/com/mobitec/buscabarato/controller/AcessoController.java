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
 * Faz o controle dos usuários no sistema
 * 
 * @author Sensum
 */
@Controller
public class AcessoController {
    
    private @Inject Result result;
    private @Inject Validator validator;
    private @Inject HttpServletRequest request;
    private @Inject UsuarioLogado usuarioLogado;
    private @Inject UsuarioFacade usuarioFacade;
    
    /**
     * Contrutor padrão para o CDI
     */
    public AcessoController() {
        
    }
    
    /**
     * Carrega os dados do usuário para o login
     * @return 
     */
    @Public
    @Get("/login")
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
    @Post("/login")
    public void login(Usuario usuario) {
                
        Usuario usuarioCarregado = usuarioFacade.carrega(usuario);
                
        // Obtém o cabecalho para verificar se os dados vieram por formulário ou JSON
        if( request.getHeader("Content-Type") != null && request.getHeader("Content-Type").equalsIgnoreCase("application/json") )
        {
            // Trata como aplicativo
            if(usuarioCarregado != null)
            {
                if( !usuarioCarregado.isAtivo() ) {
                    result.use(Results.http()).body("A conta ainda não foi ativada").setStatusCode(401);
                }
                else {
                    result.use(Results.http()).body(usuarioCarregado.getNome()).setStatusCode(202);
                    usuarioLogado.setUsuario(usuarioCarregado);
                }
            }
            else
            {
                result.use(Results.http()).body("Usuario ou senha invalido").setStatusCode(401);
            }
        }
        else
        {
            // Trata como formulário HTML
            if(usuarioCarregado == null) {
                validator.add(
                        new SimpleMessage("usuario.login", "Login e/ou senha inválidos"));
            }
            else {
                if( !usuarioCarregado.isAtivo() ) {
                    validator.add(
                        new SimpleMessage("usuario.login", "Esta conta ainda não está ativa. Acesse seu e-mail e ative seu cadastro"));
                }
                else {
                    usuarioLogado.setUsuario(usuarioCarregado);
                }
            }

            validator.onErrorRedirectTo(this).login();
            result.redirectTo(InicioController.class).index();
        }
    }

    /**
     * Faz logoff da aplicação
     */
     @Get("/logoff")
    public void logoff() {
        
        usuarioLogado.setUsuario(null);
     
        result.redirectTo(InicioController.class).index();
    }
    
}
