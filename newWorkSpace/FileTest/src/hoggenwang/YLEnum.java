package hoggenwang;

public enum YLEnum {

	A("100-90"), B("89-80"), C("79-70"), D("69-60"), E("59-0");
	private String result;// 分数段
	// private Score(){};

	/**
	 * 有参数的构造方法
	 */
	private YLEnum(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

}
