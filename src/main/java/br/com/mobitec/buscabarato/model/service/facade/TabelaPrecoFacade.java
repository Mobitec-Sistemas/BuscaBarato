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
    public List<TabelaPreco> listarProdutosEmpresa(Empresa empresa, Produto filtroProduto) {
        
        String cWhere = "produto.descricao ILIKE '%"+ filtroProduto.getDescricao().trim().replaceAll(" ", "%' and produto.descricao ILIKE '%") +"%'";
        
        //String cWhere = "%"+ filtroProduto.getDescricao().trim().replaceAll(" ", "%%") +"%";
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
                                                        //"        produto.descricao ILIKE :descProduto "+ sql +" ) as \"uniao\" " +
                                                        "        "+ cWhere +" "+ sql +" ) as \"uniao\" " +
                                                        "    left JOIN " +
                                                        "         tabela_preco " +
                                                        "    on " +
                                                        "         uniao.codigo = tabela_preco.cod_produto and " +
                                                        "         uniao.cod_pessoa = tabela_preco.cod_empresa")
                                    .addEntity("tabela_preco", TabelaPreco.class);
        
        if(empresa != null && empresa.getCodigo() > 0 ) {
            query.setInteger("codEmpresa", empresa.getCodigo());
        }
        
        //List<TabelaPreco> retorno = query.setString("descProduto", cWhere).list();
        List<TabelaPreco> retorno = query.list();
        
        // Ordena a lista por descrição do produto e preço
        retorno.sort((t1, t2) -> t1.compareTo(t2) );
        
        return retorno;
    }
    
}
