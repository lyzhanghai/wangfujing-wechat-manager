import java.util.HashMap;
import java.util.Map;

import com.wfj.dto.MaterialDto;
import com.wfj.util.HttpUtils;
import com.wfj.util.JsonUtil;

import net.sf.json.JSONObject;

public class WechatUtilTest {

	public static void main(String[] args) {
		WechatUtilTest wechat = new WechatUtilTest();
		wechat.getMaterialList();
	}

	/**
	 * 获取access_token
	 */
	public String tokenInit() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", "wx871d0104ae72e615");
		params.put("secret", "00e66c2772af76181745b6f5d92b5801");
		String jsontoken = null;
		String accessToken = null;
		try {
			jsontoken = com.wfj.util.HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token",
					params);
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
	 * 获取素材list
	 */
	public void getMaterialList() {
		String getMaterialPath = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="
				+ tokenInit();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("offset", 0);
		paramMap.put("count", 100);
		paramMap.put("type", "image");
		String reString = HttpUtils.doPost(getMaterialPath, JsonUtil.getJSONString(paramMap));
		MaterialDto material = JsonUtil.getJacksonDTO(reString, MaterialDto.class);
		System.out.println(material);
	}
}
