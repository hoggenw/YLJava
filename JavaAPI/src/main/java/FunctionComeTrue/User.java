package FunctionComeTrue;

/**
 * Created by wangliugen on 2017/5/16.
 */
public class User {
    public   int key;
    public   String name;
    public  UserNesting nestingMessage;
    public User(Integer inputKey,String inputName) {
        key = inputKey;
        name = inputName;
        nestingMessage = new UserNesting();
        nestingMessage.message = "this is " + name + " and the key is " + key;
    }

}
