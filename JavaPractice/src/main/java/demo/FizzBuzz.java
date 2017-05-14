package demo;

/**
 * Created by wangliugen on 2017/5/3.
 */
public class FizzBuzz {

    public void fizzBuzzTest() {
        for (int i = 1; i <= 100; i++) {
            numberForPrint(i);
        }
    }


    private void numberForPrint(int number) {
        String numberString = String.valueOf(number);
        if (numberString.contains("3")) {
            System.out.println("Fizz");
            return;
        }

        if (numberString.contains("5")) {
            System.out.println("Buzz");
            return;
        }
//        if (number % 15 == 0){
//            System.out.println("FizzBuzz");
//            return;
//        }
        if (number % 3 == 0) {
            System.out.println("Fizz");
            return;
        }
        if (number % 5 == 0) {
            System.out.println("Buzz");
            return;
        }
        System.out.println(number);
    }
}
