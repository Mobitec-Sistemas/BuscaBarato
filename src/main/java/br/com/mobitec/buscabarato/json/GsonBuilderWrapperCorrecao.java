/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.json;

import br.com.caelum.vraptor.core.ReflectionProvider;
import static br.com.caelum.vraptor.proxy.CDIProxies.extractRawTypeIfPossible;
import br.com.caelum.vraptor.serialization.Serializee;
import br.com.caelum.vraptor.serialization.gson.GsonBuilderWrapper;
import com.google.gson.Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

/**
 *
 * @author Sensum
 */
@Specializes
@Dependent
public class GsonBuilderWrapperCorrecao extends GsonBuilderWrapper {

    /**
     * @deprecated CDI eyes only
     */
    protected GsonBuilderWrapperCorrecao() {
        this(null, null, null, null);
    }

    @Inject
    public GsonBuilderWrapperCorrecao(final @Any Instance<com.google.gson.JsonSerializer<?>> jsonSerializers,
            final @Any javax.enterprise.inject.Instance<com.google.gson.JsonDeserializer<?>> jsonDeserializers,
            Serializee serializee,
            ReflectionProvider reflectionProvider) {

        
        super(jsonSerializers, jsonDeserializers, serializee, reflectionProvider);
    }

    @Override
    public Gson create() {

        JSonByteSerializer jByte = new JSonByteSerializer();
        registerAdapter(getAdapterType(jByte), jByte);
        
        JSonDateSerializer jDate = new JSonDateSerializer();
        registerAdapter(getAdapterType(jDate), jDate);
        
        return super.create();
    }

    private Class<?> getAdapterType(Object adapter) {
        final Class<?> klazz = extractRawTypeIfPossible(adapter.getClass());
        final Type[] genericInterfaces = klazz.getGenericInterfaces();
        final ParameterizedType type = (ParameterizedType) genericInterfaces[0];
        final Type actualType = type.getActualTypeArguments()[0];

        if (actualType instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) actualType).getRawType();
        } else {
            return (Class<?>) actualType;
        }
    }
}
