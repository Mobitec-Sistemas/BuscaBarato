/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Marca;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

/**
 *
 * @author Sensum
 */
@RequestScoped
public class MarcaFacade extends AbstractFacade<Marca> {
    
    public MarcaFacade() {
        super(Marca.class);
    }

    /**
     * Lista as marcas de acordo com o filtro infomado
     * @param marca
     * @return lista de marcas
     */
    public List<Marca> listarMarca(Marca marca) {
        Example exampleMarca = Example.create(marca)
                                        .enableLike(MatchMode.ANYWHERE)
                                        .ignoreCase();
        
        Criteria criteria = this.getSession().createCriteria(Marca.class).add(exampleMarca);
                
        List<Marca> marcas = criteria.list();
        
        return marcas;
    }
     
}
