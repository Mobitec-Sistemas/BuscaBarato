/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controleAcesso;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.mobitec.buscabarato.controller.AcessoController;
import br.com.mobitec.buscabarato.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Faz o login utilizado os dados do header da requisição
 * @author Sensum
 */
@Intercepts(before = ControlaAcesso.class)
public class LoginHeader implements Interceptor {

    @Inject 
    private HttpServletRequest request;
    
    @Inject
    private Result result;
    
    @Inject
    private UsuarioLogado usuarioLogado;
    
    @Inject
    private AcessoController acessoController;
    
    @Override
    public boolean accepts(ControllerMethod method) {
        return true;
    }
    
    @Override
    public void intercept(InterceptorStack stack, ControllerMethod method, Object controllerInstance) throws InterceptionException {
        
        String usuarioSenha = request.getHeader("Authorization");
        
        if( usuarioSenha != null && usuarioSenha.length() > 6 ) {
            usuarioSenha = usuarioSenha.substring(6);
            try {
                usuarioSenha = new String( Base64.getDecoder().decode(usuarioSenha), "UTF-8" );
            } catch (UnsupportedEncodingException ex) {
                usuarioSenha = new String( Base64.getDecoder().decode(usuarioSenha) );
            }
                
            Usuario usu = new Usuario();
            usu.setLogin(usuarioSenha.substring(0, usuarioSenha.indexOf(':')));
            usu.setSenha(usuarioSenha.substring(usuarioSenha.indexOf(':')+1));
            
            acessoController.login(usu);
            
            // Verifica se consegui fazer o login
            if( usuarioLogado.getUsuario() != null )
                stack.next(method, controllerInstance);
        }
        else {
            stack.next(method, controllerInstance);
        }
    }
    
}
