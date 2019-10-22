/**
 * Created by wangliugen on 2017/4/18.
 */
import  java.io.*;
public abstract class WorkMan  implements  java.io.Serializable {
    private  String name;
    private  String address;
    private  int number;
    public Animal delegate;
    public  WorkMan(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number;
    }



    public void mailCheck() {
        delegate.eat();
        delegate.travel();
        System.out.println("Mailing a check to " + this.name + " " + this.address);
    }


    public  String toString() {
        return  name + " " + address + " " + number;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public int getNumber() {
        return number;
    }

    public  void setNumber(int inNumber) {
        number = inNumber;
    }
    public abstract double computePay();
}
