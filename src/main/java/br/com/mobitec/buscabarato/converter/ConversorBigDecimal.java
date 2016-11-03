/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.converter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.BigDecimalConverter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

/**
 * Converte os tipos BigDecimal que vem do HTML
 *
 * @author Sensum
 */
@Specializes
@Convert(BigDecimal.class)
public class ConversorBigDecimal extends BigDecimalConverter {

    /**
     * @deprecated CDI eyes only
     */
    protected ConversorBigDecimal() {
        this(null);
    }
    
    @Inject
    public ConversorBigDecimal(Locale locale) {
        super(locale != null ? locale : java.util.Locale.getDefault());
    }

    @Override
    public BigDecimal convert(String value, Class<? extends BigDecimal> type) {
        // Troca o separador decimal
        value = value.replace('.', DecimalFormatSymbols.getInstance().getDecimalSeparator());

        return super.convert(value, type);
    }

}
