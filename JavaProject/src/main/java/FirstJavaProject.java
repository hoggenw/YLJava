import java.io.IOException;
import java.sql.Struct;
import java.util.*;

/**
 * Created by wangliugen on 2017/4/17.
 */
public class FirstJavaProject {

    public static void main(String []a1rgs) throws IOException {

        //网络
        NetWorking netWorking = new NetWorking();
        //netWorking.getID();
        //netWorking.checkHost(a1rgs);
        //netWorking.getSelfId();
        netWorking.getSize();

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
