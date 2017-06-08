/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Produto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

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
                .setMaxResults(maximo)
                .addOrder( Order.asc("descricao") );
        List<Produto> produtolst = criteria.list();
        
        return produtolst;
    }

    /**
     * Lista os produtos de acordo com a descrição
     * @param descProd Descrição do produto a ser pesquisado
     * @return Uma lista de produtos
     */
    public List<Produto> listar(String descProd) {
         //String cWhere = "produto.descricao ILIKE '%"+ descProd.trim().replaceAll(" ", "%' and produto.descricao ILIKE '%") +"%'";
         
         String[] cWhere = descProd.split(" ");
                  
         Criteria critic = this.getSession().createCriteria(Produto.class);
         
         Arrays.stream(cWhere).forEach( 
                 a -> critic.add( Restrictions.sqlRestriction("lower({alias}.descricao) ilike lower(?)", "%"+ a +"%", StandardBasicTypes.STRING))  
         );
                
         
         List<Produto> produtos = critic
                 .addOrder( Order.asc("descricao") )
                 .list();
         
         return produtos;
    }
        
}
