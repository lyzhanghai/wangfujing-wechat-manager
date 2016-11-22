package com.wfj.controller.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfj.entity.AppAccountInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
@Controller
@RequestMapping(value = {"/appAccountInfo"})
public class AppAccountInfoController {

    private static Logger logger = LoggerFactory.getLogger(AppAccountInfoController.class);

    @Autowired
    private AppAccountInfoMapper appAccountInfoMapper;

    /**
     * 查询
     *
     * @param appid
     * @param appsecret
     * @param storecode
     * @return
     */
    @RequestMapping(value = {"/findAppAccountInfoByPara"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String findAppAccountInfoByPara(String appid, String appsecret, String storecode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(appid)) {
            paraMap.put("appid", appid.trim());
        }
        if (StringUtils.isNotEmpty(appsecret)) {
            paraMap.put("appsecret", appsecret.trim());
        }
        if (StringUtils.isNotEmpty(storecode)) {
            paraMap.put("storecode", storecode.trim());
        }
        paraMap.put("delFlag", 0);
        List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paraMap);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (appAccountInfoList != null && appAccountInfoList.size() > 0) {
            resultMap.put("success", true);
            resultMap.put("list", appAccountInfoList);
        } else {
            resultMap.put("success", false);
            resultMap.put("list", "");
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(resultMap);
    }


}
