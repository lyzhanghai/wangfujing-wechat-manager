package com.wfj.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfj.dto.ArticleDto;
import com.wfj.dto.MaterialCount;
import com.wfj.dto.MaterialDto;
import com.wfj.dto.MediaDto;
import com.wfj.service.intf.MaterialService;
import com.wfj.util.HttpUtils;
import com.wfj.util.JsonUtil;
import com.wfj.util.WechatUtil;

@Service
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private WechatUtil tokenUtil;

	private static Logger logger = Logger.getLogger(MaterialServiceImpl.class);

	// private String appId = "wx871d0104ae72e615";
	// private String appSecret = "00e66c2772af76181745b6f5d92b5801";
	private String appId = "wx7aec942c6742752d";
	private String appSecret = "c27b7472a3bb1e9874c240a681b87880";

	/**
	 * 图文永久素材上传
	 * 
	 * @Methods Name articleInsert
	 * @Create In 2016年11月28日 By yedong
	 * @param artList
	 * @return String
	 */
	public String articleInsert(List<ArticleDto> artList) {
		logger.info("start-uploadPhoto,param ,artList" + artList);
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String articleUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="
				+ access_token;
		Map<String, Object> articles = new HashMap<String, Object>();
		articles.put("articles", artList);
		String media = null;
		try {
			media = HttpUtils.doPost(articleUrl, JsonUtil.getJSONString(articles));
		} catch (Exception e) {
			media = null;
		}
		return media;
	}

	/**
	 * 返回url的图片上传
	 * @param path
	 * @param param buffer/media
	 * @return
	 */
	public String imageInsert(String path,String param) {
		logger.info("start-imageInsert,param ,path" + path);
		String reString = null;
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String[] cmds = { "curl", "-F", ""+param+"=@" + path,
				"https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token };
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				reString = line;
			}
			MediaDto media = JsonUtil.getJacksonDTO(reString, MediaDto.class);
			br.close();
			return media.getUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 图片永久素材上传
	 * 
	 * @Methods Name materialInsert
	 * @Create In 2016年11月24日 By yedong
	 * @param filePath
	 * @param type
	 * @return Media
	 */
	public MediaDto materialInsert(String filePath, String type, String title,
			String introduction) {
		logger.info("start-uploadPhoto,param ,filePath" + filePath + "type " + type);
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String reString = "";
		String[] cmds = { "curl", "-F", "media=@" + filePath,
				"https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
						+ access_token + "&type=" + type };
		// curl
		// "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE"
		// -F media=@media.file -F description='{"title":VIDEO_TITLE,
		// "introduction":INTRODUCTION}'
		if (type.equals("video")) {
			String description = "description='{\"title\":\"" + title + "\",\"introduction\":\""
					+ introduction + "\"}'";
			logger.info("+++++++++++++++" + description);
			String[] cmds2 = { "curl",
					"\"https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
							+ access_token + "&type=" + type + "\" -F " + "media=@" + filePath,
					" -F " + description };
			cmds = cmds2;
		}

		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			logger.info(pb.environment());
			logger.info(pb.command());
			p = pb.start();
			BufferedReader br = null;
			String line = null;

			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				reString = line;
			}
			System.out.println("---------" + reString);
			MediaDto media = JsonUtil.getJacksonDTO(reString, MediaDto.class);
			br.close();
			return media;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取永久素材列表
	 * 
	 * @Methods Name getMaterialList
	 * @Create In 2016年11月24日 By yedong
	 * @param start
	 * @param limit
	 * @param type
	 * @return MaterialDto
	 */
	public MaterialDto getMaterialList(int offset, int count, String type) {
		logger.info("start-getMaterialList,param ,type " + type);
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String getMaterialPath = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="
				+ access_token;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("offset", offset);
		paramMap.put("count", count);
		paramMap.put("type", type);
		String reString = HttpUtils.doPost(getMaterialPath, JsonUtil.getJSONString(paramMap));
		MaterialDto material = JsonUtil.getJacksonDTO(reString, MaterialDto.class);
		logger.info(material);
		return material;
	}

	/**
	 * 删除永久素材
	 * 
	 * @Methods Name materialDelete
	 * @Create In 2016年11月24日 By yedong
	 * @param mediaId
	 * @return String
	 */
	public String materialDelete(String mediaId) {
		logger.info("start-materialDelete,param ,mediaId " + mediaId);
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String delMateriaPath = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token="
				+ access_token;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("media_id", mediaId);
		String reString = HttpUtils.doPost(delMateriaPath, JsonUtil.getJSONString(paramMap));
		logger.info(reString);
		return reString;
	}

	/**
	 * 获取永久素材数量
	 * 
	 * @Methods Name getMaterialCount
	 * @Create In 2016年11月24日 By yedong
	 * @return MaterialCount
	 */
	public MaterialCount getMaterialCount() {
		// https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=
		logger.info("start-getMaterialCount");
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String getCountPath = "https://api.weixin.qq.com/cgi-bin/material";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("access_token", access_token);
		String reString = HttpUtils.HttpGet(getCountPath, "/get_materialcount", paramMap);
		MaterialCount materialCount = JsonUtil.getJacksonDTO(reString, MaterialCount.class);
		logger.info(materialCount);
		return materialCount;
	}

	/**
	 * 根据mediaId获取素材信息
	 * 
	 * @Methods Name getMaterialByMediaId
	 * @Create In 2016年12月6日 By yedong
	 * @param mediaId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getMaterialByMediaId(String mediaId) {
		String access_token = tokenUtil.getAccessToken(appId, appSecret);
		String getCountPath = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="
				+ access_token;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("media_id", mediaId);
		String doPost = HttpUtils.doPost(getCountPath, JsonUtil.getJSONString(map));
		try {
			MediaDto material = JsonUtil.getDTO(doPost, MediaDto.class);
			paramMap.put("material", material);
			paramMap.put("success", "success");
		} catch (Exception e) {
			paramMap.put("success", "error");
		}
		return paramMap;
	}

}
