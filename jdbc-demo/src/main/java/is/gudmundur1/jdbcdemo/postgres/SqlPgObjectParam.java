package is.gudmundur1.jdbcdemo.postgres;

import org.postgresql.util.PGobject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlPgObjectParam extends SqlParam {

    PGobject value;

    public SqlPgObjectParam(PGobject value) {
        super();
        this.value = value;
    }

    @Override
    public void fill(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setObject(i, value);
    }
}
