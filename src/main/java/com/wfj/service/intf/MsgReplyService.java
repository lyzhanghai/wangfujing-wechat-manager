package com.wfj.service.intf;

import java.util.List;

import com.wfj.entity.MsgReply;

public interface MsgReplyService {
	public void msgReplyInsertOrUpdate(MsgReply msgReply);

	public List<MsgReply> getMsgReplyList(MsgReply msgReply);

	public int delMsgReply(int sid);
}
