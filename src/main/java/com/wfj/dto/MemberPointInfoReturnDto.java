package com.wfj.dto;

import java.util.Date;

public class MemberPointInfoReturnDto {
    private Long sid;

    private String storeCode;

    private String businessName;//门店名称

    private String memberCode;

    private String cardCode;

    private Integer pointType;//积分类型

    private String pointTypeView;//积分类型(显示中文)

    private Double points;

    private Date pointTime;//积分时间

    private Integer status;

    private String serialNumber;

    private Integer delFlag;

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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public String getPointTypeView() {
        return pointTypeView;
    }

    public void setPointTypeView(String pointTypeView) {
        this.pointTypeView = pointTypeView;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Date getPointTime() {
        return pointTime;
    }

    public void setPointTime(Date pointTime) {
        this.pointTime = pointTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}