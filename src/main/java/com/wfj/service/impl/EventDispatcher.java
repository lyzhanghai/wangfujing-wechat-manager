package com.wfj.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfj.entity.MsgReply;
import com.wfj.service.intf.MsgDispatcherService;
import com.wfj.util.MessageUtil;

@Service
public class EventDispatcher {
	private static Logger logger = Logger.getLogger(EventDispatcher.class);

	@Autowired
	MsgDispatcherService msgDispatcher;

	public String processEvent(Map<String, String> map) {
		String para = null;
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			// msgDispatcher.msgReplyText(openid, mpid, msg);
			logger.info("-------------------" + map);
			logger.info("==============这是关注事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			logger.info("==============这是取消关注事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { // 扫描二维码事件
			logger.info("==============这是扫描二维码事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 位置上报事件
			logger.info("==============这是位置上报事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			logger.info("==============这是自定义菜单点击事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { // 自定义菜单
																	// View事件
			logger.info("==============这是自定义菜单 View 事件！");
		}
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			logger.info("==============这是自定义菜单 CLICK 事件！");
			String openid = map.get("FromUserName"); // 用户 openid
			String mpid = map.get("ToUserName"); // 公众号原始 ID
			String msgKey = map.get("EventKey");
			MsgReply msgRelpy = new MsgReply();
			msgRelpy.setMsgKey(msgKey);
			para = msgDispatcher.msgReplyText(openid, mpid, msgRelpy);
		}

		return para;
	}
}