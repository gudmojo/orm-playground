package is.gudmundur1.unitofworkdemo;

import is.gudmundur1.unitofworkdemo.core.*;
import is.gudmundur1.unitofworkdemo.postgres.PostgresClient;
import is.gudmundur1.unitofworkdemo.postgres.PostgresDepartmentRepo;
import is.gudmundur1.unitofworkdemo.postgres.PostgresEmployeeRepo;
import is.gudmundur1.unitofworkdemo.shell.Init;
import org.hibernate.Session;

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
        Init.init();
    }

    public void createDepartment(long id) {
        Session session = CoreServiceRegistry.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new Department(id, SALES_NAME));
        session.getTransaction().commit();
        session.close();
    }

    public void createDepartmentWithEmployees(long id, long id2, long id3) {
        Session session = CoreServiceRegistry.getSessionFactory().openSession();
        session.beginTransaction();
        Department department = new Department(id, SALES_NAME);
        session.save(department);
        session.save(new Employee(id2, department.getId(), "Bonnie"));
        session.save(new Employee(id3, department.getId(), "Clyde"));
        session.getTransaction().commit();
        session.close();
    }

    public void updateDepartment(long id) {
        Session session = CoreServiceRegistry.getSessionFactory().openSession();
        session.beginTransaction();
        Department department = departmentRepo.findById(id, false).orElse(null);
        department.setName(SALES_NAME_2);
        session.update(department);

        session.getTransaction().commit();
        session.close();
    }

    public void deleteDepartment(long id) {
        Session session = CoreServiceRegistry.getSessionFactory().openSession();
        session.beginTransaction();
        Department department = departmentRepo.findById(id, false).orElse(null);
        session.delete(department);

        session.getTransaction().commit();
        session.close();
    }

    public Optional<Department> findByName(String name) {
        return departmentRepo.findByName(name);
    }

    public void cleanUpDepartment(long id) {
        Session session = CoreServiceRegistry.getSessionFactory().openSession();
        session.beginTransaction();
        Department department = departmentRepo.findById(id, false).orElse(null);
        if (department != null) {
            session.delete(department);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void cleanUpEmployee(long id) {
        Session session = CoreServiceRegistry.getSessionFactory().openSession();
        session.beginTransaction();
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee != null) {
            session.delete(employee);
        }
        session.getTransaction().commit();
        session.close();
    }

    public DepartmentRepo getDepartmentRepo() {
        return departmentRepo;
    }
}
