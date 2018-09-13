package hoggen.com.firstTest;

interface CarInterface {
	public abstract void stop();
}

public class Car implements CarInterface {
	private Integer price;
	private String color;
	static {
		System.out.println("这是静态代码块");
	}
	{
		saySomething("这是构造代码块");
	}

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(Integer price, String color) {
		// TODO Auto-generated constructor stub
		this.price = price;
		this.color = color;
	}

	public void description() {
		System.out.print("price is: " + price + " color is :" + color);
	}

	private void saySomething(String something) {
		System.out.println(something);
	}

	public void stop() {
		System.out.println(price + "hahha");
	}

}
