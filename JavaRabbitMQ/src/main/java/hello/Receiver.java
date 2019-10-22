package hello;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
/**
 CountDownLatch类是一个同步计数器,构造时传入int参数,该参数就是计数器的初始值，
 每调用一次countDown()方法，计数器减1,计数器大于0 时，await()方法会阻塞程序继续执行
 CountDownLatch如其所写，是一个倒计数的锁存器，当计数减至0时触发特定的事件。利用这种特性，
 可以让主线程等待子线程的结束。下面以一个模拟运动员比赛的例子加以说明。
 */

     private CountDownLatch latch = new CountDownLatch(1);

     public void receiveMessage(String message) {
         System.out.println("Received hoggen <" + message + ">");
         latch.countDown();

     }
    public CountDownLatch getLatch() {
        return latch;
    }


}
