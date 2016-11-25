package com.wfj.mapper;

import com.wfj.entity.MemberPointInfo;

import java.util.List;
import java.util.Map;

public interface MemberPointInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberPointInfo record);

    int insertSelective(MemberPointInfo record);

    MemberPointInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberPointInfo record);

    int updateByPrimaryKey(MemberPointInfo record);

    List<MemberPointInfo> selectListByParam(Map<String, Object> paramMap);
}