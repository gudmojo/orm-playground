package is.gudmundur1.unitofworkdemo.shell;

import is.gudmundur1.unitofworkdemo.core.CoreServiceRegistry;

import javax.persistence.Persistence;

public class Init {

    public static void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        CoreServiceRegistry.setSessionFactory(Persistence.createEntityManagerFactory("is.gudmundur1.unitofworkdemo.core"));
    }
}
