package hoggen.com.firstTest;

import java.util.ArrayList;
import java.util.Random;

public class WeatherStation {
	private String weather;
	String[] weathers = { "下雨", "下雪", "下冰雹", "出太阳" };
	private static ArrayList<BookWeather> list = new ArrayList<BookWeather>();
	Random random = new Random();

	public WeatherStation() {
		// TODO Auto-generated constructor stub
	}

	public void addListaner(BookWeather e) {
		list.add(e);
	}

	public void startWork() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					updateWeather();
					try {
						Thread.sleep(random.nextInt(1000) + 500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public void updateWeather() {
		weather = weathers[random.nextInt(4)];
		System.out.println("天气：  " + weather);
		if (list.size() <= 0) {
			return;
		}
		for (BookWeather bookWeather : list) {
			bookWeather.notifyWeather(weather);
		}
	}

	public String getWeather() {
		return weather;
	}

}
