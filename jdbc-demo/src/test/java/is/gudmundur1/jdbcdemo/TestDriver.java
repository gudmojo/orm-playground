package is.gudmundur1.jdbcdemo;

import is.gudmundur1.jdbcdemo.core.Department;
import is.gudmundur1.jdbcdemo.core.DepartmentRepo;
import is.gudmundur1.jdbcdemo.core.Employee;
import is.gudmundur1.jdbcdemo.core.EmployeeRepo;
import is.gudmundur1.jdbcdemo.core.persistence.UnitOfWork;
import is.gudmundur1.jdbcdemo.postgres.PostgresDepartmentRepo;
import is.gudmundur1.jdbcdemo.postgres.PostgresEmployeeRepo;

import java.util.Optional;

public class TestDriver {

    public static final String SALES_NAME = "Sales";
    public static final String SALES_NAME_2 = "Sales2";

    private DepartmentRepo departmentRepo;
    private EmployeeRepo employeeRepo;

    public TestDriver() {
        this.departmentRepo = new PostgresDepartmentRepo();
        this.employeeRepo = new PostgresEmployeeRepo();
    }

    public void createDepartment(long id) {
        UnitOfWork.newCurrent();
        Department.create(id, SALES_NAME);
        UnitOfWork.getCurrent().commit();
        UnitOfWork.getCurrent().getTransactionContext().close();
    }

    public void createDepartmentWithEmployees(long id, long id2, long id3) {
        UnitOfWork.newCurrent();
        Department department = Department.create(id, SALES_NAME);
        Employee.create(id2, department.getId(), "Bonnie");
        Employee.create(id3, department.getId(), "Clyde");
        UnitOfWork.getCurrent().commit();
        UnitOfWork.getCurrent().getTransactionContext().close();
    }

    public void updateDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id, false).orElse(null);
        department.setName(SALES_NAME_2);

        UnitOfWork.getCurrent().commit();
        UnitOfWork.getCurrent().getTransactionContext().close();
    }

    public void deleteDepartment(long id) {
        UnitOfWork.newCurrent();
        Department department = departmentRepo.findById(id, false).orElse(null);
        department.delete();

        UnitOfWork.getCurrent().commit();
        UnitOfWork.getCurrent().getTransactionContext().close();
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
        UnitOfWork.getCurrent().getTransactionContext().close();
    }

    public void cleanUpEmployee(long id) {
        UnitOfWork.newCurrent();
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee != null) {
            employee.delete();
        }
        UnitOfWork.getCurrent().commit();
        UnitOfWork.getCurrent().getTransactionContext().close();
    }

    public DepartmentRepo getDepartmentRepo() {
        return departmentRepo;
    }
}
