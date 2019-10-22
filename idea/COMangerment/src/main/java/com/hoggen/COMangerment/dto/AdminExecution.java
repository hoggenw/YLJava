package com.hoggen.COMangerment.dto;

import com.hoggen.COMangerment.entity.Admin;
import com.hoggen.COMangerment.enums.UserStateEnum;

public class AdminExecution {
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 用户数量
	private int count;

	// 操作的Admin
	private Admin user;

	public Admin getUser() {
		return user;
	}

	public void setUser(Admin user) {
		this.user = user;
	}

	public AdminExecution() {
	}

	// 失败的构造器
	public AdminExecution(UserStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public AdminExecution(UserStateEnum stateEnum, Admin user) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}
}
