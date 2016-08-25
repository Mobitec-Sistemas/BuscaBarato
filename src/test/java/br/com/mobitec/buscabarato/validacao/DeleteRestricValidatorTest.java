/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.mobitec.buscabarato.model.Marca;
import br.com.mobitec.buscabarato.model.Produto;
import java.util.Arrays;
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
public class DeleteRestricValidatorTest {
    
    private DeleteRestricValidator deleteValidator;
    
    public DeleteRestricValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockResult result = new MockResult();
        MockValidator validate = new MockValidator();
        this.deleteValidator = new DeleteRestricValidator(result, validate);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validate method, of class DeleteRestricValidator.
     */
    @Test
    public void testarMarcaNaoEncontrada() {
        Marca marca = null;
        
        this.deleteValidator.validate(marca);
        
        assertNotNull(this.deleteValidator.getMensagem());
    }
    
    /**
     * Test of validate method, of class DeleteRestricValidator.
     */
    @Test
    public void testarMarcaComProduto() {
        Marca marca = new Marca();
        Produto produto = new Produto();
        marca.setProdutos( Arrays.asList(produto) );
        
        this.deleteValidator.validate(marca);
        
        assertNotNull(this.deleteValidator.getMensagem());
    }
    
    @Test
    public void testarMarcaOK() {
        Marca marca = new Marca();
                
        this.deleteValidator.validate(marca);
        
        assertNull(this.deleteValidator.getMensagem());
    }

    /**
     * Test of onErrorSendErrorRequest method, of class DeleteRestricValidator.
     */
    @Test
    public void testOnErrorSendErrorRequest() {
        System.out.println("onErrorSendErrorRequest");
        DeleteRestricValidator instance = new DeleteRestricValidator();
        instance.onErrorSendErrorRequest();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
