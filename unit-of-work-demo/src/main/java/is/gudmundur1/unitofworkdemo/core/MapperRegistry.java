package is.gudmundur1.unitofworkdemo.core;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private static Map<Class<? extends DomainObject>, AbstractDataMapper> map = new HashMap<>();

    public static AbstractDataMapper getMapper(Class<? extends DomainObject> clazz) {
        return map.get(clazz);
    }

    public static void put(Class<? extends DomainObject> clazz, AbstractDataMapper mapper) {
        map.put(clazz, mapper);
    }
}
