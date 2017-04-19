/**
 * Created by wangliugen on 2017/4/18.
 */

interface  Message {
    String greet();
}


public class InnerClass implements Message{
    int num = 56;

    @Override
    public String greet() {
        return "baby";
    }

    public     class  InnerDemo {
        public  void  print() {
            System.out.println("this is an inner class" + num);
        }
    }

    void  displayInner() {
        InnerDemo test = new InnerDemo();
        test.print();
    }
    public  void  displayMessage(Message m) {
        System.out.println(m.greet() +
                ", This is an example of anonymous inner class as an argument");
    }

}
