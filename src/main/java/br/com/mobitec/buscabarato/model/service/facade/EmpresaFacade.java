/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Empresa;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Fabio
 */
//@Named("empresa")
@RequestScoped
public class EmpresaFacade extends AbstractFacade<Empresa> {

    
    public EmpresaFacade() {
        super(Empresa.class);
    }
    
}
