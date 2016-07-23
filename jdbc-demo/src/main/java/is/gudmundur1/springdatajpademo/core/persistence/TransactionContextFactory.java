package is.gudmundur1.springdatajpademo.core.persistence;

@FunctionalInterface
public interface TransactionContextFactory {
    TransactionContext create();
}
