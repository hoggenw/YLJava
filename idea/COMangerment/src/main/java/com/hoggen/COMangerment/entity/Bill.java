package com.hoggen.COMangerment.entity;

import java.util.Date;

public class Bill {



    // ID
    private Long billsId;

    //订单类型
    private int type;

    //用户id
    private Long userId;

    //用户父级id
    private Long pId;


    //积分
    private Integer  integral;
    // 创建时间
    private Date createTime;
    //备注
    private String remark;



    // 用户电话
    private String mobile;

    // 真实姓名
    private String realName;


    //0 正常订单 ， 1作废订单
    private Integer operation;


    private long backId;

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }


    public long getBackId() {
        return backId;
    }

    public void setBackId(long backId) {
        this.backId = backId;
    }








    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getBillsId() {
        return billsId;
    }

    public void setBillsId(Long billsId) {
        this.billsId = billsId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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



}
