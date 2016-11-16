package com.wfj.controller.system;

import javax.inject.Inject;

import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.mapper.UserLoginMapper;
import com.wfj.controller.index.BaseController;
import com.wfj.entity.UserLoginFormMap;
import com.wfj.plugin.PageView;
import com.wfj.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/userlogin/")
public class UserLoginController extends BaseController {
	@Inject
	private UserLoginMapper userLoginMapper;

	@RequestMapping("listUI")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/userlogin/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		UserLoginFormMap userLoginFormMap = getFormMap(UserLoginFormMap.class);
		userLoginFormMap=toFormMap(userLoginFormMap, pageNow, pageSize,userLoginFormMap.getStr("orderby"));
        pageView.setRecords(userLoginMapper.findByPage(userLoginFormMap));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("findByPage2")
	public DataTableResult findByPage(DataTableParams dataTableParams) throws Exception {
		UserLoginFormMap userLoginFormMap = getFormMap(UserLoginFormMap.class);
		String pageNow = ((dataTableParams.getiDisplayStart()+1)%dataTableParams.getiDisplayLength()>0?(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength()+1:(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength())+"";
		userLoginFormMap=toFormMap(userLoginFormMap, pageNow, dataTableParams.getiDisplayLength()+"",userLoginFormMap.getStr("orderby"));
		pageView.setRecords(userLoginMapper.findByPage(userLoginFormMap));//不调用默认分页,调用自已的mapper中findUserPage
		DataTableResult<UserLoginFormMap> dataTableResult = new DataTableResult<UserLoginFormMap>();
		dataTableResult.setsEcho(dataTableParams.getsEcho());
		dataTableResult.setAaData(userLoginMapper.findByPage(userLoginFormMap));
		dataTableResult.setiTotalDisplayRecords(pageView.getRowCount());
		dataTableResult.setiTotalRecords(pageView.getRowCount());
		return dataTableResult;
	}
}