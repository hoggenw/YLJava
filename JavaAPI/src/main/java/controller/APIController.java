package controller;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Hashtable;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import FunctionComeTrue.DealParameter;
import FunctionComeTrue.User;

/**
 * Created by wangliugen on 2017/5/15.
 */
@RestController
@EnableAutoConfiguration
public class APIController {
    @RequestMapping("/hi")
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/he" , method = POST )
    String hello() {
        return "1234234";
    }

    //    @RequestBody Book book
    @RequestMapping(value = "/cent", method = POST )
    Hashtable calCente(@RequestBody User user) {

        DealParameter parameter = new DealParameter();
        return  parameter.parameter(user);
    }
    @RequestMapping(value="/trequest", method = POST)
    @ResponseBody
    Object trequest(@RequestBody User user){

        Hashtable hashtable = new Hashtable();
        hashtable.put("1", "One");
        hashtable.put("2", "Two");
        hashtable.put("3", "Three");
        hashtable.put("key", user.key);
        hashtable.put("name",user.name);

        //new User(key, name);
        return hashtable;
    }

}
