package hoggen.com.firstTest;

abstract class SecondClass {

	public SecondClass() {
		// TODO Auto-generated constructor stub
	}

	public final void getRuntime() {
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		System.out.println("运行时间：  " + (end - start));

	}

	public abstract void code();
}
