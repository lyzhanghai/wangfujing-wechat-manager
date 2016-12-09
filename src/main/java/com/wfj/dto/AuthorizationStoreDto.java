package com.wfj.dto;

public class AuthorizationStoreDto {

    private String userId;


    private String storeCode;

    private String businessName;

    private String isLoseEfficacy;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getIsLoseEfficacy() {
        return isLoseEfficacy;
    }

    public void setIsLoseEfficacy(String isLoseEfficacy) {
        this.isLoseEfficacy = isLoseEfficacy;
    }


    @Override
    public String toString() {
        return "UserAuthorizationStoreDto{" +
                ", userId='" + userId + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", businessName='" + businessName + '\'' +
                ", isLoseEfficacy=" + isLoseEfficacy +
                '}';
    }
}
