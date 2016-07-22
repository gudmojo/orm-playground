package is.gudmundur1.unitofworkdemo.shell;

import is.gudmundur1.unitofworkdemo.core.CoreServiceRegistry;
import is.gudmundur1.unitofworkdemo.core.Department;
import is.gudmundur1.unitofworkdemo.core.Employee;
import is.gudmundur1.unitofworkdemo.core.persistence.MapperRegistry;
import is.gudmundur1.unitofworkdemo.postgres.PostgresClient;
import is.gudmundur1.unitofworkdemo.postgres.PostgresDepartmentDataMapper;
import is.gudmundur1.unitofworkdemo.postgres.PostgresEmployeeDataMapper;

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
