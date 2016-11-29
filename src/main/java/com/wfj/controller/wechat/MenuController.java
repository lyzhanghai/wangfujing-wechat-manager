package com.wfj.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfj.controller.index.BaseController;
import com.wfj.message.req.StoreInfoDto;
import com.wfj.service.intf.IAppAccountInfoService;
import com.wfj.service.intf.IMenuService;
import com.wfj.util.Common;
import com.wfj.util.WechatUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * menu
 *
 * @author kongqf
 * @create 2016-11-24
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    private Logger logger = Logger.getLogger(MenuController.class);

    @Autowired
    private WechatUtil wechatUtil;
    @Autowired
    private IAppAccountInfoService appAccountInfoService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wechat/menu/list";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public JSONObject getMenus() {
        // 1 通过门店接口获取appID,appSecret
        StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo("wx871d0104ae72e615");
        logger.info("storeInfo ================ " + storeInfo);
        String appid = storeInfo.getAppId();
        String secret = storeInfo.getSecret();
        String menus = wechatUtil.getMenus(appid, secret);
        menuService.initMenus(menus);
        String jsonStr = menus.replace("name", "text");
        jsonStr = jsonStr.replace("sub_button", "children");
        JSONArray result = (JSON.parseObject(jsonStr)).getJSONObject("menu").getJSONArray("button");
        JSONObject treeObjec = new JSONObject();
        treeObjec.put("text", "菜单");
        treeObjec.put("children", result);
        return treeObjec;
    }

}
