package is.gudmundur1.jdbcdemo.core.persistence;

public interface TransactionContext {
    void commit();

    void close();
}
