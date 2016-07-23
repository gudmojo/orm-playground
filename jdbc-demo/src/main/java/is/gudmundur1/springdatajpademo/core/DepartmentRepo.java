package is.gudmundur1.springdatajpademo.core;

import java.util.Optional;

public interface DepartmentRepo {
    Optional<Department> findById(long id, boolean loadChildren);

    Optional<Department> findByName(String name);
}
