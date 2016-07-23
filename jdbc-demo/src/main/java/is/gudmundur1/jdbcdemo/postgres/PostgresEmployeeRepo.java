package is.gudmundur1.jdbcdemo.postgres;

import is.gudmundur1.jdbcdemo.core.Employee;
import is.gudmundur1.jdbcdemo.core.EmployeeRepo;
import is.gudmundur1.jdbcdemo.core.persistence.TransactionContext;
import is.gudmundur1.jdbcdemo.core.persistence.UnitOfWork;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class PostgresEmployeeRepo implements EmployeeRepo {

    @Override
    public Optional<Employee> findById(long id) {
        try {
            String sql = "select id, department_id, name from employees where id = ?";
            ResultSetHandler<Employee> handler = new BeanHandler<>(Employee.class);
            QueryRunner queryRunner = new QueryRunner();
            UnitOfWork unitOfWork = UnitOfWork.getCurrent();
            TransactionContext transactionContext = unitOfWork.getTransactionContext();
            PostgresTransactionContext postgresTransactionContext =
                    (PostgresTransactionContext) transactionContext;
            Connection connection = postgresTransactionContext.getPostgresConnection();
            Employee result = queryRunner.query(connection, sql, handler, id);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
