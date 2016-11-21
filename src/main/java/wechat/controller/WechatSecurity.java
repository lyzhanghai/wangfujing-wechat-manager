package wechat.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wechat.controller.support.WeiXinDto;

@Controller
@RequestMapping(value = "wechat")
public class WechatSecurity {
	private static Logger logger = Logger.getLogger(WechatSecurity.class);

	// http://117.121.99.11/wechat-web/wechat/security.htm
	@ResponseBody
	@RequestMapping(value = "/security", produces = "application/octet-stream;charset=utf-8", method = {
			RequestMethod.GET })
	public String DoGet(WeiXinDto dto) {
		logger.info(dto.getAppid());
		logger.info(dto.getAppSecret());
		List<String> list = new ArrayList<String>();
		list.add(dto.getTimestamp());
		list.add(dto.getNonce());
		list.add("qwertyuiop123");
		Collections.sort(list);
		if (DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2))
				.equals(dto.getSignature())) {
			return dto.getEchostr();
		}
		return null;
	}
}
