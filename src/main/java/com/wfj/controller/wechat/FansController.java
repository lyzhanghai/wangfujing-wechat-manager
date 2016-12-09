package com.wfj.controller.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.wfj.controller.index.BaseController;
import com.wfj.entity.AppAccountInfo;
import com.wfj.entity.DataTableResult;
import com.wfj.entity.fans.FansOpenidList;
import com.wfj.entity.fans.FansGroupList;
import com.wfj.entity.fans.FansListPost;
import com.wfj.entity.fans.FansPost;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.util.Common;
import com.wfj.util.HttpUtil;
import com.wfj.util.HttpUtils;
import com.wfj.util.JsonUtils;
import com.wfj.util.RedisUtil;
import com.wfj.util.WechatUtil;

import net.sf.json.JSON;

@Controller
@RequestMapping("/fans/")
public class FansController extends BaseController {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private AppAccountInfoMapper appAccountInfoMapper;
	@Autowired
	private WechatUtil wechatUtil;
	private Logger logger = Logger.getLogger(FansController.class);

	@RequestMapping("list")
	public String listUI(Model model, Integer id) throws Exception {

		Session session = SecurityUtils.getSubject().getSession();
		String storecode = redisUtil.get(Common.USER_STORE_K + session.getAttribute("userSessionId"), "");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("storecode", storecode);
		List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paraMap);
		String appid = appAccountInfoList.get(0).getAppid();
		String appsecret = appAccountInfoList.get(0).getAppsecret();
		String accessToken = wechatUtil.getAccessToken(appid, appsecret);

		// create_tag("test",accessToken);//新建标签测试

		String openurl = "https://api.weixin.qq.com/cgi-bin/user";
		String method = "get";
		Map<String, Object> fansOpenidParam = new HashMap<String, Object>();
		fansOpenidParam.put("access_token", accessToken);
		fansOpenidParam.put("next_openid", "");
		String resutl = HttpUtils.HttpGetByUtf(openurl, method, fansOpenidParam);
		FansOpenidList fansOpenidList = com.alibaba.fastjson.JSON.parseObject(resutl, FansOpenidList.class);
		List<FansPost> user_list = new ArrayList<FansPost>();
		FansListPost fansListPost = new FansListPost();

		for (String openid : fansOpenidList.getData().getOpenid()) {
			FansPost fansPost = new FansPost();
			fansPost.setOpenid(openid);
			fansPost.setLang("zh-CN");
			user_list.add(fansPost);
		}
		fansListPost.setUser_list(user_list);
		String userlist_result = HttpUtils.doPost(
				"https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken,
				com.alibaba.fastjson.JSON.toJSONString(fansListPost));
		logger.info("获取到粉丝结果集：" + userlist_result);

		String fansGroupUrl = "https://api.weixin.qq.com/cgi-bin/groups";
		Map<String, Object> fansGroupParam = new HashMap<String, Object>();
		fansGroupParam.put("access_token", accessToken);
		String fansGroupResult = HttpUtils.HttpGetByUtf(fansGroupUrl, method, fansGroupParam);
		logger.info("获取到所有粉丝分组:" + fansGroupResult);
		FansGroupList fansGroupList = com.alibaba.fastjson.JSON.parseObject(fansGroupResult, FansGroupList.class);

		model.addAttribute("res", findByRes());
		model.addAttribute("menuId", id);
		return Common.BACKGROUND_PATH + "/wechat/fans/list";
	}

	/**
	 * 打标签
	 * 
	 * @return
	 */
	public void add_tag(List<String> openids, String accessToken, List<String> groupids) {
		String addTagUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=";
		for (String groupid : groupids) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("openid_list", openids);
			map.put("to_groupid", groupid);
			String create_group = HttpUtils.doPost(addTagUrl + accessToken, JsonUtils.mapToJson(map));
			logger.info("给用户"+openids+"添加标签："+"groupid");
		}

	}

	/**
	 * 新建标签
	 * 
	 * @return
	 */
	public void create_tag(String groupName, String accessToken) {
		String createTagUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=";
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", groupName);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("group", map);
		String create_group = HttpUtils.doPost(createTagUrl + accessToken, JsonUtils.mapToJson(map1));
		logger.info("新建标签：" + create_group);
	}

	/**
	 * 删除标签
	 * 
	 * @return
	 */
	public void delete_tag(String groupid,String accessToken ) {
		String deleteTagUrl="https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", groupid);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("group", map);
		String delete_group = HttpUtils.doPost(deleteTagUrl + accessToken, JsonUtils.mapToJson(map1));
		logger.info("删除标签：" + delete_group);
	}

	/**
	 * 重命名标签
	 * 
	 * @return
	 */
	public void rename_tag(String groupid,String name,String accessToken ) {
		String updateTagnameUrl="https://api.weixin.qq.com/cgi-bin/groups/update?access_token=";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", groupid);
		map.put("name", name);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("group", map);
		String crename_group = HttpUtils.doPost(updateTagnameUrl + accessToken, JsonUtils.mapToJson(map1));
		logger.info("重命名分组：" + crename_group);
	}

	/**
	 * 粉丝去除标签
	 * 
	 * @return
	 */
	public String remove_tag() {
		return null;
	}

	/**
	 * 修改备注
	 * 
	 * @return
	 */
	public String modifyRemarks() {
		return null;
	}

	/**
	 * 拉入黑名单
	 * 
	 * @return
	 */
	public String shielding() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public DataTableResult findPage() {

		return null;
	}
}
