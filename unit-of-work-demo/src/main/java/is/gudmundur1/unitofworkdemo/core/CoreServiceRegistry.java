package is.gudmundur1.unitofworkdemo.core;

/**
 * Created by gudmundur on 22.7.2016.
 */
public class CoreServiceRegistry {

    private static TransactionContextFactory transactionContextFactory;

    public static TransactionContextFactory getTransactionContextFactory() {
        return transactionContextFactory;
    }

    public static void setTransactionContextFactory(TransactionContextFactory transactionContextFactory) {
        CoreServiceRegistry.transactionContextFactory = transactionContextFactory;
    }
}
