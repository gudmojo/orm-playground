package is.gudmundur1.unitofworkdemo.core;

import org.hibernate.SessionFactory;

public class CoreServiceRegistry {

    private static SessionFactory sessionFactory;

    public static void setSessionFactory(SessionFactory sessionFactory) {
        CoreServiceRegistry.sessionFactory = sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
