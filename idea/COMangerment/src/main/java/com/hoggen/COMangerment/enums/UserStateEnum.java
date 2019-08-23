package com.hoggen.COMangerment.enums;

public enum UserStateEnum {
	OFFLINE(-1, "非法用户"), SUCCESS(0, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "用户为空"),
	NAMEOFFLINE(-1003, "用户名不合法"), MOBILEOFFLINE(-1004, "电话号码不正确"), ALREADY(-1005, "该用户名已存在"), SEXEMPTY(-1006, "请选择性别"),
	INPUT_ERROR(-1007, "输入有误"),INTEGRAL_NULL(-1008, "积分输入错误")
	,USER_INTEGRAL_UPDATE_ERROR(-1009, "用户更新积分错误")
	,USER_BACK_UPDATE_ERROR(-1010, "返现记录生成错误")
	,INFOILLEGAl(10011, "参数错误");;

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
