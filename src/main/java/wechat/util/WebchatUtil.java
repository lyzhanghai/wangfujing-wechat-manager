package wechat.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class WebchatUtil {
    private static Logger logger = Logger.getLogger(WebchatUtil.class);

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
     * @Methods Name getAccessToken
     * @Create In 2016年9月26日 By kongqf
     * @param appid
     * @param secret
     * @return String
     */
    public String getAccessToken(String appid, String secret) {
        String accessToken = null;
        accessToken = tokenInit(appid, secret);
        return accessToken;
    }
}
