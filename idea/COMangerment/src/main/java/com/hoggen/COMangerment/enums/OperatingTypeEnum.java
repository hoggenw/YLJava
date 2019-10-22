package com.hoggen.COMangerment.enums;

public enum OperatingTypeEnum {
	// (1登录，2上传头围检测、3上传腹围检测、4上传股骨检测、5上传头臀径检测）
	LOGIN(1, "登录"), SUCE_OR(-1000002, "该订单已经返现成功，不能作废"),
	OPERATION_ERROR(-1000002, "修改数据失败");

	private int state;

	private String stateInfo;

	private OperatingTypeEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static OperatingTypeEnum stateOf(int index) {
		for (OperatingTypeEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
