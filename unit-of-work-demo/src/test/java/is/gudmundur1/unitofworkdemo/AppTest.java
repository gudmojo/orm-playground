package is.gudmundur1.unitofworkdemo;

import is.gudmundur1.unitofworkdemo.core.Department;
import is.gudmundur1.unitofworkdemo.core.Employee;
import is.gudmundur1.unitofworkdemo.core.UnitOfWork;
import is.gudmundur1.unitofworkdemo.shell.Init;
import org.flywaydb.core.Flyway;
import org.junit.Test;

import java.util.List;

import static is.gudmundur1.unitofworkdemo.TestDriver.SALES_NAME;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AppTest {

    private static final String TOO_LONG_DEPT_NAME = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
    private TestDriver testDriver = new TestDriver();

    public AppTest() {
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
        Department department = testDriver.getDepartmentRepo().findById(deptId, false).orElse(null);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES_NAME));
    }

    @Test
    public void updateDepartmentAndReadItBack() {

        long deptId = 2L;
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartment(deptId);
        testDriver.updateDepartment(deptId);

        Department department = testDriver.getDepartmentRepo().findById(deptId, false).orElse(null);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(TestDriver.SALES_NAME_2));
    }

    @Test
    public void deleteDepartmentAndReadItBack() {

        long deptId = 3L;
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartment(deptId);
        testDriver.deleteDepartment(deptId);

        Department department = testDriver.getDepartmentRepo().findById(deptId, false).orElse(null);
        assertThat(department, is(nullValue()));
    }

    @Test
    public void createDepartmentWithEmployeesAndReadItBack() {

        long deptId = 4L;
        testDriver.cleanUpEmployee(1L);
        testDriver.cleanUpEmployee(2L);
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartmentWithEmployees(deptId);
        Department department = testDriver.getDepartmentRepo().findById(deptId, true).orElse(null);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES_NAME));
        List<Employee> employeeList = department.getEmployeeList();
        assertThat(employeeList, is(notNullValue()));
        assertThat(employeeList.size(), is(2));
        Employee bonnie = employeeList.stream()
                .filter(employee -> "Bonnie".equals(employee.getName())).findFirst().get();
        assertThat(bonnie.getName(), is("Bonnie"));
        assertThat(bonnie.getDepartmentId(), is(deptId));
        Employee clyde = employeeList.stream()
                .filter(employee -> "Bonnie".equals(employee.getName())).findFirst().get();
        assertThat(clyde.getName(), is("Bonnie"));
        assertThat(clyde.getDepartmentId(), is(deptId));
    }

    @Test
    public void rollbackOnError() {

        long deptId1 = 5L;
        long deptId2 = 6L;
        testDriver.cleanUpDepartment(deptId1);
        testDriver.cleanUpDepartment(deptId2);

        UnitOfWork.newCurrent();
        Department.create(deptId1, SALES_NAME);
        Department.create(deptId2, TOO_LONG_DEPT_NAME);
        try {
            UnitOfWork.getCurrent().commit();
        } catch (Exception ignored) {
            // ignore
        }
        Department department = testDriver.getDepartmentRepo().findById(deptId1, false).orElse(null);
        assertThat(department, is(nullValue()));
    }

}
