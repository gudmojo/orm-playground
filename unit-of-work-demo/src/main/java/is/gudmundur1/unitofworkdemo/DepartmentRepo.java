package is.gudmundur1.unitofworkdemo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartmentRepo {

    private DbClient dbClient;

    public DepartmentRepo(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    public Optional<Department> findById(long id, boolean loadChildren) {
        try {
            String sql = "select id, name from departments where id = ?";
            ResultSetHandler<Department> handler = new BeanHandler<>(Department.class);
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = dbClient.getConnection();
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

    public Optional<Department> findByName(String name) {
        try {
            String sql = "select id, name from departments where name = ?";
            ResultSetHandler<Department> handler = new BeanHandler<>(Department.class);
            QueryRunner queryRunner = new QueryRunner();
            Department result = queryRunner.query(dbClient.getConnection(), sql, handler, name);
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
