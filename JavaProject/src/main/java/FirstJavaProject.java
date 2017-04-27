import java.io.IOException;
import java.net.Socket;
import java.sql.Struct;
import java.util.*;
import javax.xml.ws.Service;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.applet.Main;

/**
 * Created by wangliugen on 2017/4/17.
 */
public class FirstJavaProject {
    static long standardNumber = -1 ;
    static int countNumber = 0;


//    private  static  void netTest() throws IOException {
//        ServerSocket ssock = new ServerSocket(1234);
//        System.out.println("Listening");
//        while (true) {
//            Socket sock = ssock.accept();
//            System.out.println("Connected");
//            new Thread(new NetWorking(sock)).start();
//        }
//
//
//    }
//
//    private static  void  client() {
//
//        try {
//            Socket socket = new Socket("127.0.0.1",8888);
//            //构建IO
//            InputStream inputStream = socket.getInputStream();
//            OutputStream outputStream = socket.getOutputStream();
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
//            bufferedWriter.write("this message is for server");
//            bufferedWriter.flush();
//            //读取服务器返回的信息
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String message = bufferedReader.readLine();
//            System.out.println("server reback message: " + message);
//        }catch (UnknownHostException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
//    static void showThreadStatus(Thread thrd) {
//        System.out.println(thrd.getName() + "Alive:=" + thrd.isAlive() + " State:=" + thrd.getState());
//    }

    static  String  pickNumberFromString(String input) {
        String regEX = "[^0-9]";
        Pattern p = Pattern.compile(regEX);
        Matcher matcher = p.matcher(input);
        return  matcher.replaceAll("").trim();

    }

    static String aescendingString(String input) {
       // System.out.println("lentgh = " + input.length());
        int[]  array =  changedStringToArray(input);
         Arrays.sort(array);
        String sortString = changedArrayToStrng(array);
        System.out.println(" sort string = " + sortString );
//        for (int number: array) {
//            System.out.println(" nubmber = " + number );
//        }
//        System.out.println("array lentgh = " + array.length );
        return  sortString;
    }

