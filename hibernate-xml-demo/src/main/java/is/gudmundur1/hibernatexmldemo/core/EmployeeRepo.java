package is.gudmundur1.hibernatexmldemo.core;

import java.util.Optional;

public interface EmployeeRepo {
    Optional<Employee> findById(long id);
}
