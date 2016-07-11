/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controleAcesso;

import br.com.caelum.brutauth.auth.annotations.GlobalRule;
import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@RequestScoped
@GlobalRule
//@HandledBy(RedirecionaAcesso.class)
public class ControlaAcesso implements CustomBrutauthRule {

    @Inject
    private UsuarioLogado usuarioLogado;

    /**
     * @deprecated CDI eyes only
     */
    protected ControlaAcesso() {
    }

    /**
     * Aplica a restrição do controle de acesso
     * @return true se o usuário pode proceguir
     */
    public boolean isAllowed() {
        return this.usuarioLogado != null && this.usuarioLogado.getUsuario() != null;
    }
        
}
