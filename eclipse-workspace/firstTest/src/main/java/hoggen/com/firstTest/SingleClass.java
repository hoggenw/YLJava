package hoggen.com.firstTest;

public class SingleClass {

	public String name;
	public String love;

	private static SingleClass s = null;

	private SingleClass() {

	}

	public static SingleClass getInstance() {
		if (s == null) {
			s = new SingleClass();
		}
		return s;
	}

}
