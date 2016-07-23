package is.gudmundur1.unitofworkdemo;


import is.gudmundur1.unitofworkdemo.shell.Init;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        Init.init();
        System.out.println("Hello World! Check out the unit tests");
    }
}
