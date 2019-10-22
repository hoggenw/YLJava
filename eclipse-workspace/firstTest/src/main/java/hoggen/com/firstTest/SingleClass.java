package hoggen.com.firstTest;

public class SingleClass extends SecondClass {

	public String name;
	public String love;

	private static SingleClass s = null;
	private long totoal;

	private SingleClass() {

	}

	public static SingleClass getInstance() {
		if (s == null) {
			s = new SingleClass();
		}
		return s;
	}

	@Override
	public void code() {
		totoal = 0;
		for (int i = 0; i < 100; i++) {
			totoal += i;
		}
	}

}
