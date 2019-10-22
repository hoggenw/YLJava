/**
 * Created by wangliugen on 2017/4/17.
 */
import  java.io.*;
import java.text.SimpleDateFormat;
import  java.util.Date;
public class Employee {
    String name;
    int age;
    String designation;
    double salary;
    public  static  final  String DEPARTMENT = "Development ";
    public  Employee(String name) {
        this.name = name;
    }
    public  void  setSalary(double empSalary) {
        salary = empSalary;
    }

    public  void  empAge(int empAge) {
        age = empAge;
    }
    public  void  empDesignation(String empDesignation) {
        designation = empDesignation;
    }
    public  void  empSalary(double empSalary) {
        salary = empSalary;
    }
    public  void  printEmployee() {
        System.out.println("Name:" + name);
       // System.out.println(name instanceof String);
//        System.out.println("Age: " + age);R
//        System.out.println("Designation: " + designation);
        System.out.println(DEPARTMENT + "Salary: " + salary );
    }
    public  void  numberDeal() {
        int [] numbers = {10, 20, 30, 40, 50};
        for (int x: numbers) {
            System.out.print(x);
            System.out.print(",");
        }
        System.out.print("\n");
        String [] names = {"James","Larry","Tom","Lacy"};
        for (String name: names) {
            System.out.print( name);
            System.out.print(",");
        }
    }

    public  void  stringTest() {
        char [] holleArray = {'h','e','l','l','o'};
        String helloeString = new String(holleArray);
        int len = helloeString.length();
        System.out.print("\nString Length is: " + len );
        helloeString = helloeString.concat(" world!");
        System.out.println( "\n" + helloeString );
        System.out.println(String.format("\n %s Length is: %d ", helloeString, helloeString.length()));
    }

    public  void dateString() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.print("\n" + ft.format(date));
    }

}
