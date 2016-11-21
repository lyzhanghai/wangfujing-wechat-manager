package com.wfj.entity;

import java.util.Date;

public class MemberPoint {
    private Long sid;

    private String storeCode;

    private String memberCode;

    private Double availablePoints;

    private Double totalPoints;

    private Integer delFlag;

    private Date createTime;

    private Date updateTime;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Double getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(Double availablePoints) {
        this.availablePoints = availablePoints;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
}