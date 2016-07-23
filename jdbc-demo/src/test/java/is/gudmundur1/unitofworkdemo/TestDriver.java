package is.gudmundur1.unitofworkdemo;

import is.gudmundur1.unitofworkdemo.core.*;
import is.gudmundur1.unitofworkdemo.core.persistence.UnitOfWork;
import is.gudmundur1.unitofworkdemo.postgres.PostgresClient;
import is.gudmundur1.unitofworkdemo.postgres.PostgresDepartmentRepo;
import is.gudmundur1.unitofworkdemo.postgres.PostgresEmployeeRepo;

import java.util.Optional;

public class TestDriver {

    public static final String SALES_NAME = "Sales";
    public static final String SALES_NAME_2 = "Sales2";

    private PostgresClient dbClient;
    private DepartmentRepo departmentRepo;
    private EmployeeRepo employeeRepo;

    public TestDriver() {
        this.dbClient = new PostgresClient();
        this.departmentRepo = new PostgresDepartmentRepo(dbClient);
        this.employeeRepo = new PostgresEmployeeRepo(dbClient);
    }

    public void createDepartment(long id) {
        UnitOfWork.newCurrent();
        Department.create(id, SALES_NAME);
        UnitOfWork.getCurrent().commit();
    }

    public void createDepartmentWithEmployees(long id, long id2, long id3) {
        UnitOfWork.newCurrent();
        Department department = Department.create(id, SALES_NAME);
        Employee.create(id2, department.getId(), "Bonnie");
        Employee.create(id3, department.getId(), "Clyde");
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
        department.delete();

        UnitOfWork.getCurrent().commit();
    }

    public Optional<Department> findByName(String name) {
        return departmentRepo.findByName(name);
    }

    public void cleanUpDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id, false).orElse(null);
        if (department != null) {
            department.delete();
        }
        UnitOfWork.getCurrent().commit();
    }

    public void cleanUpEmployee(long id) {
        UnitOfWork.newCurrent();
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee != null) {
            employee.delete();
        }
        UnitOfWork.getCurrent().commit();
    }

    public DepartmentRepo getDepartmentRepo() {
        return departmentRepo;
    }
}
