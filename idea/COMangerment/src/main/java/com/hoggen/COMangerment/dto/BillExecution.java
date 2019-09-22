package com.hoggen.COMangerment.dto;

import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;

import java.util.List;

public class BillExecution {
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

	// 操作的User
	private Bill bill;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public List<Bill> getBillList() {
		return billList;
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}

	// 获取的User列表
	private List<Bill> billList;

	public BillExecution() {
	}

	// 失败的构造器
	public BillExecution(UserStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public BillExecution(UserStateEnum stateEnum, Bill user) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.bill = user;
	}

	// 成功的构造器
	public BillExecution(UserStateEnum stateEnum, List<Bill> UserList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.billList = UserList;
	}
}
