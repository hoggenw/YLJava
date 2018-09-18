package hoggen.com.firstTest;

public class Person implements BookWeather {
	String name;
	String id;
	String phone;
	String adress;

	public Person(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("id: " + this.id + " name: " + this.name + " phone: " + this.phone + " adress: " + this.adress);
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
