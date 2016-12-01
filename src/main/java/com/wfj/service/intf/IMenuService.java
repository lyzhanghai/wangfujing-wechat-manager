package com.wfj.service.intf;

import com.alibaba.fastjson.JSONObject;
import com.wfj.entity.WechatMenu;

import java.util.List;

/**
 * @author kongqf
 * @create 2016-11-28
 */
public interface IMenuService {

    /**
     * 菜单初始化
     *
     * @param meuns
     * @param appid
     * @return
     */
    public boolean initMenus(String meuns, String appid);

    /**
     * 根据条件查询菜单
     *
     * @param record
     * @return
     */
    public List<WechatMenu> queryMenus(WechatMenu record);

    /**
     * 转换成json
     *
     * @param menuList
     * @param isSid
     * @return
     */
    public JSONObject generatorMenuJson(List<WechatMenu> menuList, boolean isSid);

    /**
     * 添加菜单
     *
     * @param wechatMenu
     */
    public boolean saveMenu(WechatMenu wechatMenu);

    /**
     * 修改菜单
     *
     * @param wechatMenu
     */
    public boolean updateMenu(WechatMenu wechatMenu);

    /**
     * 删除菜单
     *
     * @param wechatMenu
     * @return
     */
    public boolean delMenu(WechatMenu wechatMenu);
}
