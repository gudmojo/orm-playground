package is.gudmundur1.unitofworkdemo.postgres;

import is.gudmundur1.unitofworkdemo.core.TransactionContext;

import java.sql.Connection;

public interface PostgresTransactionContext extends TransactionContext {

    Connection getPostgresConnection();
}
