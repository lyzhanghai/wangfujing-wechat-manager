package com.wfj.controller.system;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.controller.index.BaseController;
import com.wfj.entity.ServerInfoFormMap;
import com.wfj.mapper.ServerInfoMapper;
import com.wfj.plugin.PageView;
import com.wfj.util.Common;
import com.wfj.util.PropertiesUtils;
import com.wfj.util.SystemInfo;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/monitor/")
public class MonitorController extends BaseController {
	
	@Inject
	private ServerInfoMapper serverInfoMapper ;
	@RequestMapping("list")
	public String listUI() throws Exception {
		return Common.BACKGROUND_PATH + "/system/monitor/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize) throws Exception {
		ServerInfoFormMap serverInfoFormMap = getFormMap(ServerInfoFormMap.class);
		serverInfoFormMap=toFormMap(serverInfoFormMap, pageNow, pageSize,serverInfoFormMap.getStr("orderby"));
        pageView.setRecords(serverInfoMapper.findByPage(serverInfoFormMap));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("findByPage2")
	public DataTableResult findByPage2(DataTableParams dataTableParams) throws Exception {
		ServerInfoFormMap serverInfoFormMap = getFormMap(ServerInfoFormMap.class);
		String pageNow = ((dataTableParams.getiDisplayStart()+1)%dataTableParams.getiDisplayLength()>0?(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength()+1:(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength())+"";
		serverInfoFormMap=toFormMap(serverInfoFormMap, pageNow, dataTableParams.getiDisplayLength()+"",serverInfoFormMap.getStr("orderby"));
		pageView.setRecords(serverInfoMapper.findByPage(serverInfoFormMap));
		DataTableResult<ServerInfoFormMap> dataTableResult = new DataTableResult<ServerInfoFormMap>();
		dataTableResult.setsEcho(dataTableParams.getsEcho());
		dataTableResult.setAaData(serverInfoMapper.findByPage(serverInfoFormMap));
		dataTableResult.setiTotalDisplayRecords(pageView.getRowCount());
		dataTableResult.setiTotalRecords(pageView.getRowCount());
		return dataTableResult;
	}
	
	@RequestMapping("info")
	public String info(Model model) throws Exception {
		model.addAttribute("cpu", PropertiesUtils.findPropertiesKey("cpu"));
		model.addAttribute("jvm", PropertiesUtils.findPropertiesKey("jvm"));
		model.addAttribute("ram", PropertiesUtils.findPropertiesKey("ram"));
		model.addAttribute("toEmail", PropertiesUtils.findPropertiesKey("toEmail"));
		return Common.BACKGROUND_PATH + "/system/monitor/info";
	}
	
	@RequestMapping("monitor")
	public String monitor() throws Exception {
		return Common.BACKGROUND_PATH + "/system/monitor/monitor";
	}
	
	@RequestMapping("systemInfo")
	public String systemInfo(Model model) throws Exception {
		model.addAttribute("systemInfo", SystemInfo.SystemProperty());
		return Common.BACKGROUND_PATH + "/system/monitor/systemInfo";
	}
	
	@ResponseBody
	@RequestMapping("usage")
	public ServerInfoFormMap usage(Model model) throws Exception {
		return SystemInfo.usage(new Sigar());
	}
	/**
	 * 修改配置　
	 * @param request
	 * @param nodeId
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
	@RequestMapping("/modifySer")
    public Map<String, Object> modifySer(String key,String value) throws Exception{
    	Map<String, Object> dataMap = new HashMap<String,Object>();
    	try {
		// 从输入流中读取属性列表（键和元素对）
    		PropertiesUtils.modifyProperties(key, value);
		} catch (Exception e) {
			dataMap.put("flag", false);
		}
    	dataMap.put("flag", true);
		return dataMap;
    }
}