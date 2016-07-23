package is.gudmundur1.unitofworkdemo.core;

import is.gudmundur1.unitofworkdemo.core.persistence.TransactionContextFactory;

public class CoreServiceRegistry {

    private static TransactionContextFactory transactionContextFactory;

    public static TransactionContextFactory getTransactionContextFactory() {
        return transactionContextFactory;
    }

    public static void setTransactionContextFactory(TransactionContextFactory transactionContextFactory) {
        CoreServiceRegistry.transactionContextFactory = transactionContextFactory;
    }
}
