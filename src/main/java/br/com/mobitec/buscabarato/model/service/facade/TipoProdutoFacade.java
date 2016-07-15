/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.TipoProduto;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Sensum
 */
@RequestScoped
public class TipoProdutoFacade extends AbstractFacade<TipoProduto> {

    public TipoProdutoFacade() {
        super(TipoProduto.class);
    }
    
}
