package com.wfj.controller.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfj.dto.MemberInfoReturnDto;
import com.wfj.entity.MemberInfo;
import com.wfj.message.req.StoreInfoDto;
import com.wfj.service.intf.MemberCardService;
import com.wfj.service.intf.MemberInfoService;
import com.wfj.util.PropertiesUtils;
import com.wfj.util.StringUtils;
import com.wfj.util.WechatUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
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
    private WechatUtil wechatUtil;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemberCardService memberCardService;

    /**
     * 注册会员
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerMember", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerMember(String storeCode, String openId, String unionId, String idCard,
                                 String email, String mobile, String appId, String secret) {
        MemberInfo memberInfo = new MemberInfo();
        if (StringUtils.isNotEmpty(storeCode)) {
            memberInfo.setStoreCode(storeCode.trim());
        }
        if (StringUtils.isNotEmpty(mobile)) {
            memberInfo.setMobile(mobile.trim());
            memberInfo.setPassword(mobile.trim().substring(5, mobile.length()));//密码默认手机后六位
        }
        if (StringUtils.isNotEmpty(openId)) {
            memberInfo.setOpenid(openId.trim());
        }
        if (StringUtils.isNotEmpty(unionId)) {
            memberInfo.setUnionid(unionId.trim());
        }
        try {
            com.wfj.dto.MemberInfo openid_userinfo = wechatUtil.Openid_userinfo(openId, appId, secret);
            memberInfo.setSubscribe(openid_userinfo.getSubscribe());
            memberInfo.setNickname(openid_userinfo.getNickname());
            memberInfo.setSex(openid_userinfo.getSex());
            memberInfo.setCity(openid_userinfo.getCity());
            memberInfo.setCountry(openid_userinfo.getCountry());
            memberInfo.setProvince(openid_userinfo.getProvince());
            memberInfo.setLanguage(openid_userinfo.getLanguage());
            memberInfo.setHeadimgurl(openid_userinfo.getHeadimgurl());
            memberInfo.setSubscribeTime(openid_userinfo.getSubscribe_time());
            memberInfo.setRemark(openid_userinfo.getRemark());
            memberInfo.setGroupid(openid_userinfo.getGroupid());
            String openid_userinfoUnionid = openid_userinfo.getUnionid();
            if (StringUtils.isNotEmpty(openid_userinfoUnionid)) {
                memberInfo.setUnionid(openid_userinfoUnionid);
            }
            String openid_userinfoOpenid = openid_userinfo.getOpenid();
            if (StringUtils.isNotEmpty(openid_userinfoOpenid)) {
                memberInfo.setOpenid(openid_userinfoOpenid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        memberInfo.setEmail(email);
        memberInfo.setIdCard(idCard);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            logger.info("注册参数：" + JSONObject.fromObject(memberInfo).toString());
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

    /**
     * 绑定卡
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bindMemberCard", method = {RequestMethod.GET, RequestMethod.POST})
    public String bindMemberCard(String storeCode, String cardCode, String mobile, String openId, String appId,
                                 String secret, String unionId, String password, String cardType, String cardLevel) {
        logger.info("start com.wfj.controller.member.MemberInfoController.bindMemberCard()");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(storeCode)) {
            paraMap.put("storeCode", storeCode);
        }
        if (StringUtils.isNotEmpty(cardCode)) {
            paraMap.put("cardCode", cardCode);
        }
        if (StringUtils.isNotEmpty(mobile)) {
            paraMap.put("mobile", mobile);
        }
        if (StringUtils.isNotEmpty(openId)) {
            paraMap.put("openid", openId);
        }
        if (StringUtils.isNotEmpty(appId)) {
            paraMap.put("appid", appId);
        }
        if (StringUtils.isNotEmpty(secret)) {
            paraMap.put("secret", secret);
        }
        if (StringUtils.isNotEmpty(unionId)) {
            paraMap.put("unionid", unionId);
        }
        if (StringUtils.isNotEmpty(password)) {
            paraMap.put("password", password);
        }
        if (StringUtils.isNotEmpty(cardType)) {
            paraMap.put("cardType", cardType);
        }
        if (StringUtils.isNotEmpty(cardLevel)) {
            paraMap.put("cardLevel", cardLevel);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            logger.info("绑定会员卡,请求数据：" + paraMap.toString());
            Map<String, Object> returnMap = memberCardService.bindMemberCard(paraMap);
            logger.info("绑定会员卡,响应数据：" + returnMap.toString());
            if ("true".equals(returnMap.get("success") + "")) {
//                resultMap.put("cid", dto.getObject());
                resultMap.put("msg", returnMap.get("desc") + "");
                resultMap.put("success", true);
            } else {
                resultMap.put("msg", returnMap.get("desc") + "");
                resultMap.put("success", false);
            }
        } catch (Exception e) {
            logger.error("绑定会员卡异常", e);
            resultMap.put("msg", "系统错误");
            resultMap.put("success", false);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(resultMap);
    }

    /**
     * 返回首页时去取地址(跳转页面、转向注册页面或者我的会员页面)
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBackIndexUrl", method = {RequestMethod.GET, RequestMethod.POST})
    public String getBackIndexUrl(String state, String openId) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(state)) {
            paramMap.put("state", state);
        }
        if (StringUtils.isNotEmpty(openId)) {
            paramMap.put("openid", openId);
        }
        String json = "";
        try {
            logger.info("访问参数：" + JSONObject.fromObject(paramMap).toString());
            json = memberIndex(paramMap);
            logger.info("返回参数：" + json);
            resultMap.put("data", json);
            resultMap.put("success", true);
        } catch (Exception e) {
            resultMap.put("msg", "系统错误");
            resultMap.put("success", false);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(resultMap);
    }

    /**
     * 跳转页面
     *
     * @param
     * @return
     */
    private String memberIndex(Map<String, Object> paramMap) {
        long starTime = System.currentTimeMillis();
        String state = (String) paramMap.get("state");
        String openid = (String) paramMap.get("openid");
        String url = "";
        try {
            // 1 通过门店接口获取appID,appSecret
            StoreInfoDto storeInfo = wechatUtil.getStoreInfo(state);
            System.out.println("storeInfo ================ " + storeInfo);
            // 2 通过appID,appSecret获取access_token
            String accessToken = wechatUtil.getAccessToken(storeInfo.getAppId(), storeInfo.getSecret());
            System.out.println("accessToken ================ " + accessToken);
            // 4 通过access_token,openID获取用户信息
            com.wfj.dto.MemberInfo memberInfo = wechatUtil.Openid_userinfo(openid, storeInfo.getAppId(), storeInfo.getSecret());
            System.out.println("memberInfo ================ " + memberInfo);
            String name = wechatUtil.getURLEncoder(memberInfo.getNickname());
            String para = "&appId=" + storeInfo.getAppId() + "&openId=" + openid + "&headimgurl="
                    + memberInfo.getHeadimgurl() + "&nickname=" + name + "&registType="
                    + storeInfo.getIsChannel() + "&unionId=" + memberInfo.getUnionid() + "&storeCode="
                    + storeInfo.getStoreCode();
            // Map<String, String> paramMap_1 = new HashMap<String, String>();
            // paramMap_1.put("appid", storeInfo.getAppId());
            // paramMap_1.put("openid", openid);

            String sendGet = getMemberInfo(storeInfo.getAppId(), openid, storeInfo.getStoreCode());// HttpUtil.sendGet(getMemberInfo,
            // paramMap_1);

            // String sendGet = getMemberInfo(storeInfo.getAppId(), openid);
            url = sendGet + para;
            long endTime = System.currentTimeMillis();
            System.out.println("------------------------------------" + (endTime - starTime) + "getMemberInfo");
            System.out.println("url ================ " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 获取会员用户及会员卡信息(跳转页面)
     *
     * @param
     * @return
     * @throws Exception
     */
    private String getMemberInfo(String appid, String openid, String storeCode) throws Exception {
        logger.info("start com.wfj.controller.member.MemberInfoController.getMemberInfo(),para:{appid:" + appid + ",openid:" + openid + "}");
        String url = "";
        String para = "";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("appid", appid);
        paraMap.put("openid", openid);
        paraMap.put("storeCode", storeCode);
        Map<String, Object> returnMap = memberInfoService.getMemberInfo(paraMap);
        if ("0".equals(returnMap.get("code") + "")) {// 未注册、未实体绑卡 跳转至头像首页
            logger.info("com.wfj.controller.member.MemberInfoController.getMemberInfo:未注册、未实体绑卡");
            url = PropertiesUtils.findPropertiesKey("myMemberInfoInit");
        } else if ("1".equals(returnMap.get("code") + "")) {
            MemberInfoReturnDto memberInfoReturnDto = new MemberInfoReturnDto();
            BeanUtils.copyProperties(memberInfoReturnDto, returnMap.get("obj"));

            String mobile = memberInfoReturnDto.getMobile();

            para = "cardNo=" + memberInfoReturnDto.getCardCode() + "&custType=" + memberInfoReturnDto.getCardLevel()
                    + "&qrcode=&mobile=" + mobile;

            Integer cardType = memberInfoReturnDto.getCardType();
            if (cardType != 1) {// 是否存在有实体卡,无实体卡
                url = PropertiesUtils.findPropertiesKey("myMemberInfo");
                para = para + "&entityCard=0&updatePWD=0";
            } else if (cardType == 1) {// 有实体卡
                url = PropertiesUtils.findPropertiesKey("myMemberInfo");
                if (StringUtils.isNotEmpty(mobile)) {//有手机号
                    para = para + "&unBund=0&updatePWD=0";
                } else {//没有手机号
                    para = para + "&unBund=0&boundPhone=0";
                }
            }
        }
        logger.info("end com.wfj.controller.member.MemberInfoController.getMemberInfo(),return:" + url + para);
        return url + para;
    }

}
