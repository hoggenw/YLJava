import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;

/**
 * Created by wangliugen on 2017/4/18.
 */
public class WorkManSalary extends  WorkMan  implements  java.io.Serializable  {
    private double salary;
    private String characteristic;

    public WorkManSalary(String name, String address, int number, double salary) {
        super(name, address, number);
        setSalary(salary);
    }

    public  void  serialize(WorkManSalary man) {
        try{
            FileOutputStream fileOut = new FileOutputStream("/tmp/employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(man);
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch (IOException i) {
            i.printStackTrace();
            System.out.printf("failer");
        }
    }

    public  void  deSerialize() {
        WorkManSalary man = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            man = (WorkManSalary) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("get out finish");
        }catch (IOException i){
            i.printStackTrace();
            System.out.println("get out error");
            return;

        }catch (ClassNotFoundException c ){
            c.printStackTrace();
            System.out.println("Employee class not found");
            return;
        }
        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + man.getName());
        System.out.println("Address: " + man.getAddress());
        System.out.println("number: " + man.getNumber());
        System.out.println("salary: " + man.salary);
    }

    public void mailCheck() {
        super.mailCheck();
        System.out.println("Within mailCheck of Salary class ");
        System.out.println("Mailing check to " + getName()
                + " with salary " + salary);
    }

    public  String getCharacteristic() {
        return characteristic;
    }

    public void  setCharacteristic(WorkManSalary inCharacteristic) {
        characteristic = String.valueOf(inCharacteristic);
    }

    public double getSalary() {
        return salary;
    }



    public void setSalary(double newSalary) {
        if(newSalary >= 0.0) {
            salary = newSalary;
        }
    }

    public double computePay() {
        System.out.println("Computing salary pay for " + getName());
        return salary/52;
    }
}
