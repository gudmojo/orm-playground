package is.gudmundur1.hibernatexmldemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SqlLongParam extends SqlParam {

    Long value;

    public SqlLongParam(Long value) {
        super();
        this.value = value;
    }

    @Override
    public void fill(PreparedStatement preparedStatement, int i) throws SQLException {
        if (value != null) {
            preparedStatement.setLong(i, value);
        } else {
            preparedStatement.setNull(i, Types.BIGINT);
        }
    }
}
