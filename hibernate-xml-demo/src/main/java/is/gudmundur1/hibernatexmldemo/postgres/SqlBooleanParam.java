package is.gudmundur1.hibernatexmldemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlBooleanParam extends SqlParam {

    /**
     * The Value.
     */
    boolean value;

    /**
     * Instantiates a new Sql boolean param.
     *
     * @param value the value
     */
    public SqlBooleanParam(boolean value) {
        super();
        this.value = value;
    }

    @Override
    public void fill(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setBoolean(i, value);
    }
}
