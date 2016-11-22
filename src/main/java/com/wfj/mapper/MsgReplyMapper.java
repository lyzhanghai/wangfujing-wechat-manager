package com.wfj.mapper;

import com.wfj.entity.MsgReply;

public interface MsgReplyMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(MsgReply record);

    int insertSelective(MsgReply record);

    MsgReply selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(MsgReply record);

    int updateByPrimaryKey(MsgReply record);
}