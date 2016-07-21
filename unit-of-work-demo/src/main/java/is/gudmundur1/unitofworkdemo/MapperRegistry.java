package is.gudmundur1.unitofworkdemo;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    public static Map<Class<? extends DomainObject>, AbstractDataMapper> map;

    static {
        DbClient dbClient = new DbClient();
        map = new HashMap<>(2);
        map.put(Department.class, new DepartmentDataMapper(dbClient));
        map.put(Employee.class, new EmployeeDataMapper(dbClient));
    }

    public static AbstractDataMapper getMapper(Class<? extends DomainObject> clazz) {
        return map.get(clazz);
    }
}
