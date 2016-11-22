package com.wfj.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class WechatUtil {
	private Logger logger = Logger.getLogger(WechatUtil.class);

	// @Autowired
	// private RedisUtil redisUtil;

	/**
	 * 获取access_token
	 *
	 * @Methods Name tokenInit
	 * @Create In 2016年9月26日 By kongqf
	 * @param appid
	 * @param secret
	 * @return String
	 */
	public String tokenInit(String appid, String secret) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", appid);
		params.put("secret", secret);
		String jsontoken = null;
		String accessToken = null;
		try {
			// jsontoken =
			// com.wfj.util.HttpUtil.sendGet(com.wfj.util.Constants.ACCESSTOKENURL,
			// params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = JSONObject.fromObject(jsontoken);
		if (jsonObject.has("access_token")) {
			accessToken = jsonObject.getString("access_token");
		}

		// boolean flag = redisUtil.setKey(appid, accessToken, 7000);
		// if (!flag) {
		// logger.error("tokenInit redis save:" + flag);
		// }
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "token 为==============================" + accessToken);
		return accessToken;
	}

	/**
	 * 获取access_token
	 *
	 * @Methods Name getAccessToken
	 * @Create In 2016年9月26日 By kongqf
	 * @param appid
	 * @param secret
	 * @return String
	 */
	public String getAccessToken(String appid, String secret) {
		String accessToken = null;
		// accessToken = redisUtil.getKey(appid, "0000");
		if ("0000".equals(accessToken)) {
			accessToken = tokenInit(appid, secret);
		}
		return accessToken;
	}

	/**
	 * 获取微信用户信息
	 *
	 * @Methods Name Openid_userinfo
	 * @Create In 2016年9月26日 By kongqf
	 * @param openid
	 * @return
	 * @throws Exception
	 *             MemberInfo
	 */
	/*
	 * public MemberInfo Openid_userinfo(String openid, String appid, String
	 * secret) throws Exception { HashMap<String, String> params = new
	 * HashMap<String, String>(); params.put("access_token",
	 * getAccessToken(appid, secret)); params.put("openid", openid);
	 * params.put("lang", "zh_CN");
	 * 
	 * logger.info(params); String subscribers =
	 * com.wfj.util.HttpUtil.sendGet(com.wfj.util.Constants.OPENIDUSERINFOURL,
	 * params); logger.info(subscribers); MemberInfo memberInfo = new
	 * MemberInfo(); // memberInfo = JsonUtil.getDTO(subscribers,
	 * MemberInfo.class); if (com.wfj.util.StringUtils.isNotEmpty(subscribers))
	 * { JSONObject jsonObject = JSONObject.fromObject(subscribers); try { if
	 * (jsonObject.has("unionid")) {
	 * memberInfo.setUnionid(jsonObject.getString("unionid")); }
	 * memberInfo.setNickname(jsonObject.getString("nickname"));
	 * memberInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
	 * memberInfo.setOpenid(jsonObject.getString("openid"));
	 * memberInfo.setSubscribe(jsonObject.getInt("subscribe")); } catch
	 * (Exception e) { if (0 == memberInfo.getSubscribe()) { logger.error("用户" +
	 * memberInfo.getOpenid() + "已取消关注"); } else { int errorCode =
	 * jsonObject.getInt("errcode"); String errorMsg =
	 * jsonObject.getString("errmsg"); logger.error("获取用户信息失败 errorCode:" +
	 * errorCode + " errmg:" + errorMsg); } } } return memberInfo; }
	 */

	/**
	 * 获取openid
	 *
	 * @Methods Name getOpenId
	 * @Create In 2016年9月26日 By kongqf
	 * @param appid
	 * @param secret
	 * @param code
	 * @return AccessTokenDto
	 */
	/*
	 * public AccessTokenDto getOpenId(String appid, String secret, String code)
	 * { AccessTokenDto accessTokenDto = new AccessTokenDto(); HashMap<String,
	 * String> params = new HashMap<String, String>(); params.put("appid",
	 * appid); params.put("secret", secret); params.put("code", code);
	 * params.put("grant_type", "authorization_code"); try { String openIdInfo =
	 * com.wfj.util.HttpUtil.sendGet(com.wfj.util.Constants.GETOPENIDURL,
	 * params); accessTokenDto = com.wfj.util.JsonUtil.getDTO(openIdInfo,
	 * AccessTokenDto.class); } catch (Exception e) { }
	 * 
	 * return accessTokenDto; }
	 */

	/**
	 * BASE64编码
	 *
	 * @Methods Name getEncoder
	 * @Create In 2016年9月26日 By kongqf
	 * @param str
	 * @return String
	 */
	/*
	 * public String getEncoder(String str) { String s = ""; BASE64Encoder
	 * encoder = new BASE64Encoder(); try { s =
	 * encoder.encode(str.getBytes("UTF-8")); } catch
	 * (UnsupportedEncodingException e) { logger.info("encoder:" + str + e); }
	 * return s; }
	 */

	/**
	 * BASE64解码
	 *
	 * @Methods Name getDecoder
	 * @Create In 2016年9月26日 By kongqf
	 * @param str
	 * @return String
	 */
	/*
	 * public String getDecoder(String str) { String s = ""; BASE64Decoder
	 * decoder = new BASE64Decoder(); byte[] bytes; try { bytes =
	 * decoder.decodeBuffer(str); s = new String(bytes, "UTF-8"); } catch
	 * (IOException e) { logger.info("decoder:" + str + e); } return s; }
	 */

	/**
	 * URL 编码
	 *
	 * @Methods Name getURLEncoder
	 * @Create In 2016年9月26日 By kongqf
	 * @param str
	 * @return String
	 */
	/*
	 * public String getURLEncoder(String str) { try { str =
	 * URLEncoder.encode(str, "UTF-8"); } catch (UnsupportedEncodingException e)
	 * { logger.info("URLEncoder:" + str + e); } return str; }
	 */

	/**
	 * URL解码
	 *
	 * @Methods Name getURLDecoder
	 * @Create In 2016年9月26日 By kongqf
	 * @param str
	 * @return String
	 */
	/*
	 * public String getURLDecoder(String str) { try { str =
	 * URLDecoder.decode(str, "UTF-8"); } catch (UnsupportedEncodingException e)
	 * { logger.info("URLDecoder:" + str + e); } return str; }
	 */
}
