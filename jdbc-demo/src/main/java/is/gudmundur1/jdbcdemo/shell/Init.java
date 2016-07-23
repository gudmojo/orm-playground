package is.gudmundur1.jdbcdemo.shell;

import is.gudmundur1.jdbcdemo.postgres.PostgresClient;
import is.gudmundur1.jdbcdemo.postgres.PostgresEmployeeDataMapper;
import is.gudmundur1.jdbcdemo.core.CoreServiceRegistry;
import is.gudmundur1.jdbcdemo.core.Department;
import is.gudmundur1.jdbcdemo.core.Employee;
import is.gudmundur1.jdbcdemo.core.persistence.MapperRegistry;
import is.gudmundur1.jdbcdemo.postgres.PostgresDepartmentDataMapper;

import java.sql.SQLException;

public class Init {

    public static void init() {
        PostgresClient dbClient = new PostgresClient();
        CoreServiceRegistry.setTransactionContextFactory(() -> {
            try {
                return new TransactionContextImpl(dbClient.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        MapperRegistry.put(Department.class, new PostgresDepartmentDataMapper());
        MapperRegistry.put(Employee.class, new PostgresEmployeeDataMapper());
    }
}
