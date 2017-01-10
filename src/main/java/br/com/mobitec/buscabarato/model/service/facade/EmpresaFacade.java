/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model.service.facade;

import br.com.mobitec.buscabarato.model.Empresa;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

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
    
    /**
     * Lista as empresas ordenadas pela latitude e longitude
     * @param latitude
     * @param longitude
     * @return 
     */
    public List<Empresa> listar(BigDecimal latitude, BigDecimal longitude) {
        List<Empresa> empresas;
        
        Query query = this.getSession().createSQLQuery("select *, \"CalcularDistancia\"(?, ?, empresa.latitude, empresa.longitude) as \"distancia\" from empresa inner join pessoa on empresa.cod_pessoa = pessoa.codigo order by distancia, nome")
                .addEntity(Empresa.class)
                .setBigDecimal(0, latitude)
                .setBigDecimal(1, longitude);
                //.setResultTransformer(Transformers.aliasToBean(Empresa.class));
        
        empresas = query.list();
        
        return empresas;
    }
    
}
