package com.hoggen.COMangerment.enums;

public enum LoginStateEnum {
	USERNONE(-1, "用户名或者密码错误"), SUCCESS(0, "操作成功"), OVERTIME(1001, "登录已过期"), NOTLOGIN(1002, "请登录"),
	NOPERMISSION(1003, "没有权限"), EMPTY(-1002, "用户不存在或者被冻结"), FREEZE(-1003, "用户被冻结");

	private int state;

	private String stateInfo;

	private LoginStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static LoginStateEnum stateOf(int index) {
		for (LoginStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
