package com.wfj.controller.wechat;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.annotation.SystemLog;
import com.wfj.controller.index.BaseController;
import com.wfj.dto.WechatAppDto;
import com.wfj.entity.AppAccountInfo;
import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.util.Common;
import com.wfj.util.StringUtils;

/**
 * wechatAPP
 *
 * @author kongqf
 * @create 2016-12-02
 */

@Controller
@RequestMapping("/wechatapp")
public class WechatAppController extends BaseController {
	private Logger logger = Logger.getLogger(WechatAppController.class);

	@Autowired
	private AppAccountInfoMapper appInfoMapper;

	@RequestMapping("list")
	public String listUI(Model model, Integer id) throws Exception {
		model.addAttribute("res", findByRes());
		model.addAttribute("menuId", 2);
		return Common.BACKGROUND_PATH + "/wechat/wechatapp/list";
	}

	@ResponseBody
	@RequestMapping(value = "/findByPage2")
	public DataTableResult<WechatAppDto> findByPage2(DataTableParams dataTableParams,
			String businessName) throws Exception {
		WechatAppDto searchDto = new WechatAppDto();
		searchDto.setLimit(dataTableParams.getiDisplayStart());
		searchDto.setLimit(dataTableParams.getiDisplayLength());

		List<WechatAppDto> appDtoList = appInfoMapper.selectAppInfoListByParam(searchDto);
		DataTableResult<WechatAppDto> dataTableResult = new DataTableResult<WechatAppDto>();
		dataTableResult.setsEcho(dataTableParams.getsEcho());
		dataTableResult.setAaData(appDtoList);
		dataTableResult.setiTotalDisplayRecords(appDtoList.size());
		dataTableResult.setiTotalRecords(appDtoList.size());
		return dataTableResult;
	}

	/**
	 * 跳转到新增界面
	 *
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/wechat/wechatapp/add";
	}

	/**
	 * 新增
	 *
	 * @param storeCode
	 * @param appid
	 * @param appsecret
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addEntity")
	@ResponseBody
	@Transactional(readOnly = false)
	@SystemLog(module = "基础设置", methods = "公众号管理-新增")
	public String addEntity(String storeCode, String appid, String appsecret, String sid)
			throws Exception {
		boolean flag = false;
		if (checkAppStoreisExists(storeCode)) {
			return "该门店公众号已存在,请确认!";
		}
		AppAccountInfo model = new AppAccountInfo();
		model.setAppid(appid);
		model.setAppsecret(appsecret);
		model.setStorecode(storeCode);
		if (StringUtils.isNotEmpty(sid)) {
			model.setSid(new Long(sid));
			int count = appInfoMapper.updateByPrimaryKeySelective(model);
			if (count != 0) {
				flag = true;
			}
		} else {
			int count = appInfoMapper.insertSelective(model);
			if (count != 0) {
				flag = true;
			}
		}
		if (flag) {
			return "success";

		} else {
			return "faile";
		}
	}

	public boolean checkAppStoreisExists(String storeCode) {
		boolean flag = false;
		WechatAppDto dto = new WechatAppDto();
		dto.setStorecode(storeCode);
		List<WechatAppDto> list = appInfoMapper.selectAppInfoListByParam(dto);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String sid = getPara("sid");
		if (StringUtils.isNotEmpty(sid)) {
			AppAccountInfo appAccountInfo = appInfoMapper.selectByPrimaryKey(new Long(sid));
			model.addAttribute("app", appAccountInfo);
		}
		return Common.BACKGROUND_PATH + "/wechat/wechatapp/edit";
	}

	/**
	 * 根据ID删除菜单
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@SystemLog(module = "基础设置", methods = "公众号管理-删除")
	public String deleteEntity(Model model) throws Exception {
		String ids = getPara("ids");
		if (StringUtils.isNotEmpty(ids)) {
			String[] array = ids.split(",");
			for (String id : array) {
				AppAccountInfo appAccountInfo = new AppAccountInfo();
				appAccountInfo.setSid(new Long(id));
				appAccountInfo.setDelFlag(1);

				int count = appInfoMapper.updateByPrimaryKeySelective(appAccountInfo);
			}
		}

		return "success";
	}
}
