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
import com.wfj.service.intf.MaterialService;
import com.wfj.util.Common;

@Controller
@RequestMapping("article")
public class ArticleController {
	private static Logger logger = Logger.getLogger(ArticleController.class);

	@Autowired
	private MaterialService materialService;

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
		Map<String, Object> paramMap = fileUpload(file, request, response);
		return paramMap;
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

	@ResponseBody
	@RequestMapping(value = "/getArticleList", method = RequestMethod.POST)
	public String getArticleList(@RequestBody List<ArticleDto> artList)
			throws IllegalStateException, IOException {
		return null;
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
				String url = materialService.imageInsert(path);
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
			String fileType = request.getParameter("type");
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
					introduction = request.getParameter("title");
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