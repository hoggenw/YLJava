package controller;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import FunctionComeTrue.DealParameter;
import FunctionComeTrue.KeyName;
import FunctionComeTrue.People;
import FunctionComeTrue.User;

/**
 * Created by wangliugen on 2017/5/15.
 */
//@RestController
@Controller
@EnableAutoConfiguration
public class APIController {

   @RequestMapping(value = "/",method = GET)
   public String index(ModelMap map){
       map.put("people",new People());
       // return模板文件的名称，对应src/main/resources/templates/Hello.html
       return "Hello";
   }
    @RequestMapping(value = "/add",method = POST)
    public String add(HttpServletRequest req, ModelMap map) {
        List<People> peoples = new ArrayList<>();
        People people = new People();
        people.setAge(Integer.parseInt(req.getParameter("age")));
        people.setName(req.getParameter("name"));
        peoples.add(people);
        map.put("peoples",peoples);
       return  "result";
    }

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
    Object trequest(@RequestBody Hashtable user){


        Hashtable hashtable = new Hashtable();
        int key = (int) user.get("key");
        if (key > 60) {
            hashtable.put("stage","oldMan");
        }else if (key > 30) {
            hashtable.put("stage","midlife");
        }else if (key > 15){
            hashtable.put("stage","youngMan");
        }else if (key > 0) {
            hashtable.put("stage","children");
        }else {
            hashtable.put("error","无效参数");
            return  hashtable;
        }

        hashtable.put("1", "One");
        hashtable.put("2", "Two");
        hashtable.put("3", "Three");
        hashtable.put("key", user.get("key"));
        hashtable.put("name",user.get("name"));

        //new User(key, name);
        return hashtable;
    }

}
