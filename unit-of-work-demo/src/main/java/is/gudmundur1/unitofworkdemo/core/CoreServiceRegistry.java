package is.gudmundur1.unitofworkdemo.core;

public class CoreServiceRegistry {

    private static TransactionContextFactory transactionContextFactory;

    public static TransactionContextFactory getTransactionContextFactory() {
        return transactionContextFactory;
    }

    public static void setTransactionContextFactory(TransactionContextFactory transactionContextFactory) {
        CoreServiceRegistry.transactionContextFactory = transactionContextFactory;
    }
}
