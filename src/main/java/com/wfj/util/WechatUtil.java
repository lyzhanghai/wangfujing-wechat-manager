package com.wfj.util;

import com.wfj.dto.AccessTokenDto;
import com.wfj.dto.MemberInfo;
import com.wfj.entity.AppAccountInfo;
import com.wfj.message.req.StoreInfoDto;
import com.wfj.service.impl.AppAccountInfoServiceImpl;
import com.wfj.service.intf.IAppAccountInfoService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WechatUtil {
    private Logger logger = Logger.getLogger(WechatUtil.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取access_token
     *
     * @param appid
     * @param secret
     * @return String
     * @Methods Name tokenInit
     * @Create In 2016年9月26日 By kongqf
     */
    public String tokenInit(String appid, String secret) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", appid);
        params.put("secret", secret);
        String jsontoken = null;
        String accessToken = null;
        try {
            jsontoken = com.wfj.util.HttpUtil.sendGet(PropertiesUtils.findPropertiesKey("accessTokenUrl"), params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.fromObject(jsontoken);
        if (jsonObject.has("access_token")) {
            accessToken = jsonObject.getString("access_token");
        }

        //boolean flag = redisUtil.setKey(appid, accessToken, 7000);
        //if (!flag) {
        //    logger.error("tokenInit redis save:" + flag);
        //}
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                + "token 为==============================" + accessToken);
        return accessToken;
    }

    /**
     * 获取access_token
     *
     * @param appid
     * @param secret
     * @return String
     * @Methods Name getAccessToken
     * @Create In 2016年9月26日 By kongqf
     */
    public String getAccessToken(String appid, String secret) {
        String accessToken = null;
        accessToken = redisUtil.getKey(appid, "0000");
        if ("0000".equals(accessToken)) {
            accessToken = tokenInit(appid, secret);
        }
        return accessToken;
    }

    /**
     * 获取微信用户信息
     *
     * @param openid
     * @return
     * @throws Exception MemberInfo
     * @Methods Name Openid_userinfo
     * @Create In 2016年9月26日 By kongqf
     */
    public MemberInfo Openid_userinfo(String openid, String appid, String
            secret) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", getAccessToken(appid, secret));
        params.put("openid", openid);
        params.put("lang", "zh_CN");

        logger.info(params);
        String subscribers = com.wfj.util.HttpUtil.sendGet(PropertiesUtils.findPropertiesKey("openidUserinfoUrl"), params);
        logger.info(subscribers);
        MemberInfo memberInfo = new MemberInfo(); // memberInfo = JsonUtil.getDTO(subscribers,MemberInfo.class);
        if (com.wfj.util.StringUtils.isNotEmpty(subscribers)) {
            JSONObject jsonObject = JSONObject.fromObject(subscribers);
            try {
                if (jsonObject.has("unionid")) {
                    memberInfo.setUnionid(jsonObject.getString("unionid"));
                }
                memberInfo.setNickname(jsonObject.getString("nickname"));
                memberInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
                memberInfo.setOpenid(jsonObject.getString("openid"));
                memberInfo.setSubscribe(jsonObject.getInt("subscribe"));
                memberInfo.setSex(jsonObject.getInt("sex"));
                memberInfo.setCity(jsonObject.getString("city"));
                memberInfo.setCountry(jsonObject.getString("country"));
                memberInfo.setProvince(jsonObject.getString("province"));
                memberInfo.setLanguage(jsonObject.getString("language"));
                memberInfo.setSubscribe_time(jsonObject.get("subscribe_time") + "");
                memberInfo.setRemark(jsonObject.getString("remark"));
                memberInfo.setGroupid(jsonObject.getInt("groupid"));
            } catch (Exception e) {
                if (0 == memberInfo.getSubscribe()) {
                    logger.error("用户" + memberInfo.getOpenid() + "已取消关注");
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    logger.error("获取用户信息失败 errorCode:" + errorCode + " errmg:" + errorMsg);
                }
            }
        }
        return memberInfo;
    }

    /**
     * 根据门店标识获取公主号信息
     *
     * @param appid
     * @return StoreInfoDto
     * @Methods Name getStoreInfo
     * @Create In 2016年9月26日 By kongqf
     */
    public StoreInfoDto getStoreInfo(String appid) {
        String storeInfoStr = null;
        StoreInfoDto storeInfoDto = new StoreInfoDto();
        storeInfoStr = redisUtil.getKey("storeInfoDto" + appid, "0000");
        if ("0000".equals(storeInfoStr)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appid", appid);
            IAppAccountInfoService appAccountInfoService = new AppAccountInfoServiceImpl();

            List<AppAccountInfo> appAccountInfos = appAccountInfoService.queryAppAccount(map);

            if (appAccountInfos != null && appAccountInfos.size() > 0) {
                AppAccountInfo model = appAccountInfos.get(0);
                storeInfoDto.setStoreCode(model.getStorecode());
                //storeInfoDto.setStoceName(object.getString("organizationName"));
                storeInfoDto.setAppId(model.getAppid());
                storeInfoDto.setSecret(model.getAppsecret());

                redisUtil.setIsOK("storeInfoDto" + appid, JsonUtil.getJSONString(storeInfoDto));
            }

        } else {
            storeInfoDto = JsonUtil.getDTO(storeInfoStr, StoreInfoDto.class);
        }
        return storeInfoDto;
    }

    /**
     * 获取openid
     *
     * @param appid
     * @param secret
     * @param code
     * @return AccessTokenDto
     * @Methods Name getOpenId
     * @Create In 2016年9月26日 By kongqf
     */

    public AccessTokenDto getOpenId(String appid, String secret, String code) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("secret", secret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        try {
            String openIdInfo = com.wfj.util.HttpUtil.sendGet(PropertiesUtils.findPropertiesKey("openidUrl"), params);
            accessTokenDto = com.wfj.util.JsonUtil.getDTO(openIdInfo, AccessTokenDto.class);
        } catch (Exception e) {
        }

        return accessTokenDto;
    }

    /**
     * 获取菜单
     *
     * @param appid
     * @param secret
     * @return
     */
    public String getMenus(String appid, String secret) {
        String token = getAccessToken(appid, secret);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", token);
        String menus = "";
        try {
            menus = HttpUtil.sendGet(PropertiesUtils.findPropertiesKey("menuget"), params);
        } catch (Exception e) {
            logger.error(e);
        }
        return menus;

    }

    /**
     * 创建菜单
     *
     * @param appid
     * @param secret
     * @param menus
     * @return
     */
    public String createMenus(String appid, String secret, String menus) {
        String token = getAccessToken(appid, secret);
        HashMap<String, String> params = new HashMap<String, String>();
        String result = "";
        try {
            result = HttpUtil.sendPostBuffer(PropertiesUtils.findPropertiesKey("menucreate") + token, menus);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }


    /**
     * URL 编码
     *
     * @param str
     * @return String
     * @Methods Name getURLEncoder
     * @Create In 2016年9月26日 By kongqf
     */
    public String getURLEncoder(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.info("URLEncoder:" + str + e);
        }
        return str;
    }

    /**
     * URL解码
     *
     * @param str
     * @return String
     * @Methods Name getURLDecoder
     * @Create In 2016年9月26日 By kongqf
     */
    public String getURLDecoder(String str) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.info("URLDecoder:" + str + e);
        }
        return str;
    }
}
