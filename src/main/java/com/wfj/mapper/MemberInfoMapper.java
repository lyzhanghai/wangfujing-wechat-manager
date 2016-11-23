package com.wfj.mapper;

import com.wfj.entity.MemberInfo;

import java.util.List;
import java.util.Map;

public interface MemberInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);

    List<MemberInfo> selectListByParam(Map<String, Object> paramMap);

    Map<String, Object> selectMaxMemberCodeByParam(Map<String, Object> paramMap);
}