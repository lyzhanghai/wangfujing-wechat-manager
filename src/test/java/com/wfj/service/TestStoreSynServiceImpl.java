package com.wfj.service;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-07 0007.
 */
public class TestStoreSynServiceImpl {

    public static void main(String args[]) {
        transformToWechatAddPoi();
    }

    public static void transformToWechatAddPoi() {

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("sid", "33788392");
        map1.put("business_name", "15个汉字或30个英文字符内");
        map1.put("branch_name", "15个汉字或30个英文字符内");
        map1.put("province", "15个汉字或30个英文字符内");
        map1.put("city", "15个汉字或30个英文字符内");
        map1.put("district", "15个汉字或30个英文字符内");
        map1.put("address", "");
        map1.put("telephone", "");
        map1.put("categories", "[\"美食,小吃快餐\"]");
        map1.put("offset_type", 1);
        map1.put("longitude", 115.32375);
        map1.put("latitude", 25.097486);
        map1.put("photo_list", "[{\"photo_url\":\"https:// 不超过20张.com\"},{\"photo_url\":\"https://XXX.com\"}]");
        map1.put("recommend", "");
        map1.put("special", "");
        map1.put("introduction", "");
        map1.put("open_time", "8:00-20:00");
        map1.put("avg_price", 35);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("base_info", map1);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("business", map2);

        System.out.println(map3.toString());
        String json = JSONObject.toJSONString(map3);

        System.out.println(json);
    }

}
