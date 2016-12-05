package com.wfj.controller.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfj.entity.StoreInfo;
import com.wfj.mapper.StoreInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-21 0021.
 */
@Controller
@RequestMapping(value = {"/storeInfo"})
public class StoreInfoController {

    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /**
     * 查询门店列表
     *
     * @return
     */
    @RequestMapping(value = {"/findStoreInfoList"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String findStoreInfoList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (storeInfoList != null && storeInfoList.size() > 0) {
            resultMap.put("success", true);
            resultMap.put("list", storeInfoList);
        } else {
            resultMap.put("success", false);
            resultMap.put("list", "");
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = {"/findStoreInfoList2"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> findStoreInfoList2() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (storeInfoList != null && storeInfoList.size() > 0) {
            resultMap.put("success", true);
            resultMap.put("list", storeInfoList);
        } else {
            resultMap.put("success", false);
            resultMap.put("list", "");
        }
        return resultMap;
    }

}
