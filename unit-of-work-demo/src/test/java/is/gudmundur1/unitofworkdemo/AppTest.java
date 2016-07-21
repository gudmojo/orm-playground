package is.gudmundur1.unitofworkdemo;

import org.flywaydb.core.Flyway;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AppTest {

    public static final String SALES = "Sales";
    public static final String SALES2 = "Sales2";
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
        Department department = testDriver.findByName(SALES).orElse(null);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES));
    }

    @Test
    public void updateDepartmentAndReadItBack() {

        long deptId = 2L;
        testDriver.cleanUpDepartment(deptId);

        testDriver.createDepartment(deptId);
        testDriver.updateDepartment(deptId);

        Department department = testDriver.getDepartmentRepo().findById(deptId).orElse(null);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES2));
    }
}
