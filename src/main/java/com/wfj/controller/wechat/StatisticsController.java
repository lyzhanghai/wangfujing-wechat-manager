package com.wfj.controller.wechat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hyperic.jni.ArchLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfj.dto.Result;
import com.wfj.util.Common;
import com.wfj.util.HttpUtils;
import com.wfj.util.JsonUtil;
import com.wfj.util.WechatUtil;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	
	
	private final static Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	@Autowired
	private WechatUtil tokenUtil;
	
	
	/**
	 * 用户分析，用户增长页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/UserAnalysis")
	public String userAnalysis(HttpServletRequest request,HttpServletResponse response,Model model){
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		List<Object> list = new ArrayList<Object>();
//		map.put("begin_date", "2016-12-01");
//		map.put("end_date", "2015-12-06");
		long time = System.currentTimeMillis();
		logger.info("调查询用户增减数据接口入参:{}",JSON.toJSONString(map));
		logger.info("调查询用户增减数据接口开始时间:{}",time);
//		String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
		String result = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 0,'cancel_user': 0},"
				+ "{'ref_date': '2016-12-06','user_source': 30,'new_user': 2,'cancel_user': 0}]}";
		logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
		logger.info("调查询用户增减数据接口出参:{}",result);
		String userCount = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'cumulate_user': 0},{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2}]}";
		
		JSONObject json = JSON.parseObject(result);
		JSONObject jsonCount = JSON.parseObject(userCount);
		JSONArray jsonArr = JSON.parseArray(json.getString("list"));
		JSONArray jsonArrCount = JSON.parseArray(jsonCount.getString("list"));
		StringBuffer sb = new StringBuffer();
		for(Object obj : jsonArr){
			JSONObject jsonObj = JSON.parseObject(obj.toString());
			for(Object objCount : jsonArrCount){
				JSONObject jsonObjCount = JSON.parseObject(objCount.toString());
				if(jsonObj.getString("ref_date").equals(jsonObjCount.getString("ref_date"))){
					model.addAttribute("ref_date", jsonObj.getString("ref_date"));
					model.addAttribute("cumulate_user", jsonObjCount.getString("cumulate_user"));
					model.addAttribute("cancel_user", jsonObj.getString("cancel_user"));
					model.addAttribute("new_user", jsonObj.getString("new_user"));
				}
			}
		}
		return Common.BACKGROUND_PATH + "/wechat/statistics/useranalysis";
	}
	
	/**
	 * 用户分析，用户属性页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userAttribute")
	public String userAttribute(HttpServletRequest request,HttpServletResponse response){
		
		return Common.BACKGROUND_PATH + "/wechat/statistics/userAttribute";
	}
	
	/**
	 * 获取用户增减数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getUserSummary")
	@ResponseBody
	public String getUserSummary(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		List<Object> list = new ArrayList<Object>();
//		map.put("begin_date", "2016-12-01");
//		map.put("end_date", "2015-12-06");
		long time = System.currentTimeMillis();
		logger.info("调查询用户增减数据接口入参:{}",JSON.toJSONString(map));
		logger.info("调查询用户增减数据接口开始时间:{}",time);
//		String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
		String result = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 0,"
				+ "'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user':"
				+ " 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0}]}";
		logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
		logger.info("调查询用户增减数据接口出参:{}",result);
		String userCount = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'cumulate_user"
				+ "': 0},{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2}]}";
		
		JSONObject json = JSON.parseObject(result);
		JSONObject jsonCount = JSON.parseObject(userCount);
		JSONArray jsonArr = JSON.parseArray(json.getString("list"));
		JSONArray jsonArrCount = JSON.parseArray(jsonCount.getString("list"));
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(Object obj : jsonArr){
			JSONObject jsonObj = JSON.parseObject(obj.toString());
			for(Object objCount : jsonArrCount){
				JSONObject jsonObjCount = JSON.parseObject(objCount.toString());
				if(jsonObj.getString("ref_date").equals(jsonObjCount.getString("ref_date"))){
					sb.append("<tr id='tr"+i+"'>" + "<td class='table_cell'>" + jsonObj.getString("ref_date") + "</td>")
					.append("<td class='table_cell tr js_new_user'>" + jsonObj.getString("new_user") + "</td>")
					.append("<td class='table_cell tr js_cancel_user'>" + jsonObj.getString("cancel_user") + "</td>")
					.append("<td class='table_cell tr js_netgain_user'>" + jsonObj.getString("new_user") + "</td>")
					.append("<td class='table_cell tr js_cumulate_user'>" + jsonObjCount.getString("cumulate_user") + "</td></tr>");
					i ++;
					break;
				}
			}
			
		}
		return JSON.toJSONString(sb);
	}
	
	/**
	 * 获取累计用户数据     
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getUserCumulate")
	@ResponseBody
	public String getUserCumulate(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + access_token;
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("begin_date", "2016-12-01");
		map.put("end_date", "2015-12-06");
		long time = System.currentTimeMillis();
		logger.info("调查询累计用户数据接口入参:{}",JSON.toJSONString(map));
		logger.info("调查询累计用户数据接口开始时间:{}",time);
		String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
		logger.info("调查询累计用户数据口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
		logger.info("调查询累计用户数据接口出参:{}",result);
		return result;
	}

	
	@RequestMapping("/getCurveData")
	@ResponseBody
	public Map<Object,Object> getCurveData(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		Map<Object,Object> returnStr = new HashMap<Object, Object>();
		String dateStr = request.getParameter("dateStr");
		try {
			Map<Object,Object> map = new HashMap<Object, Object>();
			List<Object> list = new ArrayList<Object>();
//			map.put("begin_date", "2016-12-01");
//			map.put("end_date", "2015-12-06");
			long time = System.currentTimeMillis();
			logger.info("调查询用户增减数据接口入参:{}",JSON.toJSONString(map));
			logger.info("调查询用户增减数据接口开始时间:{}",time);
//			String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
			String resultStr = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 0,'cancel_user': 0},"
					+ "{'ref_date': '2016-12-06','user_source': 30,'new_user': 2,'cancel_user': 0}]}";
//			logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
//			logger.info("调查询用户增减数据接口出参:{}",result);
//			String userCount = "{'list': [{'ref_date': '202-05','user_source': 0,'cumulate_user': 0},{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2}]}";
			
			JSONObject json = JSON.parseObject(resultStr);
			JSONArray jsonArr = JSON.parseArray(json.getString("list"));
			StringBuffer sbTime = new StringBuffer();
			StringBuffer sbData = new StringBuffer();
			for(Object obj : jsonArr){
				JSONObject jsonObj = JSON.parseObject(obj.toString());
				sbTime.append(jsonObj.getString("ref_date")+",");
				sbData.append(jsonObj.getString("new_user")+",");
			}
			returnStr.put("list",jsonArr);
			String resultDate = dateTime(dateStr);
			returnStr.put("date",resultDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("服务器内部错误:{}",e);
		}
		return returnStr;
	}
	
	public static String dateTime(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DATE, cale.get(Calendar.DATE) - 1);
		String dataStr = sdf.format(cale.getTime());
		cale.set(Calendar.DATE, cale.get(Calendar.DATE)-Integer.parseInt(dateStr));
		String dataStr1 = sdf.format(cale.getTime());
		String returnDate = dataStr1 + " - " +dataStr;
		return returnDate;
	}
}
