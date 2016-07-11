/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Bairro;
import br.com.mobitec.buscabarato.model.Cidade;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.hibernate.Session;

/**
 *
 * @author Fabio
 */
@RequestScoped
@Named("bairro")
public class BairroFacade extends AbstractFacade<Bairro> {

    public BairroFacade() {
        super(Bairro.class);
    }

    public List<Bairro> listarBairroCidade(Integer codigo) {
        Session session = (Session)getEntityManager().getDelegate();
        
        String hql = "from Bairro b where b.cidade.codigo = :cidade";
        
        return session.createQuery(hql)
                .setParameter("cidade", codigo)
                .list();
    }
    
}
