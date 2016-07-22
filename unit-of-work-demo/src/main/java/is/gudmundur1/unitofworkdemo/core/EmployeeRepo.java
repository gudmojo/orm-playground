package is.gudmundur1.unitofworkdemo.core;

import java.util.Optional;

public interface EmployeeRepo {
    Optional<Employee> findById(long id);
}
