package hoggenwang;

public class FileModel implements foo {
	private int id;
	private String name;
	private String size;
	private String type;
	private String addTime;
	private String file_path;
	private String info;

	public FileModel(int id, String name) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.name = name;
	}

	public FileModel() {
		// TODO Auto-generated constructor stub
		super();

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String filePath) {
		file_path = filePath;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "FileModel [addTime=" + addTime + ", file_path=" + file_path + ", id=" + id + ", info=" + info
				+ ", name=" + name + ", size=" + size + ", type=" + type + "]";
	}

}
