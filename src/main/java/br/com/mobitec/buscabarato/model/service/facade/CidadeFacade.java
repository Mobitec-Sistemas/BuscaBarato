/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Cidade;
import br.com.mobitec.buscabarato.model.Estado;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

/**
 *
 * @author Fabio
 */
@RequestScoped
@Named("cidade")
public class CidadeFacade extends AbstractFacade<Cidade> {

    public CidadeFacade() {
        super(Cidade.class);
    }

    /**
     * Lista as cidade de um determinado estado
     * @param estado
     * @return Lista de cidades
     */
    public List<Cidade> listarCidadeEstado(int estado) {
        Session session = this.getSession();
        
        String hql = "from Cidade c where c.estado.codigo = :nEstado";
        
        return session.createQuery(hql)
                .setParameter("nEstado", estado)
                .list();
        
    }

    /**
     * Lista as cidade de acordo com o filtro infomado
     * @param cidade
     * @return lista de cidades
     */
    public List<Cidade> listarCidade(Cidade cidade) {
        Example exampleCidade = Example.create(cidade)
                                        .enableLike(MatchMode.ANYWHERE)
                                        .ignoreCase();
        
        Criteria criteria = this.getSession().createCriteria(Cidade.class).add(exampleCidade);
        
        if( cidade.getEstado() != null ) {
            Example exampleEstado = Example.create(cidade.getEstado()).ignoreCase();
            criteria.createCriteria("estado").add(exampleEstado);
        }
        
        List<Cidade> cidades = criteria.list();
        
        return cidades;
    }

    public List<Cidade> findCidadesEstado(Integer codEstado) {
        Estado estado = new Estado();
        estado.setCodigo(codEstado);
        
        EntityManager manager = getEntityManager();
        
        List<Cidade> listaCidade = manager
                .createQuery("select t from Cidade as t where t.estado = :codigoEstado order by t.nome")
                .setParameter("codigoEstado", estado)
                .getResultList();
                
        return listaCidade;
    }
    
}
