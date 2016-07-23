package is.gudmundur1.jdbcdemo.postgres;

import is.gudmundur1.jdbcdemo.core.Department;
import is.gudmundur1.jdbcdemo.core.DepartmentRepo;
import is.gudmundur1.jdbcdemo.core.Employee;
import is.gudmundur1.jdbcdemo.core.persistence.UnitOfWork;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostgresDepartmentRepo implements DepartmentRepo {

    @Override
    public Optional<Department> findById(long id, boolean loadChildren) {
        try {
            String sql = "select id, name from departments where id = ?";
            ResultSetHandler<Department> handler = new BeanHandler<>(Department.class);
            QueryRunner queryRunner = new QueryRunner();
            PostgresTransactionContext transactionContext =
                    (PostgresTransactionContext) UnitOfWork.getCurrent().getTransactionContext();
            Connection connection = transactionContext.getPostgresConnection();
            Department result = queryRunner.query(connection, sql, handler, id);
            if (result == null) {
                return Optional.empty();
            }
            if (loadChildren) {
                loadEmployees(result, connection);
            }
            return Optional.of(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmployees(Department department, Connection connection) throws SQLException {
        String sql = "select id, department_id as departmentid, name from employees where department_id = ?";
        ResultSetHandler<List<Employee>> handler = new BeanListHandler<>(Employee.class);
        QueryRunner queryRunner = new QueryRunner();
        List<Employee> employees = queryRunner.query(connection, sql, handler, department.getId());
        department.setEmployeeList(employees);
    }

    @Override
    public Optional<Department> findByName(String name) {
        try {
            String sql = "select id, name from departments where name = ?";
            ResultSetHandler<Department> handler = new BeanHandler<>(Department.class);
            QueryRunner queryRunner = new QueryRunner();
            PostgresTransactionContext transactionContext =
                    (PostgresTransactionContext) UnitOfWork.getCurrent().getTransactionContext();
            Connection connection = transactionContext.getPostgresConnection();
            Department result = queryRunner.query(connection, sql, handler, name);
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
