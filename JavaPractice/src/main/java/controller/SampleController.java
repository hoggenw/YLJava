package controller;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.CountCoins;

@RestController
public class SampleController {
    @RequestMapping("/hi")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/he")
    String hello() {
        return "1234234";
    }


    //    @RequestBody Book book

    @RequestMapping(value = "/cent/{cent}", method = GET)
    String calCente(@PathVariable Integer cent) {
        CountCoins countCoins = new CountCoins();

        Integer times = countCoins.testCountCoins(cent);

        return format("The result is: %s", times);
    }
}
