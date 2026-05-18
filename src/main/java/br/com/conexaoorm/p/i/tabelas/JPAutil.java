package br.com.conexaoorm.p.i.tabelas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAutil {

    private static final String PERSISTENCE_UNIT = "MyStockSystem-PU";
    private EntityManagerFactory fabrica;
    private EntityManager manager = null;

    public EntityManager getEntityManager() {

        if (fabrica == null || !fabrica.isOpen()) {
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }

        if (manager == null || !manager.isOpen()) {
            manager = fabrica.createEntityManager();
        }

        return manager;

    }

    public void getCloseEntityManager() {

        if (manager != null && manager.isOpen()) {
            manager.close();
            manager = null;
        }

    }

}
