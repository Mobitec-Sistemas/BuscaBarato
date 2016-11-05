/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.converter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.BigDecimalConverter;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;
import static com.google.common.base.Strings.isNullOrEmpty;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import org.apache.log4j.Logger;

/**
 * Converte os tipos BigDecimal que vem do HTML
 *
 * @author Sensum
 */
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Convert(BigDecimal.class)
public class ConversorBigDecimal implements Converter<BigDecimal> {

    private static final String INVALID_MESSAGE_KEY = "is_not_a_valid_number";

    private final Locale locale;

    private static final Logger logger = Logger.getLogger(ConversorBigDecimal.class);

    /**
     * @deprecated CDI eyes only
     */
    protected ConversorBigDecimal() {
        this(null);
    }

    @Inject
    public ConversorBigDecimal(Locale locale) {
        if (locale == null) {
            locale = java.util.Locale.getDefault();
        }

        this.locale = locale;
    }

    @Override
    public BigDecimal convert(String value, Class<? extends BigDecimal> type) {
        if (isNullOrEmpty(value)) {
            return null;
        }

        try {
            // Troca o separador decimal
            value = value.replace('.', DecimalFormatSymbols.getInstance(this.locale).getDecimalSeparator());

            return (BigDecimal) getNumberFormat().parse(value);
        } catch (ParseException e) {
            throw new ConversionException(new ConversionMessage(INVALID_MESSAGE_KEY, value));
        }
    }

    protected NumberFormat getNumberFormat() {
        DecimalFormat fmt = (DecimalFormat) DecimalFormat.getInstance(locale);
        fmt.setParseBigDecimal(true);
        return fmt;
    }

}
