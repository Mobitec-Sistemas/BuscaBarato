package br.com.mobitec.buscabarato.aspecto;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.validator.Validator;
import javax.inject.Inject;
import javax.persistence.EntityTransaction;

@Intercepts //(after = ControlaAcesso.class)
public class TransacoesInterceptor implements Interceptor {

    //@PersistenceContext(unitName = "default")
    @Inject
    private EntityManager manager;
    
    @Inject
    private Validator validator;

    
    @Override
    public boolean accepts(ControllerMethod method) {
        return method.containsAnnotation(Transacional.class);
    }

    @Override
    public void intercept(InterceptorStack stack, ControllerMethod method, Object controller) throws InterceptionException {
        
        EntityTransaction transaction = null;
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            stack.next(method, controller);
            
            if (!validator.hasErrors() && transaction.isActive())
                transaction.commit();
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

}
