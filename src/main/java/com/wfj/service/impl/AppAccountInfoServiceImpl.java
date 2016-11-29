package com.wfj.service.impl;

import com.wfj.entity.AppAccountInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.message.req.StoreInfoDto;
import com.wfj.service.intf.IAppAccountInfoService;
import com.wfj.util.JsonUtil;
import com.wfj.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 16-11-23.
 */
@Service
public class AppAccountInfoServiceImpl implements IAppAccountInfoService {
    private Logger logger = Logger.getLogger(AppAccountInfoServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AppAccountInfoMapper appAccountInfoMapper;

    /**
     * 查询门店与APP关系列表
     *
     * @param paramMap
     * @return
     */
    public List<AppAccountInfo> queryAppAccount(Map<String, Object> paramMap) {
        List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paramMap);
        return appAccountInfoList;
    }


    /**
     * 根据门店标识获取公主号信息
     *
     * @param appid
     * @return StoreInfoDto
     * @Methods Name getStoreInfo
     * @Create In 2016年9月26日 By kongqf
     */
    public StoreInfoDto getStoreInfo(String appid) {
        String storeInfoStr = null;
        StoreInfoDto storeInfoDto = new StoreInfoDto();
        storeInfoStr = redisUtil.getKey("storeInfoDto" + appid, "0000");
        if ("0000".equals(storeInfoStr)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appid", appid);
            List<AppAccountInfo> appAccountInfos = queryAppAccount(map);

            if (appAccountInfos != null && appAccountInfos.size() > 0) {
                AppAccountInfo model = appAccountInfos.get(0);
                storeInfoDto.setStoreCode(model.getStorecode());
                //storeInfoDto.setStoceName(object.getString("organizationName"));
                storeInfoDto.setAppId(model.getAppid());
                storeInfoDto.setSecret(model.getAppsecret());

                redisUtil.setIsOK("storeInfoDto" + appid, JsonUtil.getJSONString(storeInfoDto));
            }

        } else {
            storeInfoDto = JsonUtil.getDTO(storeInfoStr, StoreInfoDto.class);
        }
        return storeInfoDto;
    }

}
