package hoggen.com.firstTest;

public class Person implements BookWeather {
	String name;

	public Person(String name) {
		this.name = name;
	}

	// private WeatherStation station;

	// public Person(String name) {
	// this(name);
	// //this.station = station;
	// }

	// 下雨","下雪 ","下冰雹","出太阳"
	@Override
	public void notifyWeather() {
		// String weather = station.getWeather();
		// if ("下雨".equals(weather)) {
		// System.out.println(name + "打着雨伞上班");
		// } else if ("下雪".equals(weather)) {
		// System.out.println(name + "溜冰 上班");
		// } else if ("下冰雹".equals(weather)) {
		// System.out.println(name + "带着头盔 上班");
		// } else if ("出太阳".equals(weather)) {
		// System.out.println(name + "嗮着太阳 上班");
		// }
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notifyWeather(String weather) {
		// TODO Auto-generated method stub
		if ("下雨".equals(weather)) {
			System.out.println(name + "打着雨伞上班");
		} else if ("下雪".equals(weather)) {
			System.out.println(name + "溜冰 上班");
		} else if ("下冰雹".equals(weather)) {
			System.out.println(name + "带着头盔 上班");
		} else if ("出太阳".equals(weather)) {
			System.out.println(name + "嗮着太阳 上班");
		}
	}

}
