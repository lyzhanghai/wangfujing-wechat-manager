package com.wfj.controller.coupon;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.annotation.SystemLog;
import com.wfj.controller.index.BaseController;
import com.wfj.dto.CouponTemplateDto;
import com.wfj.entity.CouponTemplate;
import com.wfj.entity.DataTableResult;
import com.wfj.service.intf.CouponTemplateService;
import com.wfj.util.Common;

/**
 * CouponTPLController
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Controller
@RequestMapping("coupontpl")
public class CouponTPLController extends BaseController {

	@Autowired
	private CouponTemplateService couponTPLService;

	@RequestMapping("getlist")
	public String getlist(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/coupon/tpl/list";
	}

	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/tpl/add";
	}

	@ResponseBody
	@RequestMapping("/findCouponTPLByPage")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-分页查询")
	public DataTableResult<CouponTemplate> findCouponTPLByPage(
			@RequestBody CouponTemplateDto para) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		paramMap.put("ifdel", 0);
		try {
			BeanUtils.copyProperties(paramMap, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		DataTableResult<CouponTemplate> page = couponTPLService.selectPageListByParam(paramMap);
		page.setiTotalDisplayRecords(page.getAaData().size());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@ResponseBody
	@RequestMapping("/addCouponTPL")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-新增")
	public String addCouponTPL(@RequestBody CouponTemplateDto para) {
		CouponTemplate entity = new CouponTemplate();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int i = couponTPLService.insertSelective(entity);
		if (i == 1) {
			return "success";
		} else {
			return "faile";
		}
	}

	@ResponseBody
	@RequestMapping("/deleteCouponTPL")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-删除")
	public String deleteCouponTPL(Model model) {
		String sid = getPara("sid");
		int del = couponTPLService.deleteByPrimaryKey(Integer.parseInt(sid));
		if (del > 0) {
			return "success";
		} else {
			return "faile";
		}
	}

}
