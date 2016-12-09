package com.wfj.service.intf;

import java.util.List;
import java.util.Map;

import com.wfj.entity.CouponTemplate;
import com.wfj.entity.DataTableResult;

public interface CouponTemplateService {
	public DataTableResult<CouponTemplate> selectPageListByParam(Map<String, Object> paramMap);

	public int insertSelective(CouponTemplate entity);

	public int deleteByPrimaryKey(Integer sid);

	public int updateByPrimaryKeySelective(CouponTemplate entity);

	public List<CouponTemplate> selectListByParam(CouponTemplate entity);
}
