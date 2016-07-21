package is.gudmundur1.unitofworkdemo.core;

import java.util.Optional;

/**
 * Created by gudmundur on 21.7.2016.
 */
public interface DepartmentRepo {
    Optional<Department> findById(long id, boolean loadChildren);

    Optional<Department> findByName(String name);
}
