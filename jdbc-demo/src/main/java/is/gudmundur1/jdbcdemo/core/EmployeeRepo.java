package is.gudmundur1.jdbcdemo.core;

import java.util.Optional;

public interface EmployeeRepo {
    Optional<Employee> findById(long id);
}
