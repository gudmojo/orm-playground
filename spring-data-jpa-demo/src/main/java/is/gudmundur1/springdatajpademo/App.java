package is.gudmundur1.springdatajpademo;


import is.gudmundur1.springdatajpademo.shell.Init;
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
