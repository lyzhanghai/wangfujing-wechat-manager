package com.wfj.mapper;

import java.util.List;
import java.util.Map;

import com.wfj.entity.MsgReply;

public interface MsgReplyMapper {
	List<MsgReply> selectListByParam(Map<String, Object> paramMap);

	List<MsgReply> selectPageListByParam(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);

	List<MsgReply> selectListByParam(MsgReply entity);

	List<MsgReply> selectPageListByParam(MsgReply entity);

	Integer getCountByParam(MsgReply entity);

	int deleteByPrimaryKey(Integer sid);

	int insert(MsgReply record);

	int insertSelective(MsgReply record);

	MsgReply get(Integer sid);

	int updateByPrimaryKeySelective(MsgReply record);

	int updateByPrimaryKey(MsgReply record);
}