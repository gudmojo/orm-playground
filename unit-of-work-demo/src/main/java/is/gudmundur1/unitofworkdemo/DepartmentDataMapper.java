package is.gudmundur1.unitofworkdemo;

import is.gudmundur1.unitofworkdemo.postgres.SqlParams;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DepartmentDataMapper extends AbstractDataMapper {

    private DbClient dbClient;

    public DepartmentDataMapper(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public void insert(DomainObject obj) {
        Department department = (Department) obj;
        String sql = "insert into departments(id, name) values (?, ?)";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(department.getId());
            sqlParams.addString(department.getName());
            CallableStatement callableStatement = dbClient.getConnection().prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DomainObject obj) {
        Department department = (Department) obj;
        throw new RuntimeException("not implemented");
    }

    @Override
    public void delete(DomainObject obj) {
        Department department = (Department) obj;
        String sql = "delete from departments where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(department.getId());
            CallableStatement callableStatement = dbClient.getConnection().prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
