package is.gudmundur1.hibernatexmldemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlStringParam extends SqlParam {

    String value;

    public SqlStringParam(String value) {
        super();
        this.value = value;
    }

    @Override
    public void fill(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setString(i, value);
    }
}
