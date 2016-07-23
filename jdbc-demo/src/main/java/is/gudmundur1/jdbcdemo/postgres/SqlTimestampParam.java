package is.gudmundur1.jdbcdemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SqlTimestampParam extends SqlParam {

    Timestamp value;

    public SqlTimestampParam(Timestamp value) {
        super();
        this.value = value;
    }

    @Override
    public void fill(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setTimestamp(i, value);
    }
}
