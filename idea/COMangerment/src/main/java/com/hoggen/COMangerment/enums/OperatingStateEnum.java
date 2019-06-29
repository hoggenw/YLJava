package com.hoggen.COMangerment.enums;

public enum OperatingStateEnum {
	SUCCESS(0, "操作成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "操作为空或数据不足"),;

	private int state;

	private String stateInfo;

	private OperatingStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static OperatingStateEnum stateOf(int index) {
		for (OperatingStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
