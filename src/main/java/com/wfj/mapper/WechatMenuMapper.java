package com.wfj.mapper;

import com.wfj.entity.WechatMenu;

public interface WechatMenuMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(WechatMenu record);

    int insertSelective(WechatMenu record);

    WechatMenu selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(WechatMenu record);

    int updateByPrimaryKey(WechatMenu record);
}