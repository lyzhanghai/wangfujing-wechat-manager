package com.wfj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfj.entity.WechatMenu;
import com.wfj.mapper.WechatMenuMapper;
import com.wfj.service.intf.IMenuService;
import com.wfj.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 菜单
 *
 * @author kongqf
 * @create 2016-11-28
 */
@Service
public class MenuServiceImpl implements IMenuService {
    private Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Autowired
    private WechatMenuMapper menuMapper;

    /**
     * 菜单初始化
     *
     * @param menus
     * @return
     */
    public boolean initMenus(String menus) {
        List<WechatMenu> menuList = new ArrayList<WechatMenu>();
        WechatMenu menu = null;
        JSONArray result = (JSON.parseObject(menus)).getJSONObject("menu").getJSONArray("button");
        getMenus(result,"0");
        return false;
    }

    public  void getMenus(JSONArray jsonArray, String parentSid) {
        WechatMenu menu = null;
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                menu = new WechatMenu();
                JSONObject object = (JSONObject) jsonArray.get(i);
                menu.setName(object.get("name").toString());
                JSONArray subOject = object.getJSONArray("sub_button");
                if (object.containsKey("type")) {
                    menu.setType(object.get("type").toString());
                }
                if (object.containsKey("key")) {
                    menu.setClickkey(object.get("key").toString());
                }
                if (object.containsKey("url")) {
                    menu.setViewurl(object.get("url").toString());
                }

                System.out.println(menu.toString());
                System.out.println(subOject.size());

                int count = menuMapper.insertSelective(menu);
                System.out.println("count:"+count);
                parentSid = menu.getSid().toString();
                System.out.println("parentSid:"+parentSid);
                if (subOject.size() != 0) {
                    getMenus(subOject, parentSid);
                }

            }
        }
    }

    public static String getMenus() {
        String token = "Zri6_MUGQ-IJYXdkieUx1-CbWexc5eDp2xLgjHLgcJGBzzz-NOTwMWGw92qnNKNkTfqogb5g40C4Ogs22dbOXZAvmpVDoyqLCKL_DlPMAL-Ti9B4bmEAuApw0W6gcm-aRELaABAERY";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", token);
        String menus = "";
        try {
            menus = HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/menu/get", params);
        } catch (Exception e) {

        }
        return menus;

    }

    public static void main(String[] args) {
        String menus = getMenus();
        JSONArray result = (JSON.parseObject(menus)).getJSONObject("menu").getJSONArray("button");
      //  getMenus(result, "0");
        System.out.println();
    }
}
