package com.wfj.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfj.entity.MsgReply;
import com.wfj.mapper.MsgReplyMapper;
import com.wfj.service.intf.MsgReplyService;

@Service
public class MsgReplyServiceImpl implements MsgReplyService {
	private static Logger logger = Logger.getLogger(MsgReplyServiceImpl.class);
	@Autowired
	private MsgReplyMapper msgReplyMapper;

	public int msgReplyInsertOrUpdate(MsgReply msgReply) {
		logger.info("start-msgReplyInsertOrUpdate");
		MsgReply entity = new MsgReply();
		entity.setMsgKey(msgReply.getMsgKey());
		List<MsgReply> msgReplyList = msgReplyMapper.selectListByParam(entity);
		if (msgReplyList != null && msgReplyList.size() > 0) {
			msgReply.setSid(msgReplyList.get(0).getSid());
			return msgReplyMapper.updateByPrimaryKeySelective(msgReply);
		} else {
			return msgReplyMapper.insertSelective(msgReply);
		}
	}

	public List<MsgReply> getMsgReplyList(MsgReply msgReply) {
		return msgReplyMapper.selectListByParam(msgReply);
	}

	public int delMsgReply(int sid) {
		return msgReplyMapper.deleteByPrimaryKey(sid);
	}
}
