package com.wfj.entity;

public class StoreInfo {
    private Long sid;

    private String storeCode;

    private String poiId;

    private String businessName;

    private String branchName;

    private String province;

    private String city;

    private String district;

    private String address;

    private String telephone;

    private String categories;

    private Integer offsetType;

    private String longitude;

    private String latitude;

    private String photoList;

    private String special;

    private String openTime;

    private String avgPrice;

    private String introduction;

    private String recommend;

    private Integer channelType;

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

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getOffsetType() {
        return offsetType;
    }

    public void setOffsetType(Integer offsetType) {
        this.offsetType = offsetType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhotoList() {
        return photoList;
    }

    public void setPhotoList(String photoList) {
        this.photoList = photoList;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    @Override
    public String toString() {
        return "StoreInfo{" +
                "sid=" + sid +
                ", storeCode='" + storeCode + '\'' +
                ", poiId='" + poiId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", categories='" + categories + '\'' +
                ", offsetType=" + offsetType +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", photoList='" + photoList + '\'' +
                ", special='" + special + '\'' +
                ", openTime='" + openTime + '\'' +
                ", avgPrice='" + avgPrice + '\'' +
                ", introduction='" + introduction + '\'' +
                ", recommend='" + recommend + '\'' +
                ", channelType=" + channelType +
                '}';
    }
}