    static  int[] changedStringToArray(String input){
        int[] array = new int[input.length()];
        for (int i = 0; i < input.length(); i++){
            array[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return  array;
    }

    static String changedArrayToStrng(int[] array){
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            returnString.append(array[i]);
        }
        return String.valueOf(returnString);
    }

    static long mainLogic(String numberString ){
        String aescendingString = aescendingString(numberString);
        String descendingString = new StringBuffer(aescendingString).reverse().toString();
        long descendingNumber = Long.parseLong(descendingString);
        long aescendingNumber = Long.parseLong(aescendingString);
        System.out.println(descendingNumber + " - " + aescendingNumber + " = " + (descendingNumber - aescendingNumber));
        return  descendingNumber - aescendingNumber;
    }


    public  static  void  recursion(String numberString ) {

        if (standardNumber == -1){
            countNumber += 1;
            standardNumber = mainLogic(numberString);
            System.out.println("first countnumber = ：" + countNumber + "standardNumber = " + standardNumber);
            recursion(String.valueOf(standardNumber));
        }else {
            countNumber += 1;
            if (standardNumber == mainLogic(String.valueOf(standardNumber)) ){
                System.out.println("countnumber = ：" + countNumber + " standardNumber = " + standardNumber);
                System.out.println("总共用了：" + countNumber);
            }else {
                standardNumber = mainLogic(String.valueOf(standardNumber));
                System.out.println("countnumber = ：" + countNumber + "standardNumber = " + standardNumber);
                recursion(String.valueOf(standardNumber));
            }
        }
    }

    public static void main(String []a1rgs) throws IOException, InterruptedException {


        System.out.println("请输入您的数字!");

        Scanner scanner = new Scanner(System.in);
        String numberString =  pickNumberFromString(scanner.next());
        recursion(numberString);
        //long value = Long.parseLong(numberString);

        //System.out.println("value = " + value);


//        NewJavaThread newJavaThread = new NewJavaThread();
//        newJavaThread.start();
//        System.out.println("在50秒之内按任意键中断线程!");
//        int value =  System.in.read();
//        System.out.println("value = " + value);
//        newJavaThread.interrupt();;
//        newJavaThread.join();
//        System.out.println("线程已经退出!");


//        for(int i = 0; i < 5; i++)
//            new NewJavaThread().join();
//        System.out.println("thread is 挂起");

//
//        //线程
//        new JavaThread(Thread.MAX_PRIORITY);
//        for (int i = 0; i < 5; i++)
//            new JavaThread(Thread.MIN_PRIORITY);

//        tt.setName("MyThread #1");
//        showThreadStatus(tt);
//        tt.start();
//        Thread.sleep(50);
//        showThreadStatus(tt);
//        tt.waiting = false;
//        Thread.sleep(50);
//        showThreadStatus(tt);
//        tt.notice();;
//        Thread.sleep(50);
//        showThreadStatus(tt);
//        while(tt.isAlive())
//            System.out.println("alive");
//        showThreadStatus(tt);

        //tt.setName("Thread");
//        System.out.println("before start(), tt.isAlive = " + tt.isAlive());
//        tt.start();
//        System.out.println("just after start(), tt.isAlive = " + tt.isAlive());
//        for (int i=0; i < 10; i++) {
//            tt.printMessage();
//        }
//        System.out.println("The end of main(), tt.isAlive = " + tt.isAlive());

//        client();
//        //网络
//       // netTest();
////        NetWorking netWorking = new NetWorking();
////        netWorking.connetSocket();
//        //netWorking.getID();
//        //netWorking.checkHost(a1rgs);
//        //netWorking.getSelfId();
//       // netWorking.getSize();


//        //数组与集合
//        ArrayToCollection array = new ArrayToCollection();
//        //array.hashSet();
//        //array.shuffleList();
//        //array.setAndList();
//       // array.deleteSet();
//        //Rarray.mapValueAndKey();
//       // array.rotateList();
//        //array.hashTable();
//        //时间测试
//        DateTest dateTest = new DateTest();
//        dateTest.DateFormat();
//        //数组测试R
//        ArrayTest arrayTest = new ArrayTest();
//        //arrayTest.sortArray();
//        arrayTest.reverseArray();
//        //arrayTest.fillArray();

//        //字符串测试
//        StringTest string = new StringTest();
//       // string.compareString();
//       // string.searchLastString();
//        //string.removeCharAt(16);
//       // string.repalceTest();
//       // string.reverse();
//       // string.splitString();
//       // string.region();
//        //string.stringPerformance();
//        //string.stringFormat();
//       // string.stringConcatenate();

//        //多线程
//        RunnableDemo r1 = new RunnableDemo("hello!");
//        Thread t1 = new Thread(r1);
//        t1.setDaemon(true);
//        t1.setName("hoggen");
//        System.out.println("Starting hello thread...");
//        t1.start();
//
//        RunnableDemo bye = new RunnableDemo("Goodbye!");
//        Thread t2 = new Thread(bye);
//        t2.setPriority(Thread.MIN_PRIORITY);
//        t2.setDaemon(true);
//        System.out.println("Starting goodbye thread...");
//        t2.start();


//        RunnableDemo R2 = new RunnableDemo("Thread-2");
//        R2.start();
           //泛型
//        Box<Integer> integerBox = new Box<Integer>();
//        Box<String> stringBox = new Box<String>();
//        integerBox.setT(10);
//        stringBox.setT("hello world i'm hoggen");
//        System.out.printf("Integer Value :%d\n\n", integerBox.getT());
//        System.out.printf("String Value :%s\n", stringBox.getT());

        //WorkManSalary m = new WorkManSalary("John Adams", "Boston, MA", 2, 2400.00);
        //序列化与反序列化
        //m.serialize(m);
       // m.deSerialize();
        //协议代理
//        System.out.println("Call mailCheck using Salary reference --");
//        System.out.println("\n Call mailCheck using Employee reference--");
//        m.setSalary(3000000);
//        m.delegate = new Animal() {
//            @Override
//            public  void  eat() {
//                System.out.println(" work man must eat many thing");
//            }
//            @Override
//            public  void  travel() {
//                System.out.println("worker can not travel far away");
//            }
//
//        };
//        m.mailCheck();
//        m.computePay();

//        int a = 20,b = 10;
//        sonCalculation demo = new sonCalculation();
//        demo.addition(a,b);
//        demo.subtraction(a,b);
//        demo.multiplication(a,b);
//       //跟oc一样只看实际对象，无关声明对象
//        Calculation cal = new sonCalculation();
//        cal.addition(a, b);
//        cal.subtraction(a, b);

//        InnerClass inner = new InnerClass();
//        System.out.printf("Max of %d, %d and %d is %d\n\n",
//                3, 4, 5, inner.maximum( 3, 4, 5 ));
//
//        System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
//                6.6, 8.8, 7.7, inner.maximum( 6.6, 8.8, 7.7 ));
//
//        System.out.printf("Max of %s, %s and %s is %s\n","pear",
//                "apple", "orange",inner. maximum("pear", "apple", "orange"));

//        Integer[] intArray = {1,2,3,4,5};
//        Double[] doubleArray = {1.1,2.2,3.3,4.4,5.5};
//        Character[] stringArray = {'a','s','c','d','f'};
//        System.out.println("Array integerArray contains:");
//        inner.printArray(intArray);
//
//        System.out.println("\nArray doubleArray contains:");
//        inner.printArray(doubleArray);
//
//        System.out.println("\nArray characterArray contains:");
//        inner.printArray(stringArray);
//        InnerClass.InnerDemo outer = inner.new InnerDemo();
//        outer.print();
//
//        inner.displayMessage(new Message(){
//            @Override
//            public String greet() {
//                return "Hello";
//            }
//        });

//
//        methodRankPointts(255.7);
//        FileCopy file = new FileCopy();
//        file.filedeal();
//        Employee employee = new Employee("James Smith");
//        employee.empAge(26);
//        employee.empDesignation("Senior Software Engineer");
//        employee.setSalary(1000);
//        employee.printEmployee();
//        employee.numberDeal();
//        employee.stringTest();
//        employee.dateString();
       // System.out.println(employee instanceof  Employee);
//        Employee empTwo = new Employee("Mary Anne");
//        empTwo.empAge(21);
//        empTwo.empDesignation("Software Engineer");
//        empTwo.empSalary(500);
//        empTwo.printEmployee();


//        System.out.println("Hello World");
//        Library abc = new Library();
//        System.out.println(abc.saySomething());
//        System.out.print(abc.otherString());
//        Puppy myPuppy = new Puppy("tommy");
//        myPuppy.setAge(2);
//        myPuppy.getAge();
//        System.out.println("Variable Value:" + myPuppy.pupptAge);


//        Person man = new Person() ;
//        man.age = 29;
//        man.height = 173;
//        man.name = "hoggen";
//        System.out.print(man.description());
//        Test test = new Test();
//        System.out.print(test.description());
//        test.mulShow();
    }

    private   static  void methodRankPointts(double points) {
        if (points >= 202.5 ) {
            System.out.println("Rank:A1111");
        }else if (points >= 122.4) {
            System.out.println("Rank:A2");
        }else  {
            System.out.println("Rank:A3");
        }
    }

//    public  static  class TimeDeal {
//        int days = 0;
//        //获取用户输入
//        Scanner sc = new Scanner(System.in);
//        System.out.print("输入年份：");
//        int year = sc.nextInt();
//        System.out.print("输入月份：");
//        int month = sc.nextInt();
//
//        switch(month) {
//            case 1:
//            case 3:
//            case 5:
//            case 7:
//            case 8:
//            case 10:
//            case 12:
//                days = 31;
//                break;
//            case 4,6,9,11 :
//                days = 30;
//                break;
//            case 2 :
//                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
//                    days = 29;
//                }else  {
//                    days = 28;
//                }
//                break;
//            default:
//                System.out.println("输入月份错误！ ");
//                System.exit(0);
//        }
//        System.out.printf("天数：%d\n", days);
//
//
//
//    }

    public static class Person {
        int age;
        float  height;
        String name;
        public String description() {
            return  "\n" + name + " height is " + height + " and age is " + age;
    }
    }

    public  static  class  Test {
        char name1 = '我';
        char name2 = '操';
        short x = 22;
        int y = 022 ;
        long z = 0x22;
        float mq = 22.45f;
        double n = 20;

        public String description() {
            double  m = (double) mq;
            return "\n" + name1 + name2 + "\n转化十进制：x = " + x + "y = " + y + "z = " + z + "\n计算乘积：" + m + "*" + n + "=" + m * n;
        }

        public void mulShow() {
            int i,j;
            for (i = 1; i <= 9; i++) {
                for (j = 1; j <= 9; j++){
                    if ( j < i) {
                        System.out.print("        ");
                    }else  {
                        System.out.printf("%d*%d = %2d ",i,j,i*j);
                    }
                }
                System.out.print("\n");
            }
        }
    }

}
