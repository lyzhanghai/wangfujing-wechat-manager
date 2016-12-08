package com.wfj.service.intf;

import com.wfj.entity.CouponRule;

import java.util.List;

/**
 * @author kongqf
 * @create 2016-12-07
 */
public interface ICouponRuleService {

    /**
     * 优惠券规则信息
     *
     * @return
     */
    public List<CouponRule> queryCouponInfo(CouponRule record);

}
