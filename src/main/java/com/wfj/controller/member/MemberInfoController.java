package com.wfj.controller.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfj.entity.MemberInfo;
import com.wfj.service.intf.MemberInfoService;
import com.wfj.util.StringUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
@Controller
@RequestMapping(value = {"/memberInfo"})
public class MemberInfoController {

    private static Logger logger = LoggerFactory.getLogger(MemberInfoController.class);

    @Autowired
    private MemberInfoService memberInfoService;

    /**
     * 注册会员
     *
     * @param memberInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerMember", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerMember(String memberCode, String storeCode, String password, Integer subscribe, String openid,
                                 String nickname, Integer sex, String city, String country, String province, String language,
                                 String headimgurl, String subscribeTime, String unionid, String remark, Integer groupid,
                                 String idCard, String email, String mobile) {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberInfo memberInfo = new MemberInfo();
        if (StringUtils.isNotEmpty(memberCode)) {
            memberInfo.setMemberCode(memberCode.trim());
        }
        if (StringUtils.isNotEmpty(storeCode)) {
            memberInfo.setStoreCode(storeCode.trim());
        }
        if (StringUtils.isNotEmpty(mobile)) {
            memberInfo.setMobile(mobile.trim());
            memberInfo.setPassword(mobile.trim().substring(5, 6));//密码默认手机后六位
        }
        if (StringUtils.isNotEmpty(openid)) {
            memberInfo.setOpenid(openid.trim());
        }
        if (StringUtils.isNotEmpty(unionid)) {
            memberInfo.setUnionid(unionid.trim());
        }
        memberInfo.setSubscribe(subscribe);
        memberInfo.setNickname(nickname);
        memberInfo.setSex(sex);
        memberInfo.setCity(city);
        memberInfo.setCountry(country);
        memberInfo.setProvince(province);
        memberInfo.setLanguage(language);
        memberInfo.setHeadimgurl(headimgurl);
        memberInfo.setSubscribeTime(subscribeTime);
        memberInfo.setRemark(remark);
        memberInfo.setGroupid(groupid);
        memberInfo.setIdCard(idCard);
        memberInfo.setEmail(email);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            logger.info("注册参数：" + JSONObject.fromObject(map).toString());
            Map<String, Object> returnMap = memberInfoService.registerMember(memberInfo);
            logger.info("调用注册返回：" + returnMap.toString());
            if ("true".equals(returnMap.get("success") + "")) {
//                resultMap.put("cid", dto.getObject());
                resultMap.put("msg", returnMap.get("desc") + "");
                resultMap.put("success", true);
            } else {
                resultMap.put("msg", returnMap.get("desc") + "");
                resultMap.put("success", false);
            }
        } catch (Exception e) {
            resultMap.put("msg", "系统错误");
            resultMap.put("success", false);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(resultMap);
    }


}
