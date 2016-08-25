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
 * Indica que a chave estrangeira no banco de dados está como delete restrito
 * @author Sensum
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidaExclusao.class })
@Documented
public @interface DeleteRestrito {
    String message() default "Esta registro possui relacionamentos com outros registros";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
