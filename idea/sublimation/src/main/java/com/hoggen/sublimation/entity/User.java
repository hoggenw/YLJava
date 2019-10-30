package com.hoggen.sublimation.entity;

import java.util.Date;

public class User {

    // ID
    private Long userId;
    //头像
    private String avatar;

    // 名称
    private String userName;
    // 密码
    private String password;
    // mobile
    private String mobile;
//    // 真实姓名
//    private String realName;
    //
    private String randomString;
    // 用户状态(0正常，1冻结)
    private Integer status;
    // 创建时间
    private Date createTime;
    // 登录时间
    private Date lastLoginTime;
    // 更新时间S
    private Date updateTime;
//
//    // 备注
//    private String remark;

    private Integer roleType;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }



//    // token生成时间
//    private Date tokenTime;
//
//    // token
//    private String token;




}
