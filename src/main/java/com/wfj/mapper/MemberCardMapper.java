package com.wfj.mapper;

import com.wfj.entity.MemberCard;

import java.util.List;
import java.util.Map;

public interface MemberCardMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberCard record);

    int insertSelective(MemberCard record);

    MemberCard selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberCard record);

    int updateByPrimaryKey(MemberCard record);

    List<MemberCard> selectListByParam(Map<String, Object> paramMap);

    Map<String, Object> selectMaxCardCodeByParam(Map<String, Object> paramMap);

    /**
     * 根据门店号会员号修改会员卡信息
     *
     * @param memberCard
     * @return
     */
    int updateByParaSelective(MemberCard memberCard);
}