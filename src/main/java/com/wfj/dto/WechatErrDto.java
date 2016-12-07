package com.wfj.dto;

import javax.inject.Inject;

/**
 * Created by wangxuan on 2016-12-07 0007.
 * 微信接口返回结构
 */
public class WechatErrDto {

    private Integer errcode;//错误码，0为正常

    private String errmsg;//错误信息

    private String poiId;//门店poiid

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    @Override
    public String toString() {
        return "WechatErrDto{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", poiId='" + poiId + '\'' +
                '}';
    }
}
