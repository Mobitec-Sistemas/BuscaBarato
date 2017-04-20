/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.util;

import java.lang.reflect.Method;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.proxy.Proxifier;
import br.com.caelum.vraptor.proxy.SuperMethod;

/**
 * Retorna o link de um controller
 * @author Sensum
 */
@RequestScoped
public class Linker {

    @Inject
    private Proxifier proxifier;
    @Inject
    private Router router;
    @Inject
    private HttpServletRequest request;
    private Class<?> clazz;
    private Method invokedMethod;
    private Object[] arguments;

    public String getURL() {
        return "https://" + request.getHeader("Host") + request.getContextPath() + getURI();
    }

    private String getURI() {
        String retorno = router.urlFor(clazz, invokedMethod, arguments);
        return retorno;
    }

    public <T> T buildLinkTo(Class<T> clazz) {
        this.clazz = clazz;
        return proxifier.proxify(clazz, (T proxy, Method method, Object[] args, SuperMethod superMethod) -> {
            invokedMethod = method;
            arguments = args;
            return null;
        });
    }
    
    public String buildLinkToStr(Class clazz) {
        this.buildLinkTo(clazz);
        
        return this.getURL();
    }
}
