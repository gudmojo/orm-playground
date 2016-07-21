package is.gudmundur1.unitofworkdemo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.Optional;

public class DepartmentRepo {

    private DbClient dbClient;

    public DepartmentRepo(DbClient dbClient) {
        this.dbClient = dbClient;
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
