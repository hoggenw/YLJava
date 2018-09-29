package mainClass;

public class Student {
	private int id;// 首页
	private String name;// 上一页
	private String adress;// 下一页

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String adress) {
		super();
		this.adress = adress;
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Student [ adress=" + adress + ", id=" + id + ", name=" + name + "]";
	}

}
