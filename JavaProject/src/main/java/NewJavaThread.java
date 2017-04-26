/**
 * Created by wangliugen on 2017/4/26.
 */
public class NewJavaThread extends Thread{
        private int countDown = 5;
        private static int threadCount = 0;
        public NewJavaThread(){
            super("" + ++threadCount);

        }
        public String toString() {
            return "#" + getName() + " : " + countDown;
        }
        public void run(){
            try {
                sleep(50000);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
//            while (true) {
//                System.out.println(this);
//                if (--countDown == 0)
//                    return;
//                try {
//                    sleep(100);
//                }catch (InterruptedException e){
//                    throw  new RuntimeException(e);
//                }
//            }
        }
}
