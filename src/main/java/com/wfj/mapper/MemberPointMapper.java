package com.wfj.mapper;

import com.wfj.entity.MemberPoint;

public interface MemberPointMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberPoint record);

    int insertSelective(MemberPoint record);

    MemberPoint selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberPoint record);

    int updateByPrimaryKey(MemberPoint record);
}