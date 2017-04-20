/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Sensum
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
     
    private Email constraintAnnotation;
 
    public EmailValidator() {}
 
    
    @Override
    public void initialize(final Email constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
 
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        
        boolean retorno = true;
        
        if( value != null && !value.isEmpty() )
        {
            // Expressão Regular para validar E-mail
            Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
            Matcher m = p.matcher(value);
            retorno = m.matches();
        }
                
        return retorno;
    }
}
