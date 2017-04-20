/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.mobitec.buscabarato.model.Usuario;
import br.com.mobitec.buscabarato.model.service.facade.UsuarioFacade;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sensum
 */
@Controller
@RequestScoped
public class UsuarioController {
    
    @Inject
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private UsuarioFacade usuarioFacade;
        
    @Inject
    private Event<Usuario> eventoUsuarioCadastrado;
    
    /**
     * Contrutor padrão para o CDI
     */
    public UsuarioController() {
        
    }
         
    @Get("/usuario")
    @Public
    public Usuario formulario() {
        Usuario usuario = new Usuario();
        
        return this.formulario(usuario);
    }
    
    @Get("/usuario/{usuario}")
    @Public
    public Usuario formulario(Usuario usuario) {        
        return usuario;
    }
    
    @Post("/usuario")
    @Transactional
    @Public
    public void cadastrar(@NotNull Usuario usuario) {
               
        validator.validate(usuario);
        
        if(usuario.getEmail() == null || usuario.getEmail().isEmpty())
            validator.add(new SimpleMessage("email", "O E-mail não pode ficar em branco"));
        
        if(usuario.getSenha() == null || usuario.getSenha().isEmpty())
            validator.add(new SimpleMessage("senha", "A senha não pode ficar em branco"));
        
        validator.onErrorRedirectTo(this).formulario(usuario);

        if(usuario.getCodigo() == null) {
            usuario.setToken(GerarToken(usuario));
            usuarioFacade.create(usuario);
            
            eventoUsuarioCadastrado.fire(usuario);
        }
        else
            usuarioFacade.edit(usuario);
        
        result.redirectTo(AcessoController.class).login();
    }
    
    /**
     * Ativa o usuário
     * @param token do usuário a ser ativado
     */
    @Get("/usuario/ativar/{token}")
    @Transactional
    @Public
    public void ativar(String token) {
        Usuario usuario = usuarioFacade.carrega(token);
        if(usuario == null)
            validator.add(new SimpleMessage("token", "Acesso inválido"));
        else
            usuario.setAtivo(true);
        
        validator.onErrorRedirectTo(AcessoController.class).login();
        
        usuarioFacade.edit(usuario);
        
        result.include("mensagem", "Conta ativada com sucesso!");
        result.redirectTo(AcessoController.class).login();
    }
    
    private String GerarToken(Usuario usuario) {
        String retorno = "";
        
        try {
            String frase = usuario.getNome() + usuario.getEmail() + usuario.getSenha();
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(frase.getBytes());
            byte[] hashMd5 = md.digest();
            
            retorno = stringHexa(hashMd5);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    private String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}
