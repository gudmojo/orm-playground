package is.gudmundur1.jdbcdemo.shell;

import is.gudmundur1.jdbcdemo.postgres.PostgresTransactionContext;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionContextImpl implements PostgresTransactionContext {

    Connection postgresConnection;

    public TransactionContextImpl(Connection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }

    @Override
    public Connection getPostgresConnection() {
        return postgresConnection;
    }

    @Override
    public void commit() {
        try {
            postgresConnection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        DbUtils.closeQuietly(postgresConnection);
    }
}
