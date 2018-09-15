package hoggen.com.firstTest;

public class MyThread extends Thread {

	static int tickets = 10;
	static Object o = new Object();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// print();
		// sellTickets();
		while (tickets > 0) {
			sellTickets2();
		}
		// super.run();
	}

	private synchronized static void sellTickets2() {
		// System.out.println(this.getName() + "将要进入锁");
		if (tickets <= 0) {
			return;
		}
		System.out.println(Thread.currentThread().getName() + " : 买到了第" + tickets + "张票");
		tickets--;
		// System.out.println(this.getName() + "已经开锁");
	}

	private void sellTickets() {
		while (true) {
			// System.out.println(this.getName() + "将要进入锁");
			synchronized (o) {

				if (tickets > 0) {
					System.out.println(this.getName() + " : 买到了第" + tickets + "张票");
					tickets--;
				} else {
					System.out.println("买完了");
					break;
				}

			}
			// System.out.println(this.getName() + "已经开锁");

		}
	}

	public MyThread() {
		// TODO Auto-generated constructor stub
	}

	public MyThread(String name) {
		super(name);
	}

	private void print() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.getName() + " : " + i);
		}
	}

}
