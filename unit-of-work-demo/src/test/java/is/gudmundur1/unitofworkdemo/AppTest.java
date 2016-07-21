package is.gudmundur1.unitofworkdemo;

import org.junit.Test;

public class AppTest {

    private TestDriver testDriver = new TestDriver();

    @Test
    public void createOrderAndReadItBack() {
        testDriver.createRecipe();
    }
}
