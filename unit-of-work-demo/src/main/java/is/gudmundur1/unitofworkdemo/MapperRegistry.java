package is.gudmundur1.unitofworkdemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class MapperRegistry {

    public static Map<Class<? extends DomainObject>, AbstractDataMapper> map = new HashMap<>();
    public static AbstractDataMapper getMapper(Class<? extends DomainObject> clazz) {
        return null;
    }
}
