package com.wfj.controller.wechat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wfj.controller.index.BaseController;
import com.wfj.dto.ErrorDto;
import com.wfj.dto.MaterialDto;
import com.wfj.dto.MediaDto;
import com.wfj.service.intf.MaterialService;
import com.wfj.util.Common;
import com.wfj.util.JsonUtil;

@Controller
@RequestMapping(value = "/material")
public class MaterialController extends BaseController {
	@Autowired
	private MaterialService materialService;

	@Autowired
	private UploadController upload;

	@ResponseBody
	@RequestMapping(value = "/getMaterial", method = RequestMethod.POST) // 根据类型获取素材列表
	public Map<String, Object> getMaterial(@RequestBody Map<String, Object> paramMap) {
		String maString = "{\"item\":[{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"}],\"total_count\":1,\"item_count\":1}";
		MaterialDto materialList = JsonUtil.getJacksonDTO(maString, MaterialDto.class);
		// String offset = (String) paramMap.get("offset");
		// String count = (String) paramMap.get("count");
		// String eventType = (String) paramMap.get("eventType");
		// MaterialDto materialList =
		// materialService.getMaterialList(Integer.parseInt(offset),
		// Integer.parseInt(count), eventType);
		if (materialList != null && materialList.getItem() != null
				&& materialList.getItem().size() > 0) {
			paramMap.put("materialList", materialList.getItem());
			paramMap.put("success", "true");
		} else {
			paramMap.put("success", "false");
		}
		return paramMap;
	}

	@RequestMapping("list") // 素材管理初始化列表
	public String listUI(Model model) throws Exception {
		// MaterialDto materialList = materialService.getMaterialList(0, 10,
		// "image");
		String material = "{\"item\":[{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J9ftPMRWo1R6CaE8qHC73tI\",\"name\":\"148100607626410019617001_02.jpg\",\"update_time\":1481006079,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGXgA1vnXMjZBmIBQvVJ3NSpdWqeD6MRsdOCcUV6Bx3bXuYhjr9QQlCZfrZRiaxpibrr17ZWCpFhDw0A/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J56HpkvZuDv9tfy9_q7LjI0\",\"name\":\"148099219057610019617001_02.jpg\",\"update_time\":1480992194,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGXgA1vnXMjZBmIBQvVJ3NSpdWqeD6MRsdOCcUV6Bx3bXuYhjr9QQlCZfrZRiaxpibrr17ZWCpFhDw0A/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"}],\"total_count\":3,\"item_count\":3}";
		MaterialDto materialList = JsonUtil.getDTO(material, MaterialDto.class);
		List<MediaDto> item = materialList.getItem();
		for (MediaDto mediaDto : item) {
			String url = mediaDto.getUrl();
			System.out.println(url);
			String[] split = url.split("0\\?");
			mediaDto.setUrl(split[0]);
		}
		// model.addAttribute("res", findByRes());
		model.addAttribute("materialList", item);
		return Common.BACKGROUND_PATH + "/wechat/material/list";
	}

	@RequestMapping("imageList") // 显示图片素材列表
	public String imageList(Model model) throws Exception {
		// MaterialDto materialList = materialService.getMaterialList(0, 10,
		// "image");
		String material = "{\"item\":[{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J9ftPMRWo1R6CaE8qHC73tI\",\"name\":\"148100607626410019617001_02.jpg\",\"update_time\":1481006079,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGXgA1vnXMjZBmIBQvVJ3NSpdWqeD6MRsdOCcUV6Bx3bXuYhjr9QQlCZfrZRiaxpibrr17ZWCpFhDw0A/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J56HpkvZuDv9tfy9_q7LjI0\",\"name\":\"148099219057610019617001_02.jpg\",\"update_time\":1480992194,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGXgA1vnXMjZBmIBQvVJ3NSpdWqeD6MRsdOCcUV6Bx3bXuYhjr9QQlCZfrZRiaxpibrr17ZWCpFhDw0A/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"}],\"total_count\":3,\"item_count\":3}";
		MaterialDto materialList = JsonUtil.getDTO(material, MaterialDto.class);
		List<MediaDto> item = materialList.getItem();
		for (MediaDto mediaDto : item) {
			String url = mediaDto.getUrl();
			System.out.println(url);
			String[] split = url.split("0\\?");
			mediaDto.setUrl(split[0]);
		}
		// model.addAttribute("res", findByRes());
		model.addAttribute("materialList", item);
		return Common.BACKGROUND_PATH + "/wechat/material/imageList";
	}

	@RequestMapping("imageAdd") // 弹出图片素材添加页面
	public String imageAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/imageAdd";
	}

	@RequestMapping("newsAdd") // 弹出图文添加页面
	public String newsAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/newsAdd";
	}

	@ResponseBody
	@RequestMapping("imageAdd/upload") // 图片素材上传
	public String upload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = upload.fileUpload(file, request, response);
			String flag = (String) paramMap.get("success");
			if (flag.equals("success")) {
				return "success";
			} else {
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		// return "success";
	}

	@RequestMapping("del") // 根据mid删除素材
	public String del(@RequestBody Map<String, Object> paramMap) {
		String mediaId = (String) paramMap.get("mediaId");
		String materialDelete = materialService.materialDelete(mediaId);
		ErrorDto error = JsonUtil.getDTO(materialDelete, ErrorDto.class);
		if (error.getErrcode().equals("0")) {
			return "success";
		} else {
			return "error";
		}
	}

}
