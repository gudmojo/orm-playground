package is.gudmundur1.unitofworkdemo;

import java.util.Optional;

public class TestDriver {

    public static final String SALES_NAME = "Sales";
    public static final String SALES_NAME_2 = "Sales2";

    DbClient dbClient;
    DepartmentRepo departmentRepo;
    EmployeeRepo employeeRepo;

    public TestDriver() {
        this.dbClient = new DbClient();
        this.departmentRepo = new DepartmentRepo(dbClient);
        this.employeeRepo = new EmployeeRepo(dbClient);
    }

    public void createDepartment(long id) {
        UnitOfWork.newCurrent();
        Department.create(id, SALES_NAME);
        UnitOfWork.getCurrent().commit();
    }

    public void createDepartmentWithEmployees(long id) {
        UnitOfWork.newCurrent();
        Department department = Department.create(id, SALES_NAME);
        Employee.create(1L, department.getId(), "Bonnie");
        Employee.create(2L, department.getId(), "Clyde");
        UnitOfWork.getCurrent().commit();
    }

    public void updateDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id, false).orElse(null);
        department.setName(SALES_NAME_2);

        UnitOfWork.getCurrent().commit();
    }

    public void deleteDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id, false).orElse(null);
        department.markRemoved();

        UnitOfWork.getCurrent().commit();
    }

    public Optional<Department> findByName(String name) {
        return departmentRepo.findByName(name);
    }

    public void cleanUpDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id, false).orElse(null);
        if (department != null) {
            department.markRemoved();
        }
        UnitOfWork.getCurrent().commit();
    }

    public void cleanUpEmployee(long id) {
        UnitOfWork.newCurrent();
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee != null) {
            employee.markRemoved();
        }
        UnitOfWork.getCurrent().commit();
    }

    public DepartmentRepo getDepartmentRepo() {
        return departmentRepo;
    }
}
