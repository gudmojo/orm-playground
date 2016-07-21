package is.gudmundur1.unitofworkdemo;

import org.flywaydb.core.Flyway;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AppTest {

    private TestDriver testDriver = new TestDriver();

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
        assertThat(department.getName(), is(TestDriver.SALES_NAME));
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
        assertThat(department.getName(), is(TestDriver.SALES_NAME));
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

}
