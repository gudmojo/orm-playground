package is.gudmundur1.springdatajpademo.core;

import javax.persistence.EntityManagerFactory;

public class CoreServiceRegistry {

    private static EntityManagerFactory sessionFactory;

    public static void setSessionFactory(EntityManagerFactory sessionFactory) {
        CoreServiceRegistry.sessionFactory = sessionFactory;
    }

    public static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }
}
