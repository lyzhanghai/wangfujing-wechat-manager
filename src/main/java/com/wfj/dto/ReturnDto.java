package com.wfj.dto;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public class ReturnDto {

    public String code;//返回标志编码

    public String desc;//返回信息描述

    public Object obj;//返回对象

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ReturnDto{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", obj=" + obj +
                '}';
    }
}
