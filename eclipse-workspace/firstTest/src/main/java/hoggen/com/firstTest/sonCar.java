package hoggen.com.firstTest;

public class sonCar extends Car {
	String sonNew;

	public sonCar(Integer price, String color, String sonNew) {
		super(price, color);
		this.sonNew = sonNew;
		// TODO Auto-generated constructor stub
	}

	public void description() {
		super.description();
		System.out.println(" new info :" + this.sonNew);
	}
}
