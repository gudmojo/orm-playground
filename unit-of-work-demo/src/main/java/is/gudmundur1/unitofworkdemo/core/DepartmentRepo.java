package is.gudmundur1.unitofworkdemo.core;

import java.util.Optional;

public interface DepartmentRepo {
    Optional<Department> findById(long id, boolean loadChildren);

    Optional<Department> findByName(String name);
}
