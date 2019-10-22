package FunctionComeTrue;

/**
 * Created by wangliugen on 2017/5/16.
 */
public class User {
//    public   int key;
//    public   String name;
//    public  UserNesting nestingMessage;
//    public User(Integer inputKey,String inputName) {
//        key = inputKey;
//        name = inputName;
//        nestingMessage = new UserNesting();
//        nestingMessage.message = "this is " + name + " and the key is " + key;
//    }
    private  Long id;
    private  String name;
    private  Integer age;

    public Long getId() {
        return  this.id;
    }
    public String getName() {
        return  this.name;
    }
    public  Integer getAge() {
        return this.age;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
