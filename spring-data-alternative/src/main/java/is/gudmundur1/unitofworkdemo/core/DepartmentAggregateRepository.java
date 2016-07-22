package is.gudmundur1.unitofworkdemo.core;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface DepartmentAggregateRepository extends CrudRepository<DepartmentAggregate, Long> {

}
