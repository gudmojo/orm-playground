package is.gudmundur1.unitofworkdemo.core.persistence;

@FunctionalInterface
public interface TransactionContextFactory {
    TransactionContext create();
}
