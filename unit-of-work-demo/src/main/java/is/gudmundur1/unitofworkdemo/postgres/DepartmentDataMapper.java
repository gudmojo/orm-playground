package is.gudmundur1.unitofworkdemo.postgres;

import is.gudmundur1.unitofworkdemo.core.AbstractDataMapper;
import is.gudmundur1.unitofworkdemo.core.Department;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DepartmentDataMapper extends AbstractDataMapper<Department> {

    private DbClient dbClient;

    public DepartmentDataMapper(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public void insert(Department department) {
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
    public void update(Department department) {
        String sql = "update departments set name = ? where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addString(department.getName());
            sqlParams.addLong(department.getId());
            CallableStatement callableStatement = dbClient.getConnection().prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Department department) {
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
