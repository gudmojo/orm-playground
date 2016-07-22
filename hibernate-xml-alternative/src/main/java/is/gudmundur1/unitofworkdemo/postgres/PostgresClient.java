package is.gudmundur1.unitofworkdemo.postgres;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresClient {

    public static final String dbUsername = "postgres";
    public static final String dbPassword = "mysecretpassword";

    private PoolingDataSource<PoolableConnection> dataSource;

    public PostgresClient() {
        this.dataSource = setupDataSource("jdbc:postgresql://localhost:5432/postgres");
    }

    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    private static PoolingDataSource<PoolableConnection> setupDataSource(String connectURI) {

        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(connectURI, dbUsername, dbPassword);

        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);

        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);

        poolableConnectionFactory.setPool(connectionPool);

        return new PoolingDataSource<>(connectionPool);
    }
}
