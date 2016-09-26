package br.com.mobitec.buscabarato.model.service.facade;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class FabricaDeEntityManager {

    //@PersistenceContext(unitName = "default")
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private EntityManager manager = factory.createEntityManager();

    /**
     * @deprecated CDI eyes only
     */
    protected FabricaDeEntityManager() {   
    }

    /**
     * @return uma instância do EntityManager
     */
    @Produces @ApplicationScoped
    public EntityManager getEntityManager() {
        
        if(!this.manager.isOpen())
            this.manager = factory.createEntityManager();
        
        return this.manager;
    }

    @PreDestroy
    public void fechaManager() {
        this.manager.close();
    }

}
