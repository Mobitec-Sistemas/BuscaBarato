
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Usuario;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fabio
 */

@RequestScoped
//@Named("usuario")
public class UsuarioFacade extends AbstractFacade<Usuario> {
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario carrega(Usuario usuario) {
        Session session = (Session)getEntityManager().getDelegate();
                
        return (Usuario) session.createCriteria(Usuario.class)
                .add(
                    Restrictions.or(
                        Restrictions.eq("login", usuario.getLogin()),
                        Restrictions.eq("email", usuario.getLogin())
                    )
                )
                .add(Restrictions.eq("senha", usuario.getSenha()))
                .uniqueResult();
    }
    
    public Usuario carrega(String token) {
        Session session = (Session)getEntityManager().getDelegate();
                
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("token", token))
                .uniqueResult();
    }
    
}
