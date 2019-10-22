package com.hoggen.COMangerment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysSetting {
    private  Long id;

    private  Integer  first_time_limit;
    private  Integer  second_time_limit;
    private  Integer  third_time_limit;


    private  Integer  first_percent;
    private  Integer  second_percent;
    private  Integer  third_percent;

    private Date created_at;
    private Date 	updated_at;
    private Date 	deleted_at;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFirst_time_limit() {
        return first_time_limit;
    }

    public void setFirst_time_limit(Integer first_time_limit) {
        this.first_time_limit = first_time_limit;
    }

    public Integer getSecond_time_limit() {
        return second_time_limit;
    }

    public void setSecond_time_limit(Integer second_time_limit) {
        this.second_time_limit = second_time_limit;
    }

    public Integer getThird_time_limit() {
        return third_time_limit;
    }

    public void setThird_time_limit(Integer third_time_limit) {
        this.third_time_limit = third_time_limit;
    }

    public Integer getFirst_percent() {
        return first_percent;
    }

    public void setFirst_percent(Integer first_percent) {
        this.first_percent = first_percent;
    }

    public Integer getSecond_percent() {
        return second_percent;
    }

    public void setSecond_percent(Integer second_percent) {
        this.second_percent = second_percent;
    }

    public Integer getThird_percent() {
        return third_percent;
    }

    public void setThird_percent(Integer third_percent) {
        this.third_percent = third_percent;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }




}
