package com.wfj.mapper;

import com.wfj.entity.MemberPointInfo;

public interface MemberPointInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberPointInfo record);

    int insertSelective(MemberPointInfo record);

    MemberPointInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberPointInfo record);

    int updateByPrimaryKey(MemberPointInfo record);
}