/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Cidade;
import br.com.mobitec.buscabarato.model.Usuario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fabio
 */
@RequestScoped
@Named("cidade")
public class CidadeFacade extends AbstractFacade<Cidade> {

    //@PersistenceContext(unitName = "default")
    /*private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }*/

    public CidadeFacade() {
        super(Cidade.class);
    }

    
    public List<Cidade> listarCidadeEstado(int estado) {
        Session session = (Session)getEntityManager().getDelegate();
        
        String hql = "from Cidade c where c.codEstado = :estado";
        
        return session.createQuery(hql)
                .setParameter("estado", estado)
                .list();
        
    }
    
}
