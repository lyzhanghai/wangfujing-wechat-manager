package com.wfj.mapper;

import com.wfj.entity.MemberInfo;

public interface MemberInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);
}