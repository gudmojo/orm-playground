package is.gudmundur1.jdbcdemo.core.persistence;

@FunctionalInterface
public interface TransactionContextFactory {
    TransactionContext create();
}
