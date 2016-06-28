/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Produto;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fabio
 */
@RequestScoped
public class ProdutoFacade extends AbstractFacade<Produto> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        if (em == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
            em = factory.createEntityManager();
        }
   
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }
 
    /*public void whenApplicationStarts(@Observes ServletContext context) {
        //logger.info("My application is UP");
    }*/
}
