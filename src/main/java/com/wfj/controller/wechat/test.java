package com.wfj.controller.wechat;

import com.wfj.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class test {
    private  String aa;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String url="https://api.weixin.qq.com/cgi-bin/menu/get";
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", "kmQXpHrNACyp98xN0tLJLUZz-8hQ3JlMwQIh3LA0ZjVsaXd7Csq58DG54CefMJCmb5adz0GuVU48BoNk-R5fDvoZpHFMuCKIiG3ldjMbwUPNXFd7Yvh_gCYaf-WZCK5KUEGgAHAQAC");
        try{
            String rs= HttpUtil.sendGet(url, params);
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
    }
}
