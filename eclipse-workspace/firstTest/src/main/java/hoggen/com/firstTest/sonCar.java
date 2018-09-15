package hoggen.com.firstTest;

import java.io.Serializable;
import java.util.Comparator;

public class sonCar extends Car implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String sonNew;

	public sonCar(Integer price, String color, String sonNew) {
		super(price, color);
		this.sonNew = sonNew;
		// TODO Auto-generated constructor stub
	}

	public String description() {
		String returnString = super.description() + " new info :" + this.sonNew;
		// System.out.println(" new info :" + this.sonNew);
		return returnString;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// return super.toString();
		return description();
	}

}

class MyComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		sonCar b1 = (sonCar) o1;
		sonCar b2 = (sonCar) o2;
		System.out.println(b1 + " comparator " + b2);
		if (b1.price > b2.price) {
			return -1;
		}
		if (b1.price < b2.price) {
			return 1;
		}
		return b1.color.compareTo(b2.color);
	}

}

enum SayStatus {
	One, Two, Three
}
