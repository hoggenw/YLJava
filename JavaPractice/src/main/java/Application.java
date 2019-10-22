import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller"})
public class Application {

    public static void main(String[] args) {

//        Doors100 temp = new Doors100();
//        temp.showTheRusult();
//        FizzBuzz temp = new FizzBuzz();
//        temp.fizzBuzzTest();
//        NumberName temp = new NumberName();
//        temp.numberNameTest();
//        CountCoins countCoins = new CountCoins();
//
//        System.out.println();
//
//        countCoins.testCountCoins(5);
        SpringApplication.run(Application.class, args);
    }

}
