package com.hoggen.COMangerment.entity;

import java.util.Date;

public class Cashback {
    // ID
    private Long backId;

    //订单类型
    private int type;

    //用户id
    private Long userId;

    //用户父级id
    private Long pId;
    // 创建时间
    private Date createTime;
    //备注
    private String remark;

    // ID
    private Long billId;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }




    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
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

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
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