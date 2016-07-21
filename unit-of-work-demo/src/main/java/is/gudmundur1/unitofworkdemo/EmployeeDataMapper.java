package is.gudmundur1.unitofworkdemo;

import is.gudmundur1.unitofworkdemo.postgres.SqlParams;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class EmployeeDataMapper extends AbstractDataMapper<Employee> {

    private DbClient dbClient;

    public EmployeeDataMapper(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public void insert(Employee employee) {
        String sql = "insert into employees(id, department_id, name) values (?, ?, ?)";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(employee.getId());
            sqlParams.addLong(employee.getDepartmentId());
            sqlParams.addString(employee.getName());
            CallableStatement callableStatement = dbClient.getConnection().prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "update employees set name = ?, department_id = ? where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addString(employee.getName());
            sqlParams.addLong(employee.getDepartmentId());
            sqlParams.addLong(employee.getId());
            CallableStatement callableStatement = dbClient.getConnection().prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Employee employee) {
        String sql = "delete from employees where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(employee.getId());
            CallableStatement callableStatement = dbClient.getConnection().prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
