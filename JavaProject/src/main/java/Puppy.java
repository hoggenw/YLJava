/**
 * Created by wangliugen on 2017/4/17.
 */
public class Puppy {
    int pupptAge;

    public  Puppy(String name) {
        System.out.println("Name chosen is:" + name);
    }
    public  void  setAge(int age) {
        pupptAge = age + 2;
    }
    public  int getAge() {
        System.out.println("Puppy's age is : " + pupptAge );
        return  pupptAge;
    }
}
