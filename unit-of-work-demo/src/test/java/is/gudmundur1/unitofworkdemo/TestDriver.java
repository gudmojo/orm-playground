package is.gudmundur1.unitofworkdemo;

import java.util.Optional;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class TestDriver {

    DbClient dbClient;
    DepartmentRepo departmentRepo;

    public TestDriver() {
        this.dbClient = new DbClient();
        this.departmentRepo = new DepartmentRepo(dbClient);
    }

    public void createDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = Department.create(id, "Sales");
        // todo: add employees to department

        UnitOfWork.getCurrent().commit();
    }

    public void updateDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id).orElse(null);
        department.setName("Sales2");

        UnitOfWork.getCurrent().commit();
    }

    public Optional<Department> findByName(String name) {
        return departmentRepo.findByName(name);
    }

    public void cleanUpDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id).orElse(null);
        if (department != null) {
            department.markRemoved();
        }
        UnitOfWork.getCurrent().commit();
    }

    public DepartmentRepo getDepartmentRepo() {
        return departmentRepo;
    }
}
