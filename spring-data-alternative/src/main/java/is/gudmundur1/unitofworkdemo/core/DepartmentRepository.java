package is.gudmundur1.unitofworkdemo.core;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DepartmentRepository extends CrudRepository<Department, Long> {

	List<Department> findByName(String name);
}
