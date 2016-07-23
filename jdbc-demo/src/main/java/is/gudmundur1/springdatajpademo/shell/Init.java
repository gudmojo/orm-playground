package is.gudmundur1.springdatajpademo.shell;

import is.gudmundur1.jdbcdemo.postgres.PostgresClient;
import is.gudmundur1.jdbcdemo.postgres.PostgresEmployeeDataMapper;
import is.gudmundur1.springdatajpademo.core.CoreServiceRegistry;
import is.gudmundur1.springdatajpademo.core.Department;
import is.gudmundur1.springdatajpademo.core.Employee;
import is.gudmundur1.springdatajpademo.core.*;
import is.gudmundur1.springdatajpademo.core.persistence.MapperRegistry;
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
