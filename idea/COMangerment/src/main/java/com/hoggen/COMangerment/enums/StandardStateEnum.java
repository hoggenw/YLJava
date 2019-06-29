package com.hoggen.COMangerment.enums;

public enum StandardStateEnum {
	SUCCESS(0, "操作成功"), INNER_ERROR(-1001, "操作失败"), PERROR(-1002, "参数错误");

	private int state;

	private String stateInfo;

	private StandardStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static StandardStateEnum stateOf(int index) {
		for (StandardStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
