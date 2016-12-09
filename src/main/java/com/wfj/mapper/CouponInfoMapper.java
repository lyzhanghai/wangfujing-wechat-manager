package com.wfj.mapper;

import com.wfj.entity.CouponInfo;

public interface CouponInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    CouponInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKeyWithBLOBs(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);
}