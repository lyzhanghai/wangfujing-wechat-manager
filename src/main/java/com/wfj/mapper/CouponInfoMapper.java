package com.wfj.mapper;

import java.util.List;
import java.util.Map;

import com.wfj.entity.CouponInfo;
import com.wfj.entity.CouponTemplate;

public interface CouponInfoMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(CouponInfo record);

	int insertSelective(CouponInfo record);

	CouponInfo selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(CouponInfo record);

	int updateByPrimaryKeyWithBLOBs(CouponInfo record);

	int updateByPrimaryKey(CouponInfo record);

	List<CouponTemplate> selectListByParam(CouponInfo entity);

	List<CouponTemplate> selectPageListByParam(CouponInfo entity);

	Integer getCountByParam(CouponInfo entity);

	List<CouponTemplate> selectListByParam(Map<String, Object> paramMap);

	List<CouponTemplate> selectPageListByParam(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);
}