package is.gudmundur1.unitofworkdemo.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SqlParam {

    public abstract void fill(PreparedStatement preparedStatement, int i) throws SQLException;
}
