package com.wfj.controller.member;

import com.wfj.entity.StoreInfo;
import com.wfj.mapper.StoreInfoMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = {"/findStoreInfoList"})
    @ResponseBody
    public String findStoreInfoList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
        paramMap.clear();
        paramMap.put("success", true);
        paramMap.put("list", storeInfoList);
        JSONObject jsonObject = JSONObject.fromObject(paramMap);
        return jsonObject.toString();
    }

}
