package com.wfj.controller.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wfj.dto.AccessTokenDto;
import com.wfj.dto.MemberInfo;
import com.wfj.util.WechatUtil;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private WechatUtil util;

	@RequestMapping(value = "memberInfo", method = RequestMethod.GET)
	public void memberInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "state", required = true) String state) {
		long starTime = System.currentTimeMillis();
		try {
			// 1 通过门店接口获取appID,appSecret
			// StoreInfoDto storeInfo = util.getStoreInfo(state);
			// System.out.println("storeInfo ================ " + storeInfo);
			String appid = "wx871d0104ae72e615";
			String secret = "00e66c2772af76181745b6f5d92b5801";
			// 2 通过appID,appSecret获取access_token
			String accessToken = util.getAccessToken(appid, secret);
			System.out.println("accessToken ================ " + accessToken);
			System.out.println("code:" + code);
			// 3 通过code或取网页授权access_token(暂时不用)及openID
			AccessTokenDto atkDto = util.getOpenId(appid, secret, code);
			System.out.println("atkDto ================ " + atkDto);
			// 4 通过access_token,openID获取用户信息
			MemberInfo memberInfo = util.Openid_userinfo(atkDto.getOpenid(), appid, secret);
			System.out.println("memberInfo ================ " + memberInfo);
			// String name = util.getURLEncoder(memberInfo.getNickname());
			// String para = "&appId=" + storeInfo.getAppId() + "&openId=" +
			// atkDto.getOpenid()
			// + "&headimgurl=" + memberInfo.getHeadimgurl() + "&nickname=" +
			// name
			// + "&registType=" + storeInfo.getIsChannel() + "&uid=" +
			// memberInfo.getUnionid()
			// + "&storeCode=" + storeInfo.getStoreCode();
			// response.setHeader("Content-type", "text/html;charset=UTF-8");
			// response.setCharacterEncoding("UTF-8");
			//// Map<String, String> paramMap = new HashMap<String, String>();
			//// paramMap.put("appid", storeInfo.getAppId());
			//// paramMap.put("openid", atkDto.getOpenid());
			// String sendGet = getMemberInfo(storeInfo.getAppId(),
			// atkDto.getOpenid());// HttpUtil.sendGet("",
			//// paramMap);
			//// String sendGet = getMemberInfo(storeInfo.getAppId(),
			//// atkDto.getOpenid());
			// long endTime = System.currentTimeMillis();
			// System.out.println("------------------------------------" +
			// (endTime - starTime));
			// response.sendRedirect(sendGet + para);
			// System.out.println("decoder ================ " + sendGet + para);
			// PrintWriter out = response.getWriter();
			// out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
