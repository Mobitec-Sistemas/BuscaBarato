
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Usuario;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fabio
 */

@ApplicationScoped
@Named("usuario")
public class UsuarioFacade extends AbstractFacade<Usuario> {

    //@Inject
    //private Session session;
    
    //@PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario carrega(Usuario usuario) {
        Session session = (Session)em.getDelegate();
        
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("login", usuario.getLogin()))
                .add(Restrictions.eq("senha", usuario.getSenha()))
                .uniqueResult();
    }
    
    public void whenApplicationStarts(@Observes ServletContext context) {
        //logger.info("My application is UP");
    }
}
