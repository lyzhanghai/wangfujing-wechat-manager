package com.wfj.entity;

import java.util.Date;

public class CouponTemplate {
    private Integer sid;

    private String couponType;

    private String couponValue;

    private String couponPriceLimit;

    private String createUserid;

    private Date createTime;

    private Date updateTime;

    private Integer ifdel;

    private String filed1;

    private String filed2;

    private String filed3;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
    }

    public String getCouponPriceLimit() {
        return couponPriceLimit;
    }

    public void setCouponPriceLimit(String couponPriceLimit) {
        this.couponPriceLimit = couponPriceLimit;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIfdel() {
        return ifdel;
    }

    public void setIfdel(Integer ifdel) {
        this.ifdel = ifdel;
    }

    public String getFiled1() {
        return filed1;
    }

    public void setFiled1(String filed1) {
        this.filed1 = filed1;
    }

    public String getFiled2() {
        return filed2;
    }

    public void setFiled2(String filed2) {
        this.filed2 = filed2;
    }

    public String getFiled3() {
        return filed3;
    }

    public void setFiled3(String filed3) {
        this.filed3 = filed3;
    }
}