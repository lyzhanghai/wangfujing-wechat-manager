package com.wfj.service.impl;

import com.wfj.entity.CouponRule;
import com.wfj.mapper.CouponRuleMapper;
import com.wfj.service.intf.ICouponRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Coupon Rule Service
 *
 * @author kongqf
 * @create 2016-12-07
 */
@Service
public class CouponRuleServiceImpl implements ICouponRuleService {

    @Autowired
    private CouponRuleMapper couponRuleMapper;

    /**
     * 优惠券规则信息
     *
     * @return
     */
    public List<CouponRule> queryCouponInfo(CouponRule record) {
        List<CouponRule> list = couponRuleMapper.selectCouponRuleInfo(record);

        return list;
    }
}
