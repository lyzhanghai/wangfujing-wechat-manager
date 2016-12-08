package com.wfj.mapper;

import com.wfj.entity.GlobalDic;

public interface GlobalDicMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(GlobalDic record);

    int insertSelective(GlobalDic record);

    GlobalDic selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(GlobalDic record);

    int updateByPrimaryKey(GlobalDic record);
}