package is.gudmundur1.jdbcdemo.postgres;

import org.postgresql.util.PGobject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqlParams {

    List<SqlParam> list;

    public SqlParams() {
        list = new ArrayList<>();
    }

    public void fill(PreparedStatement preparedStatement) throws SQLException {
        int i = 1;
        for (SqlParam param : list) {
            param.fill(preparedStatement, i);
            ++i;
        }
    }

    public void addString(String str) {
        list.add(new SqlStringParam(str));
    }

    public void addLong(Long value) {
        list.add(new SqlLongParam(value));
    }

    public void addInt(Integer value) {
        list.add(new SqlIntegerParam(value));
    }

    public void addTimestamp(Timestamp value) {
        list.add(new SqlTimestampParam(value));
    }

    public void addBoolean(boolean value) {
        list.add(new SqlBooleanParam(value));
    }

    public void addUUID(UUID value) throws SQLException {
        PGobject object = new PGobject();
        object.setType("uuid");
        object.setValue(value.toString());
        list.add(new SqlPgObjectParam(object));
    }
}
