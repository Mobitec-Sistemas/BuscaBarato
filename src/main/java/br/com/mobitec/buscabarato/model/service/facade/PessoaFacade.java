/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Pessoa;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author Fabio
 */
@Named("pessoa")
public class PessoaFacade extends AbstractFacade<Pessoa> {

    //@PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(Pessoa.class);
    }
    
}
