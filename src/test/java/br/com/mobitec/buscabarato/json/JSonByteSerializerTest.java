/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.json;

import br.com.mobitec.buscabarato.model.Produto;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import java.text.DateFormat;
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
public class JSonByteSerializerTest {
    
    public JSonByteSerializerTest() {
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
     * Test of serialize method, of class JSonByteSerializer.
     */
    @Test
    public void testSerialize() {
        /*GsonBuilder gson = new GsonBuilder();
        
        gson.registerTypeAdapter(byte[].class, new JSonByteSerializer());
        Produto prod = new Produto();
        prod.setImagem(new byte[5]);
        String retorno = gson.create().toJson(prod);
        assertEquals(retorno, "{\"imagem\":\"AAAAAAA\\u003d\"}");*/
    }


}
