package is.gudmundur1.hibernatexmldemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SqlIntegerParam extends SqlParam {

    Integer value;

    public SqlIntegerParam(Integer value) {
        super();
        this.value = value;
    }

    @Override
    public void fill(PreparedStatement preparedStatement, int i) throws SQLException {
        if (value != null) {
            preparedStatement.setInt(i, value);
        } else {
            preparedStatement.setNull(i, Types.INTEGER);
        }
    }
}
