package com.wfj.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.dto.MaterialDto;
import com.wfj.service.intf.MaterialService;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {
	@Autowired
	private MaterialService materialService;

	@ResponseBody
	@RequestMapping(value = "/getMaterial")
	public void getMaterial(int start, int limit, String type) {
		MaterialDto materialList = materialService.getMaterialList(start, limit, type);
	}
}
