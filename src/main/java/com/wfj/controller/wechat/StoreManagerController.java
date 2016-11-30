package com.wfj.controller.wechat;

import com.wfj.controller.index.BaseController;
import com.wfj.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangxuan on 2016-11-30 0030.
 * 门店管理
 */
@Controller
@RequestMapping(value = {"/storeManager"})
public class StoreManagerController extends BaseController {

    /**
     * 访问展示页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list"})
    public String listUI(Model model) {
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wechat/storeManager/list";
    }


}
