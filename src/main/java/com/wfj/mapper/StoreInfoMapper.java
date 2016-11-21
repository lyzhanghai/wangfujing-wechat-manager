package com.wfj.mapper;

import com.wfj.entity.StoreInfo;

public interface StoreInfoMapper {
    int deleteByPrimaryKey(String sid);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
}