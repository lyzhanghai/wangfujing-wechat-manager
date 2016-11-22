package com.wfj.controller.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfj.entity.MemberInfo;
import com.wfj.service.intf.MemberInfoService;
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
    public String registerMember(MemberInfo memberInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
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
