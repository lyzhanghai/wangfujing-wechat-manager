package com.wfj.mapper;

import com.wfj.entity.WechatMenu;

import java.util.List;

public interface WechatMenuMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(WechatMenu record);

    int insertSelective(WechatMenu record);

    WechatMenu selectByPrimaryKey(Long sid);

    List<WechatMenu> selectByParam(WechatMenu record);

    int updateByPrimaryKeySelective(WechatMenu record);

    int updateByPrimaryKey(WechatMenu record);
}