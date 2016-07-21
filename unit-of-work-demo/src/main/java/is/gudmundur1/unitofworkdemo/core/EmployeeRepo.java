package is.gudmundur1.unitofworkdemo.core;

import java.util.Optional;

/**
 * Created by gudmundur on 21.7.2016.
 */
public interface EmployeeRepo {
    Optional<Employee> findById(long id);
}
