package is.gudmundur1.hibernatexmldemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SqlParam {

    public abstract void fill(PreparedStatement preparedStatement, int i) throws SQLException;
}
