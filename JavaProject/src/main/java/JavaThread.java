

/**
 * Created by wangliugen on 2017/4/26.
 */
public class JavaThread extends Thread {

    //用volatile修饰的变量，线程在每次使用变量的时候
    private volatile double aDouble = 0;
    private int countDown = 5;


    boolean waiting = true;
    boolean ready =false;
    JavaThread(int priority) {
        setPriority(priority);
        start();
    }

    public String toString(){
        return super.toString() + " : " + countDown;
    }


    synchronized void  startWait() {
        try {
            while (!ready) wait();
        }catch (InterruptedException e){
            System.out.println("wait() interrupted");
        }
    }

    public  void run() {
        while (true){
            for (int i = 1; i < 100000; i++)
                aDouble = aDouble + (Math.PI + Math.E) / (double)i;
            System.out.println(this);
            if (--countDown == 0) return;
        }
//        String thrdName = Thread.currentThread().getName();
//        System.out.println(thrdName + " starting.");
//        while (waiting)
//            System.out.println("waiting:"+waiting);
//        System.out.println("waiting...");
//        startWait();
//        try {
//            Thread.sleep(1000);
//
//        }catch (Exception e) {
//            System.out.println(thrdName + " interrupted.");
//        }
//        System.out.println(thrdName + " terminating.");
//        for (int i = 0; i < 10; i++) {
//            printMessage();
//        }
    }

    synchronized void notice() {
        ready = true;
        notify();
    }

    public  void  printMessage() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("name = " + name);
    }
}
