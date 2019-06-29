package com.hoggen.COMangerment.enums;

public enum UserStateEnum {
	OFFLINE(-1, "非法用户"), SUCCESS(0, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "用户为空"),
	NAMEOFFLINE(-1003, "用户名不合法"), PASSOFFLINE(-1004, "密码不合法"), ALREADY(-1005, "该用户名已存在");

	private int state;

	private String stateInfo;

	private UserStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static UserStateEnum stateOf(int index) {
		for (UserStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
