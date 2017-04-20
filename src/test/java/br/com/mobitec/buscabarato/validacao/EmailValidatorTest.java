/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import javax.validation.ConstraintValidatorContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sensum
 */
public class EmailValidatorTest {
    
    private EmailValidator email;
    
    public EmailValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        email = new EmailValidator();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testa se o Email é válido
     */
    @Test
    public void testIsValid_email_valido() {
        boolean result = email.isValid("fabiodematos@gmail.com", null);
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_email_valido2() {
        boolean result = email.isValid("mobitec.sistemas@gmail.com", null);
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_email_valido3() {        
        boolean result = email.isValid("mobitec.sistemas@gmail.com.br", null);
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_email_valido4() {
        boolean result = email.isValid("", null);
        assertTrue(result);
    }
    
        
    @Test
    public void testIsValid_email_invalido() { 
        boolean result = email.isValid("mobitec.sistemas@", null);
        assertFalse(result);
    }
    
    @Test
    public void testIsValid_email_invalido2() {
        boolean result = email.isValid("mobitec.sistemas", null);
        assertFalse(result);
    }
    
    @Test
    public void testIsValid_email_invalido3() {
        boolean result = email.isValid("mobitec.sistemas@gmail", null);
        assertFalse(result);
    }
    
    @Test
    public void testIsValid_email_invalido4() {
        boolean result = email.isValid("*?_tes\\/te-teste123@teste123.com", null);
        assertFalse(result);
    }
    
}
