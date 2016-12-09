package com.wfj.controller.coupon;

import com.wfj.controller.index.BaseController;
import com.wfj.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CouponTPLController
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Controller
@RequestMapping("coupontpl")
public class CouponTPLController extends BaseController {

    @RequestMapping("getlist")
    public String getlist(Model model) {
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/coupon/tpl/list";
    }

    @RequestMapping("addUI")
    public String addUI(Model model) {
        return Common.BACKGROUND_PATH + "/coupon/tpl/add";
    }
}
