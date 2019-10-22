package com.hoggen.COMangerment.enums;

public enum DCMCheckStateEnum {
	EMPTY(-1, "文件为空"), SUCCESS(0, "操作成功"), STROEFAIL(-2, "文件存入失败"), CHECHFAIL(-3, "检查结果失败"), UNKNOWN(-4, "未知错误");

	private int state;

	private String stateInfo;

	private DCMCheckStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static DCMCheckStateEnum stateOf(int index) {
		for (DCMCheckStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
