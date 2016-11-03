/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Estado;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Fabio
 */
@RequestScoped
@Named("estado")
public class EstadoFacade extends AbstractFacade<Estado> {

    //@PersistenceContext(unitName = "default")
    /*private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }*/

    public EstadoFacade() {
        super(Estado.class);
    }
    
    @Override
    public List<Estado> findAll() {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Estado> estado = cq.from(Estado.class);
        cq.select(estado);
        
        cq.orderBy(cb.asc(estado.get("nome")));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}
