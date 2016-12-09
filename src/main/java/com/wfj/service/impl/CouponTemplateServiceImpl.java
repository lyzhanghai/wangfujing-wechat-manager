package com.wfj.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wfj.entity.CouponInfo;
import com.wfj.entity.CouponTemplate;
import com.wfj.entity.DataTableResult;
import com.wfj.mapper.CouponInfoMapper;
import com.wfj.mapper.CouponTemplateMapper;
import com.wfj.service.intf.CouponTemplateService;

@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

	@Autowired
	private CouponTemplateMapper couponTemplateMapper;
	@Autowired
	private CouponInfoMapper couponInfoMapper;

	@Transactional
	public int insertSelective(CouponTemplate entity) {
		return couponTemplateMapper.insertSelective(entity);
	}

	@Transactional
	public int deleteByPrimaryKey(Integer sid) {
		CouponInfo couponInfo = new CouponInfo();
		couponInfo.setTplSid(sid);
		int count = couponInfoMapper.getCountByParam(couponInfo);
		if (count == 0) {
			CouponTemplate entity = new CouponTemplate();
			entity.setSid(sid);
			entity.setIfdel(1);
			return couponTemplateMapper.updateByPrimaryKey(entity);
		} else {
			return 0;
		}
	}

	/**
	 * update
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2016年12月9日 By yedong
	 * @param entity
	 * @return int
	 */
	@Transactional
	public int updateByPrimaryKeySelective(CouponTemplate entity) {
		return couponTemplateMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 
	 * @Methods Name selectPageListByParam
	 * @Create In 2016年12月9日 By yedong
	 * @param paramMap
	 * @return DataTableResult<CouponTemplate>
	 */
	public DataTableResult<CouponTemplate> selectPageListByParam(Map<String, Object> paramMap) {
		DataTableResult<CouponTemplate> page = new DataTableResult<CouponTemplate>();

		List<CouponTemplate> couponList = couponTemplateMapper.selectPageListByParam(paramMap);
		if (couponList != null && couponList.size() > 0) {
			page.setAaData(couponList);
		}
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer count = couponTemplateMapper.getCountByParam(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	public List<CouponTemplate> selectListByParam(CouponTemplate entity) {
		return couponTemplateMapper.selectListByParam(entity);
	}

	public int getCountByParam(CouponTemplate entity) {
		return couponTemplateMapper.getCountByParam(entity);
	}

}
