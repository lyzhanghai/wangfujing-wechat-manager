package com.wfj.controller.wechat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wfj.dto.ArticleDto;
import com.wfj.dto.MediaDto;
import com.wfj.entity.MsgReply;
import com.wfj.service.intf.MaterialService;
import com.wfj.service.intf.MsgReplyService;
import com.wfj.util.Common;
import com.wfj.util.MessageUtil;

@Controller
@RequestMapping("upload")
public class UploadController {
	private static Logger logger = Logger.getLogger(UploadController.class);

	@Autowired
	private MaterialService materialService;

	@Autowired
	private MsgReplyService msgReplyService;

	/**
	 * 图片/文件上传
	 */
	@RequestMapping(value = "/articleAdd")
	public String articleAdd(Model model) throws Exception {
		System.out.println(Common.BACKGROUND_PATH + "/system/article/add");
		return Common.BACKGROUND_PATH + "/system/article/add";
	}

	/**
	 * 图片/文件上传
	 */
	@ResponseBody
	@RequestMapping(value = "/photoUpload", method = RequestMethod.POST)
	public Map<String, Object> photoUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		String fileType = request.getParameter("fileType");
		String eventType = request.getParameter("eventType");
		String ruleName = request.getParameter("ruleName");
		String msgKey = request.getParameter("msgKey");
		if (fileType.equals("text")) {
			String textContent = request.getParameter("textContent");
			MsgReply msgReply = new MsgReply();
			msgReply.setEventType(eventType);
			msgReply.setContent(textContent);
			msgReply.setMsgKey(msgKey);
			msgReply.setRuleName(ruleName);
			int iORu = msgReplyService.msgReplyInsertOrUpdate(msgReply);
			logger.info(iORu);
		} else {// 回复类型(0文本,1图片,2语音,3视频,4音频,5图文)
			String imgMedia = request.getParameter("imgMedia");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (imgMedia != null) {
				paramMap = materialService.getMaterialByMediaId(imgMedia);
			} else {
				paramMap = fileUpload(file, request, response);
			}
			if ("success".equals((String) paramMap.get("success"))) {
				MediaDto material = (MediaDto) paramMap.get("material");
				MsgReply msgReply = new MsgReply();
				msgReply.setEventType(eventType);
				msgReply.setMsgKey(msgKey);
				msgReply.setRuleName(ruleName);
				if (fileType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
					msgReply.setMsgType(1);
				} else if (fileType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
					msgReply.setMsgType(3);
					String title = request.getParameter("title");
					String introduction = request.getParameter("introduction");
					msgReply.setTitle(title);
					msgReply.setDescription(introduction);
				} else if (fileType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					msgReply.setMsgType(2);
				}
				msgReply.setMediaId(material.getMedia_id());
				msgReply.setPicUrl(material.getUrl());
				int iORu = msgReplyService.msgReplyInsertOrUpdate(msgReply);
				logger.info(iORu);
			}
		}
		return null;
	}

	/**
	 * 图文素材上传
	 */
	@ResponseBody
	@RequestMapping(value = "/articleUpload", method = RequestMethod.POST)
	public String articleUpload(@RequestBody List<ArticleDto> artList)
			throws IllegalStateException, IOException {
		String articleInsert = materialService.articleInsert(artList);
		if (articleInsert == null) {
			logger.info("error");
			return "error";
		}
		logger.info("success");
		return "success";
	}

	/**
	 * 返回url的图片上传
	 */
	public Map<String, Object> imgUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("success", "error");
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			logger.info("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1
					? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// 判断文件类型是否为空
				if (!"PNG".equals(type.toUpperCase()) && !"JPG".equals(type.toUpperCase())) {
					logger.info("不是我们想要的文件类型,请按要求重新上传");
					paramMap.put("errMsg", "不是我们想要的文件类型,请按要求重新上传");
					return paramMap;
				}
				// 项目在容器中实际发布运行的根路径
				// String realPath =
				// request.getSession().getServletContext().getRealPath("/");
				String realPath = System.getProperty("user.dir") + "/";
				// 自定义的文件名称
				String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
				// 设置存放图片文件的路径
				path = realPath + /* System.getProperty("file.separator")+ */trueFileName;
				logger.info("存放图片文件的路径:" + path);
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				logger.info("文件成功上传到指定目录下");
				String url = materialService.imageInsert(null, path);
				if (url != null) {
					paramMap.put("success", "success");
					paramMap.put("url", url);
					return paramMap;
				}
			} else {
				logger.info("文件类型为空");
				paramMap.put("errorMsg", "文件类型为空");
				return paramMap;
			}
		} else {
			logger.info("没有找到相对应的文件");
			paramMap.put("errorMsg", "没有找到相对应的文件");
			return paramMap;
		}
		return paramMap;
	}

	/**
	 * 
	 */
	public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		// ResultData<Object> resultData = new ResultData<>();
		// 判断用户是否登录
		/*
		 * User user=(User) session.getAttribute("user"); if (user==null) {
		 * resultData.setCode(40029); resultData.setMsg("用户未登录"); return
		 * resultData; }
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("success", "error");
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			String title = null;// 视频标题
			String introduction = null;// 视频内容
			String fileName = file.getOriginalFilename();// 文件原名称
			String fileType = request.getParameter("fileType");
			logger.info("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1
					? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// 判断文件类型是否为空
				if (fileType.equals("image")) {
					if (!"PNG".equals(type.toUpperCase()) && !"JPG".equals(type.toUpperCase())) {
						logger.info("不是我们想要的文件类型,请按要求重新上传");
						paramMap.put("errMsg", "不是我们想要的文件类型,请按要求重新上传");
						return paramMap;
					}
				}
				if (fileType.equals("video")) {
					title = request.getParameter("title");
					introduction = request.getParameter("introduction");
				}
				// 项目在容器中实际发布运行的根路径
				// String realPath =
				// request.getSession().getServletContext().getRealPath("/");
				String realPath = System.getProperty("user.dir") + "/";
				// 自定义的文件名称
				String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
				// 设置存放图片文件的路径
				path = realPath + /* System.getProperty("file.separator")+ */trueFileName;
				logger.info("存放图片文件的路径:" + path);
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				logger.info("文件成功上传到指定目录下");
				MediaDto material = materialService.materialInsert(path, fileType, title,
						introduction);
				paramMap.put("material", material);
				paramMap.put("success", "success");
				return paramMap;
			} else {
				logger.info("文件类型为空");
				paramMap.put("errMsg", "文件类型为空");
				return paramMap;
			}
		} else {
			logger.info("没有找到相对应的文件");
			paramMap.put("errMsg", "没有找到相对应的文件");
			return paramMap;
		}
	}

}