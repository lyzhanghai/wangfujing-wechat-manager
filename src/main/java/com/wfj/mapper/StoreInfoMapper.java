package com.wfj.mapper;

import com.wfj.entity.StoreInfo;

import java.util.List;
import java.util.Map;

public interface StoreInfoMapper {
    int deleteByPrimaryKey(String sid);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);

    List<StoreInfo> selectListByParam(Map<String, Object> paramMap);

    List<StoreInfo> selectListByParamLike(Map<String, Object> paramMap);

    int updateByParaSelective(StoreInfo storeInfo);

    int batchDeleteByPara(List<String> storeCodeList);
}