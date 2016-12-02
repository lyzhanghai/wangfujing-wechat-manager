package com.wfj.mapper;

import com.wfj.dto.WechatAppDto;
import com.wfj.entity.AppAccountInfo;

import java.util.List;
import java.util.Map;

public interface AppAccountInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(AppAccountInfo record);

    int insertSelective(AppAccountInfo record);

    AppAccountInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(AppAccountInfo record);

    int updateByPrimaryKey(AppAccountInfo record);

    List<AppAccountInfo> selectListByParam(Map<String, Object> paramMap);

    List<WechatAppDto> selectAppInfoListByParam(WechatAppDto dto);
}