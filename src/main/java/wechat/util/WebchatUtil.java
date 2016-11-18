package wechat.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import wechat.dto.WechatUserInfoDto;
import wechat.para.WechatUserInfoQueryPara;

import java.util.HashMap;
import java.util.Map;

public class WebchatUtil {
    private static Logger logger = Logger.getLogger(WebchatUtil.class);

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
            // https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
            jsontoken = HttpUtils.HttpGet("https://api.weixin.qq.com/cgi-bin", "/token", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(jsontoken);
        if (jsonObject.has("access_token")) {
            accessToken = jsonObject.getString("access_token");
        }
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
        accessToken = tokenInit(appid, secret);
        return accessToken;
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param para
     * @return
     */
    public static WechatUserInfoDto userInfo(WechatUserInfoQueryPara para) {
        WechatUserInfoDto dto = new WechatUserInfoDto();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("access_token", para.getAccess_token());
        param.put("openid", para.getOpenid());
        String lang = para.getLang();
        if (StringUtils.isNotBlank(lang)) {
            param.put("lang", lang.trim());
        }
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/user";
            String method = "info";
            String jsonResult = HttpUtils.HttpGet(url, method, param);
            if (StringUtils.isNotBlank(jsonResult)) {
                JSONObject jsonObject = JSONObject.fromObject(jsonResult);
                if (jsonObject.has("subscribe")) {
                    dto.setSubscribe(jsonObject.getInt("subscribe"));
                }
                if (jsonObject.has("openid")) {
                    dto.setOpenid(jsonObject.getString("openid"));
                }
                if (jsonObject.has("nickname")) {
                    dto.setNickname(jsonObject.getString("nickname"));
                }
                if (jsonObject.has("sex")) {
                    dto.setSex(jsonObject.getInt("sex"));
                }
                if (jsonObject.has("city")) {
                    dto.setCity(jsonObject.getString("city"));
                }
                if (jsonObject.has("country")) {
                    dto.setCountry(jsonObject.getString("country"));
                }
                if (jsonObject.has("province")) {
                    dto.setProvince(jsonObject.getString("province"));
                }
                if (jsonObject.has("headimgurl")) {
                    dto.setHeadimgurl(jsonObject.getString("headimgurl"));
                }
                if (jsonObject.has("subscribe_time")) {
                    dto.setSubscribe_time(jsonObject.get("subscribe_time") + "");
                }
                if (jsonObject.has("unionid")) {
                    dto.setUnionid(jsonObject.getString("unionid"));
                }
                if (jsonObject.has("remark")) {
                    dto.setRemark(jsonObject.getString("remark"));
                }
                if (jsonObject.has("groupid")) {
                    dto.setGroupid(jsonObject.getInt("groupid"));
                }
            }
        } catch (Exception e) {

        }
        return dto;
    }
}
