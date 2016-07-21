package is.gudmundur1.unitofworkdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class DbClient {
    public Connection getConnection() throws SQLException {
        String postgresUsername = "postgres";
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                postgresUsername,
                "mysecretpassword");

    }
}
