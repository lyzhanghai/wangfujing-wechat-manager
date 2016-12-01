package com.wfj.service.intf;

import java.util.List;
import java.util.Map;

import com.wfj.entity.MsgReply;

public interface MsgDispatcherService {
	public String processMessage(Map<String, String> map);

	public String msgReplyText(String openid, String mpid, MsgReply msg);

	public String getReplyXml(String openid, String mpid, List<MsgReply> msgList);
}
