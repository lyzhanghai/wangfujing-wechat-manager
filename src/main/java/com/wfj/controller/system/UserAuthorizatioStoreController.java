package com.wfj.controller.system;


import com.wfj.controller.index.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author zhaocj 2016-12-07
 */
@Controller
@RequestMapping("/userAuthorizatioStore/")
public class UserAuthorizatioStoreController extends BaseController {

	/**
	 *
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("test")
	@ResponseBody
	public String isExist(String name) {
			return "OK";
	}
	

}