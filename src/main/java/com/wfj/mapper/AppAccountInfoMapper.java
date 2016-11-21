package com.wfj.mapper;

import com.wfj.entity.AppAccountInfo;

public interface AppAccountInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(AppAccountInfo record);

    int insertSelective(AppAccountInfo record);

    AppAccountInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(AppAccountInfo record);

    int updateByPrimaryKey(AppAccountInfo record);
}