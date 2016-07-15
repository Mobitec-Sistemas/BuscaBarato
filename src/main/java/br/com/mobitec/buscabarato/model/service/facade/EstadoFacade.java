/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Estado;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
    
}
