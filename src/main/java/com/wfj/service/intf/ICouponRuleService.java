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

    /**
     * 根据sid查询券规则
     *
     * @param sid
     * @return
     */
    public CouponRule queryCouponInfoBySid(Integer sid);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    public boolean saveCouponInfo(CouponRule record);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    public boolean updateCouponInfo(CouponRule record);
}
