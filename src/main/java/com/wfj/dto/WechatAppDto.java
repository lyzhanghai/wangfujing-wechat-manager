package com.wfj.dto;

/**
 * 公众号管理
 *
 * @author kongqf
 * @create 2016-12-02
 */
public class WechatAppDto extends PageBase {
    private Long sid;

    private String appid;

    private String appsecret;

    private String storecode;

    private String storename;

    private Integer delFlag;

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getStorecode() {
        return storecode;
    }

    public void setStorecode(String storecode) {
        this.storecode = storecode;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "WechatAppDto{" +
                "sid=" + sid +
                ", appid='" + appid + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", storecode='" + storecode + '\'' +
                ", storename='" + storename + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
