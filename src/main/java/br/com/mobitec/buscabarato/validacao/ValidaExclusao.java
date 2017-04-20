/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Sensum
 */
public class ValidaExclusao implements ConstraintValidator<DeleteRestrito, List> {

            
    @Override
    public void initialize(DeleteRestrito constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return value.isEmpty();
    }

    
}
