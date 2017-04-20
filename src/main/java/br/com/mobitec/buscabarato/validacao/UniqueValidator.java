/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

/*import static de.hashcode.validation.ReflectionUtils.getIdField;
import static de.hashcode.validation.ReflectionUtils.getPropertyValue;*/

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sensum
 */
public class UniqueValidator implements ConstraintValidator<Unique, Serializable> {
 
    @Inject private EntityManager entityManager;
 
    private Unique constraintAnnotation;
 
    public UniqueValidator() {}
 
    public UniqueValidator(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }
 
    public EntityManager getEntityManager() {
        return entityManager;
    }
 
    @Override
    public void initialize(final Unique constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
 
    @Override
    public boolean isValid(final Serializable value,
            final ConstraintValidatorContext context) {
         
        if (entityManager == null || value == null) {
            
            return true;
        }
        
        Session session = (Session)getEntityManager().getDelegate();
        Object retorno = session.createCriteria( constraintAnnotation.table() )
                .add(Restrictions.eq(constraintAnnotation.field(), value))
                .uniqueResult();
       
        return retorno == null;    
    }
         
}