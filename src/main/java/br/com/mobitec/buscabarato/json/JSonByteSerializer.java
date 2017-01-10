/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Base64;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

/**
 *
 * @author Sensum
 */
@Dependent
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class JSonByteSerializer implements JsonSerializer<byte[]>, JsonDeserializer<byte[]>  {

    @Override
    public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
        String dados = "";
        if(src != null)
            dados = Base64.getEncoder().encodeToString(src);
        
        return new JsonPrimitive(dados);
    }

    @Override
    public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String base64 = json.getAsString();
        return Base64.getDecoder().decode(base64);
    }
    
}
