package com.wfj.service.intf;

import com.wfj.entity.AppAccountInfo;
import com.wfj.message.req.StoreInfoDto;

import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 16-11-23.
 */
public interface IAppAccountInfoService {

    /**
     * 查询门店与APP关系列表
     *
     * @param paramMap
     * @return
     */
    public List<AppAccountInfo> queryAppAccount(Map<String, Object> paramMap);

    public StoreInfoDto getStoreInfo(String appid);
}
