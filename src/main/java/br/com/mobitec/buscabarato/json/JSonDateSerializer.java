/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.json;

import br.com.mobitec.buscabarato.converter.DateConverter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

/**
 *
 * @author Sensum
 */
@Dependent
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class JSonDateSerializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    @Inject
    private DateConverter dateConverter;

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        String dateString = getFormat().format(date);
        return new JsonPrimitive(dateString);
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return getFormat().parse(json.getAsString());
        } catch (ParseException e) {
            throw new JsonSyntaxException(json.getAsString(), e);
        }
    }

    protected DateFormat getFormat() {
        //return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return this.dateConverter.getFormat();
    }
}
