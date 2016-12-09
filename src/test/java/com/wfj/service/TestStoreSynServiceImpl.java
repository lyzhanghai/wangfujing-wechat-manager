package com.wfj.service;

import com.alibaba.fastjson.JSONObject;
import com.wfj.util.Common;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-07 0007.
 */
public class TestStoreSynServiceImpl {

    public static void main(String args[]) {
        transformToWechatAddPoi();
        test();
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
        String[] categories = {"美食,小吃快餐"};
        List<String> categorieList = new ArrayList<String>();
        categorieList.add("美食,小吃快餐");
        map1.put("categories", "[\"美食,小吃快餐\"]");
        map1.put("categories", categories);
        map1.put("categories", categorieList);
        map1.put("offset_type", 1);
        map1.put("longitude", 115.32375);
        map1.put("latitude", 25.097486);

        Map<String, String> photo_url1 = new HashMap<String, String>();
        photo_url1.put("photo_url", "https:// 不超过20张.com");
        Map<String, String> photo_url2 = new HashMap<String, String>();
        photo_url2.put("photo_url", "https://XXX.com");

//        Object[] photo_list = {photo_url1, photo_url2};
//        map1.put("photo_list", "[{\"photo_url\":\"https://不超过20张.com\"},{\"photo_url\":\"https://XXX.com\"}]");
//        map1.put("photo_list", photo_list);

//        String photoList = "https://不超过20张.com,https://XXX.com";
//        if (StringUtils.isNotBlank(photoList)) {
//            String[] split = photoList.split(",");
//            Object[] photo_list = new Object[split.length];
//            for (int i = 0; i < split.length; i++) {
//                String str = split[i];
//                if (StringUtils.isNotBlank(str)) {
//                    Map<String, String> photo_url = new HashMap<String, String>();
//                    photo_url.put("photo_url", str);
//                    photo_list[i] = photo_url;
//                }
//            }
//            map1.put("photo_list", photo_list);
//        }

        String photoList = "https://不超过20张.com,https://XXX.com";
        if (StringUtils.isNotBlank(photoList)) {
            String[] split = photoList.split(",");
            List<Map<String, String>> photo_list = new ArrayList<Map<String, String>>();
            for (String str : split) {
                if (StringUtils.isNotBlank(str)) {
                    Map<String, String> photo_url = new HashMap<String, String>();
                    photo_url.put("photo_url", str);
                    photo_list.add(photo_url);
                }
            }
            map1.put("photo_list", photo_list);
        }

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

    public static void test() {
        Map<String, Object> map1 = new HashMap<String, Object>();
        String photoList = "https://不超过20张.com,https://XXX.com";
        if (StringUtils.isNotBlank(photoList)) {
            String[] split = photoList.split(",");
            Object[] photo_list = new Object[split.length];
            for (int i = 0; i < split.length; i++) {
                String str = split[i];
                if (StringUtils.isNotBlank(str)) {
                    Map<String, String> photo_url = new HashMap<String, String>();
                    photo_url.put("photo_url", str);
                    photo_list[i] = photo_url;
                }
            }
            map1.put("photo_list", photo_list);
        }
        System.out.println(map1);
        System.out.println(JSONObject.toJSONString(map1));
    }

}
