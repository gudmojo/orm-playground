package is.gudmundur1.unitofworkdemo;

import org.flywaydb.core.Flyway;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AppTest {

    public static final String SALES = "Sales";
    private TestDriver testDriver = new TestDriver();

    @Test
    public void createOrderAndReadItBack() {
        testDriver.createDepartment();
        String postgresUsername = "postgres";
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:postgresql://localhost:5432/postgres", postgresUsername, "mysecretpassword");
        flyway.migrate();

        Department department = testDriver.findByName(SALES).orElse(null);
        assertThat(department, is(notNullValue()));
        assertThat(department.getName(), is(SALES));
    }
}
