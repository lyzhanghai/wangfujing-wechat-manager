package com.wfj.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wfj.util.HttpUtils;
import com.wfj.util.MessageUtil;

import net.sf.json.JSONObject;

public class EventDispatcher {
	private static Logger logger = Logger.getLogger(EventDispatcher.class);

	public String processEvent(Map<String, String> map) {
		String para = null;
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
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

			// 1、获取APPID
			String appID = (String) map.get("EventKey");// APPID
			logger.info("EventKey :" + map.get("EventKey"));
			// 2、根据APPID获取密钥AppSecret及门店编码
			String AppSecret = "5fbe0df851429f5b7a7d7f6910f96e0d";
			logger.info("AppSecret : " + AppSecret);
			// 3、获取ACCESS_TOKEM

			String accessToken = "3SQOHRrOXnJvqRlqiBdZljwjGibyrXwgPsHLnZTw9TcLJREeGIwxn2sBD6jghd31MKGAAe28OqqIvim8vu7C7YhF8eXfcm0Ao8z7KsG_8wcrjxvLOz_23udIHR9b896pSZCfAGAMTW";
			// logger.info("accessToken : " + accessToken);
			// 4、根据OPENID+ACCESS_TOKEM获取用户信息
			String openID = (String) map.get("FromUserName");
			Map<String, String> InfoMap = new HashMap<String, String>();
			InfoMap.put("openid", openID);
			InfoMap.put("lang", "zh_CN");
			InfoMap.put("access_token", accessToken);
			// InfoMap.put(
			// "access_token",
			// "xsTZvXbnc26uJaQOE_q7oGwQmWKYLHFmHBChKMJwFuaEf8jGwkd0GYW8jQJvxbWl-mFZUCMOyL7BRCpxIRVKklvKNdJK9x7VCU4xmh-TIY1XvHiMhh5hbaT6o422SJjRWRRaAIABWW");
			String memberJson = null;
			try {
				memberJson = HttpUtils.HttpGet("https://api.weixin.qq.com/cgi-bin/user", "/info",
						InfoMap);
			} catch (Exception e) {
			}
			logger.info("memberJson : " + memberJson);
			// 5、转换为实体类
			// MemberInfo memberInfo = JsonUtil.getDTO(memberJson,
			// MemberInfo.class);

			// 6、跳转至页面（包含用户信息、APPID、门店编码）
			// para = "?appID=" + appID + "&openID=" + openID + "&headimgurl="
			// + memberInfo.getHeadimgurl() + "&nickname=" +
			// memberInfo.getNickname();

			para = "?appID=" + appID + "&openID=" + openID + "&headimgurl="
					+ JSONObject.fromObject(memberJson).getString("headimgurl") + "&nickname="
					+ JSONObject.fromObject(memberJson).getString("nickname");
			// Map<String, Object> paramMap = new HashMap<String, Object>();
			// paramMap.put("appID", appID);
			// paramMap.put("openID", openID);
			// paramMap.put("headimgurl", memberInfo.getHeadimgurl());
			// paramMap.put("nickname", memberInfo.getNickname());
			// HttpUtil.HttpGet("http://10.6.2.49:8080/wechat-web",
			// "myMemberInfoInit.html", paramMap);
		}

		return para;
	}
}