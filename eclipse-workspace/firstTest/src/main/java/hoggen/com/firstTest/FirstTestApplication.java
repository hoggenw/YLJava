package hoggen.com.firstTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstTestApplication {

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InterruptedException, UnknownHostException {
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
		// equals() 返回的是比较的结果 如果相等返回true，否则false，比较的是对象的内存地址值。
		// 重写了equals方法就要重写hashCode方法
		// System.out.println(one.equals(two));
		// System.out.println(one.hashCode() == two.hashCode());

		// sonCar sonCar = new sonCar(700000, "white", "其他的信息");
		// System.out.println(sonCar);
		//

		// sonCar.description();
		// sonCar.stop();
		//
		// System.out.println(sonCar instanceof sonCar);
		// System.out.println(sonCar instanceof Car);

		// byte[] buf = new byte[1024 * 1024 * 1024];
		// System.out.println(buf);
		// Throwable able = new Throwable("港行便宜。。。。。");
		// System.out.println(able.toString()); // 输出该异常的类名
		//
		// System.out.println(able.getMessage()); // 输出异常的信息
		// able.printStackTrace(); // 打印栈信息

		// try {
		// div(2, 0);
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println(e.toString());
		// e.printStackTrace();
		// }
		//
		// System.out.println("over");
		// 模板
		// SingleClass tem = SingleClass.getInstance();
		// tem.getRuntime();

		// Object o = new Object();
		// System.out.println(o);

		/**
		 * boolean endsWith(String str) 是否以指定字符结束 boolean isEmpty()是否长度为0 如：“” null V1.6
		 * boolean contains(CharSequences) 是否包含指定序列 应用：搜索 boolean equals(ObjectanObject)
		 * 是否相等 boolean equalsIgnoreCase(String anotherString) 忽略大小写是否相等
		 */
		// String str1 = "hoggen love";// 创建于字符串常量池
		// String str2 = "hoggen";// 创建于字符串常量池
		// String str3 = new String("hoggen");// 创建于字符串对象
		// String str4 = new String("hoggen");
		//
		// // System.out.println(str1.equals(str2));
		// // System.out.println(str2 == str1);
		// // System.out.println(str2 == str3);
		// // System.out.println(str3 == str4);
		// // System.out.println(str3.equals(str4));
		// str1 = str1.replace("en", "ne");
		// System.out.println(str1);
		// str1 = str1.substring(0, 9);
		// System.out.println(str1);
		// // String toUpperCase() 转大写
		// // String toLowerCase() 转小写
		// // String trim() 去除空格
		// str1 = str1.trim();
		// System.out.println(str1);

		// 获取系统属性
		// Properties ps = System.getProperties();
		// ps.list(System.out);

		// timeTest();

		// randomCode();
		// collectionTest();
		// linkedList();
		// setTest();
		// treeSetTest();
		// hashMaptest();
		// enumTest();
		// resTest();
		// flieClass file = new flieClass();
		// file.writeFileTest();
		// file.readFileTest();

		// myThread();
		//
		// listanerTest();

		ipTest();

		// socketTest();

		SpringApplication.run(FirstTestApplication.class, args);
	}

	public static void socketTest() {
		try {
			// 建立tcp服务端
			ServerSocket serverSocket = new ServerSocket(9090);
			while (true) {
				Socket socket = serverSocket.accept(); // 不断接收用户请求
				new ImageServer(socket).start(); // 如产生socket，则有连接，在开启线程，服务
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void ipTest() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println("IP: " + address.getHostAddress());
		System.out.println("Name: " + address.getHostName());
		InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
		System.out.println(addresses);
	}

	public static void myThread() {

		MyThread thread1 = new MyThread("hoggen");
		MyThread thread2 = new MyThread("是的");
		MyThread thread3 = new MyThread("聂女");
		MyThread thread4 = new MyThread("野兽");
		// thread2.setPriority(6);
		thread3.start();
		thread1.start();
		thread2.start();
		thread4.setDaemon(true);
		thread4.start();
		System.out.println(thread4.isDaemon());
	}

	public static void listanerTest() {
		WeatherStation station = new WeatherStation();
		station.startWork();

		Person p1 = new Person("小名");
		station.addListaner(p1);

	}

	public static void resTest() {
		// 只能输入数字
		String str = "12435423a2";
		boolean flag = str.matches("[0-9]+");
		System.out.println(flag ? "输入正确" : "只能输入数字");

		String str2 = "124354232";
		boolean flag2 = str2.matches("[0-9]+");
		System.out.println(flag2 ? "输入正确" : "只能输入数字");

	}

	public static void enumTest() {
		System.out.println(SayStatus.One);
		System.out.println(SayStatus.Two);
	}

	public static void hashMaptest() {
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		map1.put("jack", 20);
		map1.put("rose", 18);
		map1.put("lucy", 17);
		map1.put("java", 25);
		map1.put("java", 30);
		System.out.println(map1);
		// 添加重复的键值（值不同）,会返回集合中原有（重复键）的值， System.out.println(map1.put("jack", 30)); //20
		System.out.println("value:" + map1.get("jack"));
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("张三丰", 100);
		map2.put("虚竹", 20);
		System.out.println("map2:" + map2);
		// 从指定映射中将所有映射关系复制到此映射中。
		map1.putAll(map2);
		System.out.println("map1:" + map1);
		map2.clear();
		System.out.println("map2:" + map2);
	}

	public static void treeSetTest() {
		TreeSet<sonCar> ts = new TreeSet<sonCar>(new MyComparator());
		ts.add(new sonCar(90000, "white", "d撒大声地"));
		ts.add(new sonCar(80000, "black", "d撒大声地"));
		ts.add(new sonCar(70000, "green", "d撒大声地"));
		ts.add(new sonCar(60000, "red", "d撒大声地"));
		ts.add(new sonCar(50000, "gray", "d撒大声地"));
		ts.add(new sonCar(40000, "blue", "d撒大声地"));
		System.out.println(ts);
		System.out.println(ts.size()); // 5
	}

	public static void setTest() {
		HashSet<String> hSet = new HashSet<>();
		hSet.add("世界军事");
		hSet.add("兵器知识");
		hSet.add("舰船知识");
		hSet.add("汉和防务");
		System.out.println(hSet);
		hSet.add("汉和防务");
		Iterator<String> iterator = hSet.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public static void linkedList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("西游记");
		list.add("三国演义");
		list.add("石头记");
		list.add("水浒传");
		list.add("全球通史");
		list.addFirst("史记");
		list.addLast("呐喊");
		// list.addFirst(null);
		// list.addLast(null);
		System.out.println(list);
		// 获取指定位置处的元素。
		String str = list.get(0);
		// 返回此列表的第一个元素。
		String str2 = list.getFirst();
		System.out.println(str.equals(str2));

		// 获取指定位置处的元素。
		String str3 = list.get(list.size() - 1);
		// 返回此列表的最后一个元素。
		String str4 = list.getLast();
		System.out.println(str3.equals(str4));

		// 获取但不移除此列表的头（第一个元素）。
		Object element = list.element();
		System.out.println(element);

		int size = list.size();
		System.out.println(size);
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
			if (next.equals("石头记")) {
				iterator.remove();
			}

		}
		System.out.println(list);
		// ListIterator<String> listIterator = list.listIterator();
		// while (listIterator.hasNext()) {
		// String next = listIterator.next();
		// System.out.println(next);
		//
		// }
		// System.out.println("**************");
		// while (listIterator.hasPrevious()) {
		// String next = listIterator.previous();
		// System.out.println(next);
		//
		// }
		//
		// Vector v = new Vector();
		// v.addElement("aaa");
		// v.addElement("bbb");
		// v.addElement("ccc");
		// System.out.println(v);
		// System.out.println(v.elementAt(2)); // ccc
		// // 遍历Vector遍历
		// Enumeration ens = v.elements();
		// while (ens.hasMoreElements()) {
		// System.out.println(ens.nextElement());
		// }

	}

	public static void collectionTest() {

		sonCar sonCar1 = new sonCar(700000, "white", "其他的信息1");
		sonCar sonCar2 = new sonCar(600000, "black", "其他的信息2");
		sonCar sonCar3 = new sonCar(500000, "blue", "其他的信息3");
		sonCar sonCar4 = new sonCar(400000, "red", "其他的信息4");
		ArrayList<Car> cars = new ArrayList<Car>();
		cars.add(sonCar1);
		cars.add(sonCar2);
		cars.add(sonCar2);
		cars.add(sonCar3);
		// 没找到返回-1，list里面的元素是可以重复的
		System.out.println(cars.indexOf(sonCar2) + "位置");

		System.out.println(cars);
		cars.set(0, sonCar4);
		System.out.println(cars);

		System.out.println(cars.isEmpty());
		System.out.println(cars.contains(sonCar1));
		cars.remove(sonCar1);
		System.out.println(cars);

		// Collection<String> list = new ArrayList<String>();
		// // 增加：add() 将指定对象存储到容器中
		// list.add("计算机网络");
		// list.add("现代操作系统");
		// list.add("java编程思想");
		// System.out.println(list);
		// [计算机网络, 现代操作系统, java编程思想]

		// 增加2 将list容器元素添加到list2容器中
		// Collection<String> list2 = new ArrayList<String>();
		// list2.add("java核心技术");
		// list2.addAll(list);
		// list2.add("java语言程序设计");
		// System.out.println(list2);
		//
		// System.out.println(list2.isEmpty() + " 空否?");
		// System.out.println(list2.contains("java编程思想") + " java编程思想否?");
		// System.out.println(list2.containsAll(list) + " list?");
		//
		// System.out.println(list2.size());
		//
		// list2.remove("java核心技术");
		// System.out.println(list2);
		// list2.removeAll(list);
		// System.out.println(list2);
		// list2.clear();
		// System.out.println(list2);

	}

	public static void randomCode() {
		Random random = new Random();
		char chas[] = { 'a', 'b', 'c', 'd', 'f', '你', 'H', 'O' };
		StringBuilder rel = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			rel.append(chas[random.nextInt(chas.length)]);
		}
		System.out.println("验证码为： " + rel.toString());

	}

	public static void timeTest() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		System.out
				.println(year + ":" + month + ":" + day + "  " + dayOfWeek + "  " + hour + ":" + minute + ":" + second);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));
	}

	public static void div(int x, int y) throws Exception {
		if (y == 0) {
			throw new Exception("除数为0"); // throw关键字后面接受的是具体的异常的对象
		}
		System.out.println(x / y);
		// try {
		// System.out.println(x / y);
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println(e.toString());
		// System.out.println(e.getMessage());
		// e.printStackTrace();
		// System.out.println("除数不能为0");
		// }
		System.out.println("除法运算");

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
