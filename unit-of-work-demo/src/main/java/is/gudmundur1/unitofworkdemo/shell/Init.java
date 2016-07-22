package is.gudmundur1.unitofworkdemo.shell;

import is.gudmundur1.unitofworkdemo.core.*;
import is.gudmundur1.unitofworkdemo.postgres.DbClient;
import is.gudmundur1.unitofworkdemo.postgres.DepartmentDataMapper;
import is.gudmundur1.unitofworkdemo.postgres.EmployeeDataMapper;

import java.sql.SQLException;

public class Init {

    public static void init() {
        DbClient dbClient = new DbClient();
        CoreServiceRegistry.setTransactionContextFactory(() -> {
            try {
                return new TransactionContextImpl(dbClient.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        MapperRegistry.put(Department.class, new DepartmentDataMapper());
        MapperRegistry.put(Employee.class, new EmployeeDataMapper());
    }
}
