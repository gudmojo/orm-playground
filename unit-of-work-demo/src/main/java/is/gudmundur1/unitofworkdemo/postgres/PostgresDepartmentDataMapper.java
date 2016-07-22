package is.gudmundur1.unitofworkdemo.postgres;

import is.gudmundur1.unitofworkdemo.core.AbstractDataMapper;
import is.gudmundur1.unitofworkdemo.core.Department;
import is.gudmundur1.unitofworkdemo.core.TransactionContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDepartmentDataMapper extends AbstractDataMapper<Department> {

    @Override
    public void insert(Department department, TransactionContext transactionContext) {
        String sql = "insert into departments(id, name) values (?, ?)";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(department.getId());
            sqlParams.addString(department.getName());
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
    public void update(Department department, TransactionContext transactionContext) {
        String sql = "update departments set name = ? where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addString(department.getName());
            sqlParams.addLong(department.getId());
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
    public void delete(Department department, TransactionContext transactionContext) {
        String sql = "delete from departments where id = ?";
        try {
            SqlParams sqlParams = new SqlParams();
            sqlParams.addLong(department.getId());
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
