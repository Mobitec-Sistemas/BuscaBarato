/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Empresa;
import br.com.mobitec.buscabarato.model.Produto;
import br.com.mobitec.buscabarato.model.TabelaPreco;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;


/**
 *
 * @author Fabio
 */
@RequestScoped
public class TabelaPrecoFacade extends AbstractFacade<TabelaPreco> {
        
    public TabelaPrecoFacade() {
        super(TabelaPreco.class);
    }

    /**
     * Lista os preços de todos os produtos de uma determinada empresa
     *
     * @param empresa
     * @return
     */
    public List<TabelaPreco> listarProdutosEmpresa(Empresa empresa) {
        
        // Seleciona as tabelas de preços da empresa
        DetachedCriteria query = DetachedCriteria.forClass(TabelaPreco.class, "tp")
                .createAlias("empresa", "emp", JoinType.INNER_JOIN)
                .add(Restrictions.eq("emp.codigo", empresa.getCodigo()));
        
        List<TabelaPreco> tabelaPreco =  query.getExecutableCriteria(getSession()).list();
        
        // Seleciona os produtos que não estão na tabela de preços
        query.setProjection( Property.forName("tp.produto") );
        Criteria queryProduto = getSession().createCriteria(Produto.class, "p")
                .add( Property.forName("p.codigo").notIn(query) );
        
        List<Produto> produtos = queryProduto.list();
        
        // Adiciona os produtos na lista de tabela de preço
        produtos.stream().forEach( produto -> { 
            TabelaPreco tabPre = new TabelaPreco();
            tabPre.setEmpresa(empresa);
            tabPre.setProduto(produto);
            tabPre.setPreco(BigDecimal.ZERO);
            
            tabelaPreco.add(tabPre);
        });
        
        // Ordena a lista por descrição do produto e preço
        //tabelaPreco.sort((t1, t2) -> t1.getProduto().getDescricao().compareTo(t2.getProduto().getDescricao()) );
        tabelaPreco.sort((t1, t2) -> t1.compareTo(t2) );
        
        return tabelaPreco;
    }
    
}
