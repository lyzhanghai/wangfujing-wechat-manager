package com.wfj.controller.coupon;

import com.wfj.controller.index.BaseController;
import com.wfj.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 优惠券规则
 *
 * @author kongqf
 * @create 2016-12-07
 */
@Controller
@RequestMapping("couponrule")
public class CouponRuleController extends BaseController {

    @RequestMapping("/addEntity")
    public String list(Model model) {
        model.addAttribute("resources", null);
        return Common.BACKGROUND_PATH + "/coupon/rule/add";
    }
}
