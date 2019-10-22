/**
 * Created by wangliugen on 2017/4/18.
 */

interface  Message {
    String greet();
}


public class InnerClass implements Message{
    int num = 56;

    public  <T extends  Comparable<T>> T maximum(T x,T y,T z) {
        T max = x;
        if (y.compareTo(max) > 0 ) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return  max;
    }

    public   <E> void printArray( E[] inputArray) {
        for ( E element: inputArray) {
            System.out.printf("%s", element);
        }
        System.out.println("out finish");
    }
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
