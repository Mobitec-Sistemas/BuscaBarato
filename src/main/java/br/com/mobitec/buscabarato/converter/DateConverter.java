/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.converter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

/**
 *
 * @author Sensum
 */
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Convert(Date.class)
public class DateConverter implements Converter<Date> {

    public static final String INVALID_MESSAGE_KEY = "is_not_a_valid_date";
    
    @Override
    public Date convert(String value, Class<? extends Date> type) {
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            return (Date) this.getFormat().parse(value);
        } catch (ParseException e) {
            throw new ConversionException(new ConversionMessage(INVALID_MESSAGE_KEY, value));
        }
    }
    
    public DateFormat getFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    }
}
