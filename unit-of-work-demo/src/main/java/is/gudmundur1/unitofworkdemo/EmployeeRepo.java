package is.gudmundur1.unitofworkdemo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeRepo {

    private DbClient dbClient;

    public EmployeeRepo(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    public Optional<Employee> findById(long id) {
        try {
            String sql = "select id, department_id, name from employees where id = ?";
            ResultSetHandler<Employee> handler = new BeanHandler<>(Employee.class);
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = dbClient.getConnection();
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
