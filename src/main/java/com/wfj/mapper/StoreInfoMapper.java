package com.wfj.mapper;

import com.wfj.entity.StoreInfo;
import com.wfj.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface StoreInfoMapper extends BaseMapper {
    int deleteByPrimaryKey(String sid);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);

    List<StoreInfo> selectListByParam(Map<String, Object> paramMap);

}