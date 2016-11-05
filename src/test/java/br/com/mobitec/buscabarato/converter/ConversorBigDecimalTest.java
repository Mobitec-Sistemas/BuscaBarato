/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.converter;

import java.math.BigDecimal;
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
public class ConversorBigDecimalTest {
    
    private ConversorBigDecimal conversor;
    
    public ConversorBigDecimalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        conversor = new ConversorBigDecimal(java.util.Locale.getDefault());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convert method, of class ConversorBigDecimal.
     */
    @Test
    public void testConvertInteiroPositivo() {
        
        BigDecimal retorno = conversor.convert("123", BigDecimal.class);
        
        assertEquals(new BigDecimal(123), retorno);
    }
    
    @Test
    public void testConvertInteiroNegativo() {
        
        BigDecimal retorno = conversor.convert("-123", BigDecimal.class);
        
        assertEquals(new BigDecimal(-123), retorno);
    }
    
    @Test
    public void testConvertDecimalPontoPositivo() {
        
        BigDecimal convertido = conversor.convert("123.456", BigDecimal.class);
        BigDecimal resultado = new BigDecimal("123.456");
                
        assertEquals(resultado, convertido);
    }
    
    @Test
    public void testConvertDecimalPontoNegativo() {
        
        BigDecimal convertido = conversor.convert("-26.911877", BigDecimal.class);
        BigDecimal resultado = new BigDecimal("-26.911877");
                
        assertEquals(resultado, convertido);
    }
    
    @Test
    public void testConvertDecimalVirgulaPositivo() {
        
        BigDecimal convertido = conversor.convert("123,456", BigDecimal.class);
        BigDecimal resultado = new BigDecimal("123.456");
                
        assertEquals(resultado, convertido);
        assertTrue( convertido.compareTo(new BigDecimal("123")) == 1 );
        assertTrue( convertido.compareTo(new BigDecimal("124")) == -1 );
    }
    
    @Test
    public void testConvertDecimalVirgulaNegativo() {
        
        BigDecimal convertido = conversor.convert("-123,456", BigDecimal.class);
        BigDecimal resultado = new BigDecimal("-123.456");
            
        assertEquals(resultado, convertido);
    }
    
}
