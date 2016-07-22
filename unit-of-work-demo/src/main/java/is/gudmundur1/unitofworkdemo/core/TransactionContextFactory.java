package is.gudmundur1.unitofworkdemo.core;

@FunctionalInterface
public interface TransactionContextFactory {
    TransactionContext create();
}
