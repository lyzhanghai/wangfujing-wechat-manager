package com.wfj.controller.system;


import com.alibaba.fastjson.JSON;
import com.wfj.controller.index.BaseController;
import com.wfj.dto.UserAuthorizationStoreDto;
import com.wfj.entity.UserAuthorizationStore;
import com.wfj.service.intf.UserAuthorizationStoreService;
import com.wfj.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhaocj 2016-12-07
 */
@Controller
@RequestMapping("/userAuthorizatioStore/")
public class UserAuthorizatioStoreController extends BaseController {

	@Autowired
	private UserAuthorizationStoreService userAuthorizationStoreService;

	/**
	 *
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("test")
	@ResponseBody
	public String isExist(String name) {
			return "OK";
	}


	@RequestMapping("userAuthorizatioStore")
	public String permissions(String userId,Model model) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId",userId);
		List<UserAuthorizationStoreDto> userAuthorizationStoreDtoList = null;
		try{
			userAuthorizationStoreDtoList =	userAuthorizationStoreService.selectListByUserId(paramMap);
		}catch (Exception e){

		}

		model.addAttribute("userAuthorizatioStoreList", userAuthorizationStoreDtoList);
		System.out.println(JSON.toJSONString(userAuthorizationStoreDtoList));
		return Common.BACKGROUND_PATH + "/system/userAuthorizationStore/userAuthorizationStore";
	}


	/**
	 *
	 * @param userId
	 * @param model
     * @return
     */
	@RequestMapping("getUserAuthorizatioStore")
	public String getUserAuthorizatioStore(String userId,Model model) {
		Map<String,Object> paramMaps = new HashMap<String, Object>();
		paramMaps.put("userId",userId);
		List<UserAuthorizationStore> userAuthorizationStoreDtoList = null;
		try{
			userAuthorizationStoreDtoList =	userAuthorizationStoreService.getselectListByUserId(paramMaps);
		}catch (Exception e){

		}

		model.addAttribute("userAuthorizatioStoreList", userAuthorizationStoreDtoList);
		System.out.println(JSON.toJSONString(userAuthorizationStoreDtoList));
		return Common.BACKGROUND_PATH + "/system/userAuthorizationStore/userAuthorizationStore";
	}

}