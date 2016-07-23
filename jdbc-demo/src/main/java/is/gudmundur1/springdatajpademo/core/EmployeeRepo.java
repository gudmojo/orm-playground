package is.gudmundur1.springdatajpademo.core;

import java.util.Optional;

public interface EmployeeRepo {
    Optional<Employee> findById(long id);
}
