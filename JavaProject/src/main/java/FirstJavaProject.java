import java.io.IOException;
import java.sql.Struct;
import java.util.*;

/**
 * Created by wangliugen on 2017/4/17.
 */
public class FirstJavaProject {

    public static void main(String []a1rgs) throws IOException {

          WorkManSalary s =  new WorkManSalary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
          WorkMan m = new WorkManSalary("John Adams", "Boston, MA", 2, 2400.00);
        System.out.println("Call mailCheck using Salary reference --");
        s.mailCheck();
        System.out.println("\n Call mailCheck using Employee reference--");
        m.mailCheck();
//        int a = 20,b = 10;
//        sonCalculation demo = new sonCalculation();
//        demo.addition(a,b);
//        demo.subtraction(a,b);
//        demo.multiplication(a,b);
//       //跟oc一样只看实际对象，无关声明对象
//        Calculation cal = new sonCalculation();
//        cal.addition(a, b);
//        cal.subtraction(a, b);

 //       InnerClass inner = new InnerClass();
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
