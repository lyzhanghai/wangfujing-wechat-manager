package com.wfj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfj.entity.WechatMenu;
import com.wfj.mapper.WechatMenuMapper;
import com.wfj.service.intf.IMenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public boolean initMenus(String menus, String appid) {
        List<WechatMenu> menuList = new ArrayList<WechatMenu>();
        WechatMenu menu = null;
        JSONArray result = (JSON.parseObject(menus)).getJSONObject("menu").getJSONArray("button");
        getMenus(result, "0", appid);
        return false;
    }

    public List<WechatMenu> queryMenus(WechatMenu record) {
        return menuMapper.selectByParam(record);
    }

    /**
     * 实体转换成json
     *
     * @param menuList
     * @return
     */
    public JSONObject generatorMenuJson(List<WechatMenu> menuList, boolean isSid) {
        JSONObject menuObject = new JSONObject();
        JSONObject buttonOject = new JSONObject();
        JSONArray button = new JSONArray();
        if (menuList != null) {
            JSONObject object = null;
            for (WechatMenu model : menuList) {
                object = new JSONObject();
                if (isSid) {
                    object.put("id", model.getSid());
                }
                object.put("name", model.getName());

                String parentSid = model.getSid().toString();
                if ("click".equals(model.getType())) {
                    object.put("type", model.getType());
                    object.put("key", model.getClickkey());
                }
                object.put("sub_button", getSub_buttonJson(parentSid, isSid));
                button.add(object);
            }
        }
        if (button.size() != 0) {
            buttonOject.put("button", button);
            menuObject.put("menu", buttonOject);
        }
        return menuObject;
    }

    public JSONArray getSub_buttonJson(String parentSid, boolean isSid) {
        JSONArray sub_button = new JSONArray();
        JSONObject menuObject = new JSONObject();

        WechatMenu menuModel = new WechatMenu();
        menuModel.setParentSid(parentSid);
        List<WechatMenu> menuList = queryMenus(menuModel);
        if (menuList != null) {
            JSONObject object = null;
            for (WechatMenu model : menuList) {
                object = new JSONObject();
                if (isSid) {
                    object.put("id", model.getSid());
                }
                object.put("name", model.getName());
                String subSid = model.getSid().toString();
                if ("click".equals(model.getType())) {
                    object.put("type", model.getType());
                    object.put("key", model.getClickkey());
                } else if ("view".equals(model.getType())) {
                    object.put("type", model.getType());
                    object.put("url", model.getViewurl());
                }
                object.put("sub_button", getSub_buttonJson(subSid, isSid));
                sub_button.add(object);
            }
        }
        return sub_button;
    }

    /**
     * 解析并保存菜单
     *
     * @param jsonArray
     * @param parentSid
     * @param appid
     */
    public void getMenus(JSONArray jsonArray, String parentSid, String appid) {
        WechatMenu menu = null;

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                menu = new WechatMenu();
                JSONObject object = (JSONObject) jsonArray.get(i);
                menu.setAppid(appid);
                menu.setParentSid(parentSid);
                menu.setOrderBy(new Integer(i));

                menu.setName(object.get("name").toString());
                if (object.containsKey("type")) {
                    menu.setType(object.get("type").toString());
                }
                if (object.containsKey("key")) {
                    menu.setClickkey(object.get("key").toString());
                }
                if (object.containsKey("url")) {
                    menu.setViewurl(object.get("url").toString());
                }
                int count = menuMapper.insertSelective(menu);
                String sub_sid = menu.getSid().toString();
                JSONArray subOject = object.getJSONArray("sub_button");
                if (subOject.size() != 0) {
                    getMenus(subOject, sub_sid, appid);
                }
            }
        }
    }

    /**
     * 添加菜单
     *
     * @param wechatMenu
     * @return
     */
    public boolean saveMenu(WechatMenu wechatMenu) {
        boolean flag = false;
        int count = menuMapper.insertSelective(wechatMenu);
        if (count != 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 更新菜单
     *
     * @param wechatMenu
     * @return
     */
    public boolean updateMenu(WechatMenu wechatMenu) {
        boolean flag = false;
        int count = menuMapper.updateByPrimaryKeySelective(wechatMenu);
        if (count != 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 删除菜单
     *
     * @param sid
     * @return
     */
    public boolean delMenu(Long sid) {
        boolean flag = false;
        int count = menuMapper.deleteByPrimaryKey(sid);
        if (count != 0) {
            flag = true;
        }
        return flag;
    }
}
