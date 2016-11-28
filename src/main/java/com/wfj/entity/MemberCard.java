package com.wfj.entity;

import java.util.Date;

public class MemberCard {
    private Long sid;

    private String storeCode;

    private String cardCode;

    private String memberCode;

    private Integer cardType;

    private Integer cardLevel;

    private Integer status;

    private Integer delFlag;

    private Date updateTime;

    private Date createTime;

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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(Integer cardLevel) {
        this.cardLevel = cardLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MemberCard{" +
                "sid=" + sid +
                ", storeCode='" + storeCode + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", cardType=" + cardType +
                ", cardLevel=" + cardLevel +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}