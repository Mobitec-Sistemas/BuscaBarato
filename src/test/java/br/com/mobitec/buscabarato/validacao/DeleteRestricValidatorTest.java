/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.validacao;

import br.com.caelum.vraptor.util.test.MockResult;
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
        MockResult result = new MockResult();
        
        DeleteRestricValidator instance = new DeleteRestricValidator(result);
        instance.validate(marca);
        
        assertNotNull(instance.getMensagem());
    }
    
    /**
     * Test of validate method, of class DeleteRestricValidator.
     */
    @Test
    public void testarMarcaComProduto() {
        Marca marca = new Marca();
        Produto produto = new Produto();
        marca.setProdutos( Arrays.asList(produto) );
        MockResult result = new MockResult();
        
        DeleteRestricValidator instance = new DeleteRestricValidator(result);
        instance.validate(marca);
        
        assertNotNull(instance.getMensagem());
    }
    
    @Test
    public void testarMarcaOK() {
        Marca marca = new Marca();
        
        MockResult result = new MockResult();
        
        DeleteRestricValidator instance = new DeleteRestricValidator(result);
        instance.validate(marca);
        
        assertNull(instance.getMensagem());
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
