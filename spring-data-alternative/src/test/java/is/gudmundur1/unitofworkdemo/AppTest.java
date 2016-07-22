package is.gudmundur1.unitofworkdemo;

import is.gudmundur1.unitofworkdemo.core.*;
import is.gudmundur1.unitofworkdemo.shell.Init;
import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static is.gudmundur1.unitofworkdemo.TestDriver.SALES_NAME;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {


    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private TestDriver testDriver;

    static {
        Init.init();
    }

    @Test
    public void createDbSchema() {

        String postgresUsername = "postgres";
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:postgresql://localhost:5432/postgres", postgresUsername, "mysecretpassword");
        flyway.migrate();
    }

    @Test
    public void createDepartmentAndReadItBack() {

        long deptId = 1L;
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartment(deptId);
        Department department = departmentRepository.findOne(deptId);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES_NAME));
    }

    @Test
    public void updateDepartmentAndReadItBack() {
        System.out.print("g1");
        long deptId = 2L;
        testDriver.cleanUpDepartment(deptId);

        System.out.print("g1");
        testDriver.createDepartment(deptId);
        testDriver.updateDepartment(deptId);

        Department department = departmentRepository.findOne(deptId);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(TestDriver.SALES_NAME_2));
    }

    @Test
    public void deleteDepartmentAndReadItBack() {

        long deptId = 3L;
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartment(deptId);
        testDriver.deleteDepartment(deptId);

        Department department = departmentRepository.findOne(deptId);
        assertThat(department, is(nullValue()));
    }

    @Test
    public void createDepartmentWithEmployeesAndReadItBack() {

        long deptId = 4L;
        testDriver.cleanUpEmployee(1L);
        testDriver.cleanUpEmployee(2L);
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartmentWithEmployees(deptId, 1L, 2L);
        Department department = departmentRepository.findOne(deptId);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES_NAME));
        List<Employee> employeeList = employeeRepository.findByDepartment(department);
        assertThat(employeeList, is(notNullValue()));
        assertThat(employeeList.size(), is(2));
        Employee bonnie = employeeList.stream()
                .filter(employee -> "Bonnie".equals(employee.getName())).findFirst().get();
        assertThat(bonnie.getName(), is("Bonnie"));
        assertThat(bonnie.getDepartment().getId(), is(deptId));
        Employee clyde = employeeList.stream()
                .filter(employee -> "Bonnie".equals(employee.getName())).findFirst().get();
        assertThat(clyde.getName(), is("Bonnie"));
        assertThat(clyde.getDepartment().getId(), is(deptId));
    }

    @Test
    public void rollbackOnError() {

        long deptId1 = 5L;
        long deptId2 = 6L;
        testDriver.cleanUpDepartment(deptId1);
        testDriver.cleanUpDepartment(deptId2);

        try {
            testDriver.testRollback(deptId1, deptId2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Department department = departmentRepository.findOne(deptId1);
        assertThat(department, is(nullValue()));
    }

    @Test
    public void updateEmployeeViaDepartment() {

        long deptId = 7L;
        testDriver.cleanUpEmployee(3L);
        testDriver.cleanUpEmployee(4L);
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartmentWithEmployees(deptId, 3L, 4L);
        Department department = departmentRepository.findOne(deptId);
        List<Employee> employeeList = employeeRepository.findByDepartment(department);
        System.out.println("begin");
        Employee bonnie = employeeList.stream()
                .filter(employee -> "Bonnie".equals(employee.getName())).findFirst().get();
        bonnie.setName("Bonnie2");
        employeeRepository.save(bonnie);
        assertThat(employeeList, is(notNullValue()));
        assertThat(employeeList.size(), is(2));
        Employee bonnie2 = employeeList.stream()
                .filter(employee -> "Bonnie2".equals(employee.getName())).findFirst().get();
        assertThat(bonnie2.getName(), is("Bonnie2"));
        assertThat(bonnie2.getDepartment().getId(), is(deptId));
        Employee clyde = employeeList.stream()
                .filter(employee -> "Clyde".equals(employee.getName())).findFirst().get();
        assertThat(clyde.getName(), is("Clyde"));
        assertThat(clyde.getDepartment().getId(), is(deptId));
    }

}
