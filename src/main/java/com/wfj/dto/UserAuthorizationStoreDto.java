package com.wfj.dto;

public class UserAuthorizationStoreDto {
    private Integer sid;

    private String userId;

    private String userName;

    private String storeCode;

    private String businessName;

    private Integer isLoseEfficacy;

    private String userNumber;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getIsLoseEfficacy() {
        return isLoseEfficacy;
    }

    public void setIsLoseEfficacy(Integer isLoseEfficacy) {
        this.isLoseEfficacy = isLoseEfficacy;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public String toString() {
        return "UserAuthorizationStoreDto{" +
                "sid=" + sid +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", businessName='" + businessName + '\'' +
                ", isLoseEfficacy=" + isLoseEfficacy +
                ", userNumber='" + userNumber + '\'' +
                '}';
    }
}
