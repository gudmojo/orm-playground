package is.gudmundur1.jdbcdemo.postgres;

import is.gudmundur1.jdbcdemo.core.persistence.TransactionContext;

import java.sql.Connection;

public interface PostgresTransactionContext extends TransactionContext {

    Connection getPostgresConnection();
}
