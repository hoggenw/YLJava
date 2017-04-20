/**
 * Created by wangliugen on 2017/4/20.
 */

//实现 Runnable 接口来创建线程
public class RunnableDemo implements  Runnable {
//通过继承Thread来创建线程
//创建一个线程的第二种方法是创建一个新的类，该类继承 Thread 类，然后创建一个该类的实例。继承类必须重写 run() 方法，该方法是新线程的入口点。它也必须调用 start() 方法才能执行。
//public class RunnableDemo extends Thread {
    private  Thread t;
    private  String threadName;

    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public  void  run() {
        System.out.println("Running " +  threadName );
        try {
            for (int i = 4; i > 0; i--){
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }
    public  void  start() {
        System.out.println("Starting " +  threadName );
        if (t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }
}






















