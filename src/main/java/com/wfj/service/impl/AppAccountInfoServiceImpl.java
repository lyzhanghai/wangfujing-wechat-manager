package com.wfj.service.impl;

import com.wfj.entity.AppAccountInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.service.intf.IAppAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 16-11-23.
 */
public class AppAccountInfoServiceImpl implements IAppAccountInfoService {

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
}
