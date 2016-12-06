package com.wfj.controller.wechat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.dto.MaterialDto;
import com.wfj.service.intf.MaterialService;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {
	@Autowired
	private MaterialService materialService;

	@ResponseBody
	@RequestMapping(value = "/getMaterial", method = RequestMethod.POST)
	public Map<String, Object> getMaterial(@RequestBody Map<String, Object> paramMap) {
		// String maString =
		// "{\"item\":[{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"},{\"media_id\":\"F0q3LA9b9Ix7qoW5Rq-5J1F2Qu-bLalKjYzr0UhUeiQ\",\"name\":\"148092530738710020962001_01.jpg\",\"update_time\":1480925310,\"url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGWuAsIENRhRsEApVTxL6R1p9zaicXNtAxc8Licibiapf1z5DTgttRrJQzNR7Pa1rMVVXmxcxMYy4r7TZA/0?wx_fmt=jpeg\"}],\"total_count\":1,\"item_count\":1}";
		// MaterialDto materialList = JsonUtil.getJacksonDTO(maString,
		// MaterialDto.class);
		String offset = (String) paramMap.get("offset");
		String count = (String) paramMap.get("count");
		String eventType = (String) paramMap.get("eventType");
		MaterialDto materialList = materialService.getMaterialList(Integer.parseInt(offset),
				Integer.parseInt(count), eventType);
		if (materialList != null && materialList.getItem() != null
				&& materialList.getItem().size() > 0) {
			paramMap.put("materialList", materialList.getItem());
			paramMap.put("success", "true");
		} else {
			paramMap.put("success", "false");
		}
		return paramMap;
	}
}
