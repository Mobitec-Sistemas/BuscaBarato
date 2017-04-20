/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Empresa;
import br.com.mobitec.buscabarato.model.Produto;
import br.com.mobitec.buscabarato.model.TabelaPreco;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.SQLQuery;


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
     * @param filtroProduto
     * @return
     */
/*    public List<TabelaPreco> listarProdutosEmpresa(Empresa empresa, Produto filtroProduto) {
                
        // Seleciona as tabelas de preços da empresa
        DetachedCriteria query = DetachedCriteria.forClass(TabelaPreco.class, "tp");
                
        if(empresa != null && empresa.getCodigo() > 0 ) {
            query.createAlias("empresa", "emp", JoinType.INNER_JOIN)
                .add(Restrictions.eq("emp.codigo", empresa.getCodigo()));
        }
        
        /*List<TabelaPreco> tabelaPreco =  query.getExecutableCriteria(getSession())
                .createAlias("produto", "prod", JoinType.INNER_JOIN)
                .list();*/
/*        Criteria critTabelaPreco = query.getExecutableCriteria(getSession())
                .createAlias("produto", "prod", JoinType.INNER_JOIN);
        
        if(filtroProduto != null)
            critTabelaPreco.add( Restrictions.ilike("prod.descricao", "%"+ filtroProduto.getDescricao().trim().replaceAll(" ", "%%") +"%", MatchMode.ANYWHERE) );
        
        List<TabelaPreco> tabelaPreco = critTabelaPreco.list();
        
                
        // Seleciona os produtos que não estão na tabela de preços
        query.setProjection( Property.forName("tp.produto") );
        Criteria queryProduto = getSession().createCriteria(Produto.class, "p")
                .add( Property.forName("p.codigo").notIn(query) );
        
        if(filtroProduto != null)
            queryProduto.add(Restrictions.ilike("p.descricao", "%"+ filtroProduto.getDescricao() +"%", MatchMode.ANYWHERE));
        
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
    }*/
    
    /**
     * Lista os preços de todos os produtos de uma determinada empresa
     *
     * @param empresa
     * @param filtroProduto
     * @return
     */
    public List<TabelaPreco> listarProdutosEmpresa(Empresa empresa, Produto filtroProduto) {
        
        String cWhere = "%"+ filtroProduto.getDescricao().trim().replaceAll(" ", "%%") +"%";
        String sql = "";
        if(empresa != null && empresa.getCodigo() > 0 ) {
            sql = " and empresa.cod_pessoa = :codEmpresa";
        }
        
        SQLQuery query = getSession().createSQLQuery("SELECT " +
                                                        "    uniao.*,               " +
                                                        "    coalesce(tabela_preco.preco, 0) as \"preco\", " +
                                                        "    tabela_preco.alteracao, " +
                                                        "    uniao.codigo as \"cod_produto\", " +
                                                        "    uniao.cod_pessoa as \"cod_empresa\" "+
                                                        "from " +
                                                        "    (select " +
                                                        "        produto.*, " +
                                                        "        empresa.* " +
                                                        "    from " +
                                                        "        produto cross join empresa "+ 
                                                        "    where "+ 
                                                        "        produto.descricao ILIKE :descProduto "+ sql +" ) as \"uniao\" " +
                                                        "    left JOIN " +
                                                        "         tabela_preco " +
                                                        "    on " +
                                                        "         uniao.codigo = tabela_preco.cod_produto and " +
                                                        "         uniao.cod_pessoa = tabela_preco.cod_empresa")
                                    .addEntity("tabela_preco", TabelaPreco.class);
        
        if(empresa != null && empresa.getCodigo() > 0 ) {
            query.setInteger("codEmpresa", empresa.getCodigo());
        }
        
        List<TabelaPreco> retorno = query.setString("descProduto", cWhere).list();
        
        // Ordena a lista por descrição do produto e preço
        //tabelaPreco.sort((t1, t2) -> t1.getProduto().getDescricao().compareTo(t2.getProduto().getDescricao()) );
        retorno.sort((t1, t2) -> t1.compareTo(t2) );
        
        return retorno;
    }
    
}
