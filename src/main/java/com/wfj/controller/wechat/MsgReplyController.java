package com.wfj.controller.wechat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.entity.MsgReply;
import com.wfj.service.intf.MsgReplyService;

/**
 * 消息管理模块
 * 
 * @Class Name MsgReplyController
 * @Author yedong
 * @Create In 2016年11月28日
 */
@Controller
@RequestMapping(value = "/msgReply")
public class MsgReplyController {

	@Autowired
	private MsgReplyService msgReplyService;

	@ResponseBody
	@RequestMapping(value = "/msgReplyInsertOrUpdate", method = { RequestMethod.POST })
	public void msgReplyInsertOrUpdate(@RequestBody MsgReply msgReply) {
		msgReplyService.msgReplyInsertOrUpdate(msgReply);
	}

	public List<MsgReply> getMsgReplyList(@RequestBody MsgReply msgReply) {
		return msgReplyService.getMsgReplyList(msgReply);
	}

	public int delMsgReply(@RequestBody Integer sid) {
		return msgReplyService.delMsgReply(sid);
	}
}
