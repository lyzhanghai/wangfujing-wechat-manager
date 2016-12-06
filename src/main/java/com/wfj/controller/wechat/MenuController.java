package com.wfj.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfj.annotation.SystemLog;
import com.wfj.controller.index.BaseController;
import com.wfj.entity.MsgReply;
import com.wfj.entity.ResFormMap;
import com.wfj.entity.WechatMenu;
import com.wfj.message.req.StoreInfoDto;
import com.wfj.service.intf.IAppAccountInfoService;
import com.wfj.service.intf.IMenuService;
import com.wfj.service.intf.MsgReplyService;
import com.wfj.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    private MsgReplyService msgReplyService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wechat/menu/list";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public JSONObject getMenus() {
        // 1 通过门店接口获取appID,appSecret
        StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(PropertiesUtils.findPropertiesKey("appid"));
        logger.info("storeInfo ================ " + storeInfo);
        String appid = storeInfo.getAppId();
        String secret = storeInfo.getSecret();
        WechatMenu menuModel = new WechatMenu();
        menuModel.setAppid(appid);
        menuModel.setParentSid("0");
        List<WechatMenu> menuList = menuService.queryMenus(menuModel);
        String menus = "";
        JSONObject treeObjec = new JSONObject();
        if (menuList != null && menuList.size() > 0) {
            JSONObject menuJson = menuService.generatorMenuJson(menuList, true);
            menus = menuJson.toJSONString();
        } else {
            menus = wechatUtil.getMenus(appid, secret);
            menuService.initMenus(menus, appid);
        }

        String jsonStr = menus.replace("name", "text");
        jsonStr = jsonStr.replace("sub_button", "children");
        JSONArray result = (JSON.parseObject(jsonStr)).getJSONObject("menu").getJSONArray("button");
        treeObjec.put("text", "菜单");
        treeObjec.put("children", result);

        return treeObjec;
    }

    @ResponseBody
    @RequestMapping("reslists")
    public List<WechatMenu> reslists(Model model) throws Exception {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        WechatMenu menuModel = new WechatMenu();
        menuModel.setAppid(PropertiesUtils.findPropertiesKey("appid"));
        List<WechatMenu> list = menuService.queryMenus(menuModel);
       /* List<TreeObject> list = new ArrayList<TreeObject>();
        for (WechatMenu menu : mps) {
            TreeObject ts = new TreeObject();
            Common.flushObject(ts, map);
            list.add(ts);
        }*/
        MenuUtil menuUtil = new MenuUtil();
        List<WechatMenu> ns = menuUtil.getChildTreeObjects(list, "0", "　");
        return ns;
    }

    /**
     * 跳转到新增界面
     *
     * @return
     */
    @RequestMapping("addUI")
    public String addUI(Model model) {
        return Common.BACKGROUND_PATH + "/wechat/menu/add";
    }

    /**
     * 添加菜单
     *
     * @param menuName
     * @param menuUrl
     * @param menuParentId
     * @return
     * @throws Exception
     */
    @RequestMapping("addEntity")
    @ResponseBody
    @Transactional(readOnly = false)
    @SystemLog(module = "系统管理", methods = "资源管理-新增资源")
    public String addEntity(String radioMenuType, String menuName, String menuUrl, String menuParentId,
                            String sid) throws Exception {
        boolean flag = false;
        WechatMenu wechatMenu = new WechatMenu();
        wechatMenu.setParentSid(menuParentId);
        wechatMenu.setName(menuName);
        wechatMenu.setType(radioMenuType);
        wechatMenu.setViewurl(menuUrl);

        if (StringUtils.isNotEmpty(sid)) {
            wechatMenu.setSid(new Long(sid));
            flag = menuService.updateMenu(wechatMenu);
        } else {
            List<WechatMenu> list = menuService.queryMenus(wechatMenu);
            if (list != null) {
                wechatMenu.setAppid(list.get(0).getAppid());
            }
            flag = menuService.saveMenu(wechatMenu);
        }
        if (flag) {
            return "success";

        } else {
            return "faile";
        }
    }

    /**
     * 编辑菜单
     *
     * @param model
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model) {
        String id = getPara("id");
        WechatMenu wechatMenu = new WechatMenu();
        wechatMenu.setSid(new Long(id));
        if (Common.isNotEmpty(id)) {
            List<WechatMenu> list = menuService.queryMenus(wechatMenu);
            if (list != null && list.size() > 0) {
                WechatMenu menu = list.get(0);
                if (menu.getType().equals("click")) {
                    MsgReply msgReply = new MsgReply();
                    msgReply.setMsgKey(menu.getClickkey());
                    List<MsgReply> msgReplyList = msgReplyService.getMsgReplyList(msgReply);
                    if (msgReplyList != null && msgReplyList.size() > 0) {

                    }
                }
                model.addAttribute("resources", list.get(0));
            } else {
                model.addAttribute("resources", null);
            }
        }
        return Common.BACKGROUND_PATH + "/wechat/menu/edit";
    }

    /**
     * 根据ID删除菜单
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("deleteEntity")
    @SystemLog(module = "系统管理", methods = "资源管理-删除资源")
    public String deleteEntity(Model model) throws Exception {
        String ids = getPara("ids");
        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            for (String id : array) {
                menuService.delMenu(new Long(id));
            }
        }

        return "success";
    }

    /**
     * 发布菜单
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("releaseMenu")
    @SystemLog(module = "系统管理", methods = "资源管理-删除资源")
    public String releaseMenu() throws Exception {
        StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(PropertiesUtils.findPropertiesKey("appid"));
        logger.info("storeInfo ================ " + storeInfo);
        String appid = storeInfo.getAppId();
        String secret = storeInfo.getSecret();
        WechatMenu menuModel = new WechatMenu();
        menuModel.setAppid(appid);
        menuModel.setParentSid("0");
        List<WechatMenu> menuList = menuService.queryMenus(menuModel);
        String menus = "";
        JSONObject treeObjec = new JSONObject();
        if (menuList != null && menuList.size() > 0) {
            JSONObject menuJson = menuService.generatorMenuJson(menuList, false);
            menus = menuJson.getJSONObject("menu").toJSONString();
            if (StringUtils.isNotEmpty(menus)) {
                wechatUtil.createMenus(appid, secret, menus);
            }
        }

        return "success";
    }
}
