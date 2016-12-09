package com.wfj.controller.coupon;

import com.wfj.controller.index.BaseController;
import com.wfj.dto.UserBaseInfoDto;
import com.wfj.entity.CouponRule;
import com.wfj.service.intf.ICouponRuleService;
import com.wfj.util.Common;
import com.wfj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 优惠券规则
 *
 * @author kongqf
 * @create 2016-12-07
 */
@Controller
@RequestMapping("couponrule")
public class CouponRuleController extends BaseController {

    @Autowired
    private ICouponRuleService couponRuleService;

    @RequestMapping("/getinfo")
    public String list(Model model) {
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        CouponRule couponRule = new CouponRule();
        couponRule.setStoreCode(curUserInfo.getStoreCode());
        List<CouponRule> list = couponRuleService.queryCouponInfo(couponRule);
        if (list != null && list.size() == 1) {
            model.addAttribute("coupon", list.get(0));
        }
        return Common.BACKGROUND_PATH + "/coupon/rule/add";
    }

    /**
     * 新增券规则
     *
     * @param sid
     * @param NoLength
     * @param PrefixStr
     * @param StartNo
     * @param SuffixLength
     * @param colorselector_djq
     * @return
     */
    @RequestMapping("addEntity")
    @ResponseBody
    public String addEntity(String sid, String NoLength, String PrefixStr, String StartNo, String SuffixLength,
                            String colorselector_djq) {
        CouponRule couponRule = new CouponRule();
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        couponRule.setStoreCode(curUserInfo.getStoreCode());
        couponRule.setNolength(new Integer(NoLength));
        couponRule.setPrefixstr(PrefixStr);
        couponRule.setStartno(StartNo);
        couponRule.setSuffixlength(SuffixLength);
        couponRule.setDjbackground(colorselector_djq);
        boolean flag = false;
        if (StringUtils.isNotEmpty(sid)) {
            couponRule.setSid(new Integer(sid));
            couponRule.setUpdateUserid(curUserInfo.getUserId());
            flag = couponRuleService.updateCouponInfo(couponRule);
        } else {
            couponRule.setCreateUserid(curUserInfo.getUserId());
            flag = couponRuleService.saveCouponInfo(couponRule);
        }

        if (flag) {
            return "success";
        } else {
            return "faile";
        }
    }
}
