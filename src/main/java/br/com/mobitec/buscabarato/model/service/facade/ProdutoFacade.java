/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Produto;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Criteria;

/**
 *
 * @author Fabio
 */
@RequestScoped
public class ProdutoFacade extends AbstractFacade<Produto> {

    public ProdutoFacade() {
        super(Produto.class);
    }
 
    public List<Produto> listar(int inicio, int maximo) {
        Criteria criteria = this.getSession().createCriteria(Produto.class);
        criteria.setFirstResult(inicio)
                .setMaxResults(maximo);
        List<Produto> produtolst = criteria.list();
        
        return produtolst;
    }
        
}
