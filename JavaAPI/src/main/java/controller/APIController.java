package controller;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RestController
@RequestMapping(value = "/users")
//@RestController
//@Controller
@EnableAutoConfiguration
public class APIController {

    //创建线程安全的Map
    static Map<Long,User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return  r;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public  String postUser(@ModelAttribute User user){
        //处理"/users/"的Post请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(),user);
        return  "success";

    }
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }

//=============================================================================
//   @RequestMapping(value = "/",method = GET)
//   public String index(ModelMap map){
//       map.put("people",new People());
//       // return模板文件的名称，对应src/main/resources/templates/Hello.html
//       return "Hello";
//   }
//    @RequestMapping(value = "/add",method = POST)
//    public String add(HttpServletRequest req, ModelMap map) {
//        List<People> peoples = new ArrayList<>();
//        People people = new People();
//        people.setAge(Integer.parseInt(req.getParameter("age")));
//        people.setName(req.getParameter("name"));
//        peoples.add(people);
//        map.put("peoples",peoples);
//       return  "result";
//    }
//
//    @RequestMapping("/hi")
//    String home() {
//        return "Hello World!";
//    }
//
//    @RequestMapping(value = "/he" , method = POST )
//    String hello() {
//        return "1234234";
//    }
//
//    //    @RequestBody Book book
//    @RequestMapping(value = "/cent", method = POST )
//    Hashtable calCente(@RequestBody User user) {
//
//        DealParameter parameter = new DealParameter();
//        return  parameter.parameter(user);
//    }
//    @RequestMapping(value="/trequest", method = POST)
//    @ResponseBody
//    Object trequest(@RequestBody Hashtable user){
//
//
//        Hashtable hashtable = new Hashtable();
//        int key = (int) user.get("key");
//        if (key > 60) {
//            hashtable.put("stage","oldMan");
//        }else if (key > 30) {
//            hashtable.put("stage","midlife");
//        }else if (key > 15){
//            hashtable.put("stage","youngMan");
//        }else if (key > 0) {
//            hashtable.put("stage","children");
//        }else {
//            hashtable.put("error","无效参数");
//            return  hashtable;
//        }
//
//        hashtable.put("1", "One");
//        hashtable.put("2", "Two");
//        hashtable.put("3", "Three");
//        hashtable.put("key", user.get("key"));
//        hashtable.put("name",user.get("name"));
//
//        //new User(key, name);
//        return hashtable;
//    }

}
