package com.wfj.controller.coupon;

import com.wfj.controller.index.BaseController;
import com.wfj.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CouponTPLController
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Controller
@RequestMapping("coupongrp")
public class CouponGRPController extends BaseController {

    @RequestMapping("getlist")
    public String getlist() {
        return Common.BACKGROUND_PATH + "/coupon/grp/list";
    }
}
