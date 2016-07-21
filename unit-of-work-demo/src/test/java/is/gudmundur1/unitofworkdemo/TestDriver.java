package is.gudmundur1.unitofworkdemo;

import java.util.Optional;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class TestDriver {

    DepartmentRepo departmentRepo = new DepartmentRepo(new DbClient());

    public void createDepartment() {
        UnitOfWork.newCurrent();
        Department department = Department.create(1L, "Sales");
        // todo: add employees to department

        UnitOfWork.getCurrent().commit();
    }

    public Optional<Department> findByName(String name) {
        return departmentRepo.findByName(name);
    }
}
