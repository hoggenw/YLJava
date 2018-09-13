package hoggen.com.firstTest;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstTestApplication {

	public static void main(String[] args) {
		// System.out.println("hello java");
		// System.out.println(10);
		// System.out.println(010);
		// System.out.println(0x10);
		// System.out.println(Integer.toBinaryString(10));
		// System.out.println('a'+1);

		// outer:for(int i = 0; i <4; i++) {
		// inner:for (int j = 0; j < 5; j++) {
		// System.out.print("*");
		// break outer;
		// }
		// System.out.print("s\r\n");
		// }
		// drawRect(10, 10);
		// Car car = new Car(500000, "white");
		// car.price = 5.5;
		// car.color = ;
		// car.price = ;
		// car.description();
		//
		// Car car2 = new Car(600000, "black");
		// car2.description();

		// // 获取String[] args 数组长度
		// System.out.println(args.length);
		//
		// // 变量args数组
		// for (int x = 0; x < args.length; x++) {
		// System.out.println(args[x]);
		// }

		// testarray();

		// SingleClass one = SingleClass.getInstance();
		// one.name = "hoggen";
		// one.love = "l";
		//
		// SingleClass two = SingleClass.getInstance();
		// System.out.println("two : " + two.name + " and " + two.love);

		sonCar sonCar = new sonCar(700000, "white", "其他的信息");
		sonCar.description();
		sonCar.stop();
		//
		// System.out.println(sonCar instanceof sonCar);
		// System.out.println(sonCar instanceof Car);

		SpringApplication.run(FirstTestApplication.class, args);
	}

	// 数组探索
	public static void testarray() {
		// int[] array = new int[3];
		// int[] array1 = array;
		// array[2] = 34;
		// array[0] = 78;
		// printIntValur(array[2]);
		// printIntValur(array[0]);
		// array = null;
		// printIntValur(array1[2]);
		// printIntValur(array1[0]);
		int[] a = { 80, 23, 45, 3, 56 };
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		// binarySearch()在指定数组中查找指定元素，返回元素的索引，如果没有找到返回（-插入点-1） 注意：使用查找的功能的时候，数组一定要先排序。
		System.out.println(Arrays.binarySearch(a, 120));

	}

	public static void printIntValur(int value) {
		System.out.print(value + "\n");
	}

	// public static int getResult(int x) {
	// System.out.println( x*8);
	// return x *8;
	// }

	// public static void drawRect(int width, int height) {
	// for (int i = 0; i < width; i++) {
	// for (int j = 0; j < height; j++) {
	// System.out.print("*");
	// }
	// System.out.print("\n");
	// }
	// }
}
