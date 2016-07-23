package is.gudmundur1.jdbcdemo.postgres;

import is.gudmundur1.springdatajpademo.core.persistence.AbstractDataMapper;
import is.gudmundur1.springdatajpademo.core.Employee;
import is.gudmundur1.springdatajpademo.core.persistence.TransactionContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgresEmployeeDataMapper extends AbstractDataMapper<Employee> {

    @Override
    public void insert(Employee employee, TransactionContext transactionContext) {
        String sql = "insert into employees(id, department_id, name) values (?, ?, ?)";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(employee.getId());
            sqlParams.addLong(employee.getDepartmentId());
            sqlParams.addString(employee.getName());
            PostgresTransactionContext postgresTransactionContext = (PostgresTransactionContext) transactionContext;
            Connection connection = postgresTransactionContext.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee, TransactionContext transactionContext) {
        String sql = "update employees set name = ?, department_id = ? where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addString(employee.getName());
            sqlParams.addLong(employee.getDepartmentId());
            sqlParams.addLong(employee.getId());
            PostgresTransactionContext postgresTransactionContext = (PostgresTransactionContext) transactionContext;
            Connection connection = postgresTransactionContext.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Employee employee, TransactionContext transactionContext) {
        String sql = "delete from employees where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(employee.getId());
            PostgresTransactionContext postgresTransactionContext = (PostgresTransactionContext) transactionContext;
            Connection connection = postgresTransactionContext.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(sql);
            sqlParams.fill(callableStatement);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
