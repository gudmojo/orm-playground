package is.gudmundur1.jdbcdemo.core.persistence;

import is.gudmundur1.jdbcdemo.core.DomainObject;

public abstract class AbstractDataMapper<T extends DomainObject> {

    public abstract void insert(T obj, TransactionContext transactionContext);

    public abstract void update(T obj, TransactionContext transactionContext);

    public abstract void delete(T obj, TransactionContext transactionContext);
}
