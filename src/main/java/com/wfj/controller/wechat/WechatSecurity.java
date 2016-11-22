package com.wfj.controller.wechat;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.dto.EventDispatcher;
import com.wfj.dto.MsgDispatcher;
import com.wfj.dto.WeiXinDto;
import com.wfj.util.MessageUtil;
import com.wfj.util.WechatUtil;

@Controller
@RequestMapping(value = "/wechat")
public class WechatSecurity {
	private static Logger logger = Logger.getLogger(WechatSecurity.class);

	@Autowired
	private WechatUtil tokenUtil;

	// http://117.121.99.11/wechat-web/wechat/security.htm
	@ResponseBody
	@RequestMapping(value = "/security", method = { RequestMethod.POST, RequestMethod.GET })
	public String data(WeiXinDto dto) {
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

	@RequestMapping(value = "security", method = RequestMethod.POST)
	// post 方法用于接收微信服务端消息
	public void DoPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("这是 post 方法！");
		String access_token = tokenUtil.getAccessToken("wx871d0104ae72e615",
				"00e66c2772af76181745b6f5d92b5801");
		String processMessage = null;
		try {
			Map<String, String> map = MessageUtil.parseXml(request);
			logger.info(map.toString());
			String msgtype = map.get("MsgType");
			map.put("access_token", access_token);
			logger.info(msgtype);
			if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
				logger.info("进入事件处理");
				processMessage = EventDispatcher.processEvent(map); // 进入事件处理
			} else {
				logger.info("进入消息处理");
				processMessage = MsgDispatcher.processMessage(map); // 进入消息处理
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		logger.info("processMessage" + processMessage);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(processMessage);
		out.close();
	}

}
