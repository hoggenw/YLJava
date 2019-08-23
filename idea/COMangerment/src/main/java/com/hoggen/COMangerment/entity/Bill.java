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
