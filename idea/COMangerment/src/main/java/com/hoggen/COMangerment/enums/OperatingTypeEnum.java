package com.hoggen.COMangerment.enums;

public enum OperatingTypeEnum {
	// (1登录，2上传头围检测、3上传腹围检测、4上传股骨检测、5上传头臀径检测）
	LOGIN(1, "登录"), T_UOLOAD(2, "上传头围检测"), F_UOLOAD(3, "上传腹围检测"), G_UOLOAD(4, "上传股骨检测"), TT_UOLOAD(5, "上传头臀径检测");

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
