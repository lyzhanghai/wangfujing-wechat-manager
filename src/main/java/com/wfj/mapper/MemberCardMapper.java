package com.wfj.mapper;

import com.wfj.entity.MemberCard;

public interface MemberCardMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberCard record);

    int insertSelective(MemberCard record);

    MemberCard selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberCard record);

    int updateByPrimaryKey(MemberCard record);
}