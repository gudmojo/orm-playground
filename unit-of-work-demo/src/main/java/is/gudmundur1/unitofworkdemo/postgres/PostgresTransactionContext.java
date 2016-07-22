package is.gudmundur1.unitofworkdemo.postgres;

import is.gudmundur1.unitofworkdemo.core.persistence.TransactionContext;

import java.sql.Connection;

public interface PostgresTransactionContext extends TransactionContext {

    Connection getPostgresConnection();
}
