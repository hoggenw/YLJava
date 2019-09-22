package com.hoggen.COMangerment.entity;

import java.util.Date;

public class User {
	// ID
	private Long userId;
	//推荐人id
	private Long pId;

	// 用户电话
	private String mobile;

	// 真实姓名
	private String realName;

	// 用户状态(0正常，1冻结)
	private Integer status;

	// 用户状态(0女性，1男性)
	private Integer sex;

	// 是否推荐过用户(0没有，1推荐过)
	private Integer recommend;

	//积分
	private Integer  integral;
	// 创建时间
	private Date createTime;
	//备注
	private String remark;
	//地址
	private String address;
	//生日
	private String birthday;


	//销售员
	private String salesperson;
	// 用户状态(1.国历，2农历)
	private Integer birthdayType;

	//被谁创建
	private Long createBy;



//	// 修改时间
//	private Date updateTime;
//	//被谁修改
//	private Long updateBy;
	//会员号
//	private String code;
	// 名称
//	private String userName;
//	// 密码
//	private String password;
//	//
//	private String randomString;

	public String getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(String salesperson) {
		this.salesperson = salesperson;
	}

	public Integer getBirthdayType() {
		return birthdayType;
	}

	public void setBirthdayType(Integer birthdayType) {
		this.birthdayType = birthdayType;
	}


	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public Long getUpdateBy() {
//		return updateBy;
//	}
//
//	public void setUpdateBy(Long updateBy) {
//		this.updateBy = updateBy;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}

//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getRandomString() {
//		return randomString;
//	}
//
//	public void setRandomString(String randomString) {
//		this.randomString = randomString;
//	}








}
