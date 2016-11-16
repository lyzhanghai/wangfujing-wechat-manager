package com.wfj.controller.system;

import javax.inject.Inject;

import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.mapper.LogMapper;
import com.wfj.controller.index.BaseController;
import com.wfj.entity.LogFormMap;
import com.wfj.plugin.PageView;
import com.wfj.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/log/")
public class LogController extends BaseController {
	@Inject
	private LogMapper logMapper;

	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/log/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		LogFormMap logFormMap = getFormMap(LogFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by id asc";
		}
		
		logFormMap.put("$orderby", order);
		logFormMap=toFormMap(logFormMap, pageNow, pageSize,logFormMap.getStr("orderby"));
        pageView.setRecords(logMapper.findByPage(logFormMap));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("findByPage2")
	public DataTableResult findByPage2(DataTableParams dataTableParams) throws Exception {
		LogFormMap logFormMap = getFormMap(LogFormMap.class);
		String pageNow = ((dataTableParams.getiDisplayStart()+1)%dataTableParams.getiDisplayLength()>0?(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength()+1:(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength())+"";
		logFormMap=toFormMap(logFormMap, pageNow, dataTableParams.getiDisplayLength()+"",logFormMap.getStr("orderby"));
		pageView.setRecords(logMapper.findByPage(logFormMap));
		DataTableResult<LogFormMap> dataTableResult = new DataTableResult<LogFormMap>();
		dataTableResult.setsEcho(dataTableParams.getsEcho());
		dataTableResult.setAaData(logMapper.findByPage(logFormMap));
		dataTableResult.setiTotalDisplayRecords(pageView.getRowCount());
		dataTableResult.setiTotalRecords(pageView.getRowCount());
		return dataTableResult;
	}
}