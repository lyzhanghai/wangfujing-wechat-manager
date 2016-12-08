package com.wfj.mapper;

import com.wfj.entity.CouponTemplate;

public interface CouponTemplateMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(CouponTemplate record);

    int insertSelective(CouponTemplate record);

    CouponTemplate selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(CouponTemplate record);

    int updateByPrimaryKey(CouponTemplate record);
}