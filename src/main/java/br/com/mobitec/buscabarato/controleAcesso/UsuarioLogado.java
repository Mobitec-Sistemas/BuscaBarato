/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controleAcesso;

import br.com.mobitec.buscabarato.model.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Sensum
 */
@SessionScoped
public class UsuarioLogado implements Serializable {
    
    private Usuario usuario;
    
    /**
     * @deprecated CDI eyes only
     */
    protected UsuarioLogado() {
    }
    
    /**
     * @return O usuário logado
     */
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    /**
     * Seta o usuário que está logado
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
