/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Indica que um campo é unico
 * @author Sensum
 */
@Constraint(validatedBy = { UniqueValidator.class })
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unique {
    
    String field();
    
    Class table();
 
    String message() default "Value is not unique";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
     
}
