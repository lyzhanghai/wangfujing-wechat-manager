package com.wfj.dto;

import com.wfj.entity.MsgReply;
import com.wfj.mapper.MsgReplyMapper;
import com.wfj.message.resp.*;
import com.wfj.util.MessageUtil;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MsgDispatcher {
	private static Logger logger = Logger.getLogger(MsgDispatcher.class);

	@Inject
	private MsgReplyMapper msgReplyMapper;

	public String processMessage(Map<String, String> map) {
		logger.info(map.toString());
		String openid = map.get("FromUserName"); // 用户 openid
		String mpid = map.get("ToUserName"); // 公众号原始 ID
		logger.info("openid" + openid + "mpid" + mpid);
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			// 普通文本消息
			BaseMessage basemsg = new BaseMessage();
			basemsg.setToUserName(openid);
			basemsg.setFromUserName(mpid);
			basemsg.setCreateTime(new Date().getTime());
			TextMessage txtmsg = new TextMessage();
			txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			logger.info(txtmsg);
			String content = map.get("Content");
			MsgReply msg = new MsgReply();
			msg.setMsgKey(content);
			List<MsgReply> msgList = msgReplyMapper.selectListByParam(msg);
			if (msgList != null && msgList.size() > 0) {
				MsgReply msgReply = msgList.get(0);
				if (msgReply.getMsgType().equals(0)) {// (0文本,1图片,2语音,3视频,4音频,5图文)
					txtmsg.setContent(msgReply.getContent());
				} else if (msgReply.getMsgType().equals(1)) {
					ImageMessage imgMsg = new ImageMessage();
					Image image = new Image();
					image.setMediaId(msgReply.getMediaId());
					imgMsg.setImage(image);
					imgMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
					return MessageUtil.imageMessageToXml(imgMsg);
				} else if (msgReply.getMsgType().equals(2)) {

				} else if (msgReply.getMsgType().equals(3)) {

				} else if (msgReply.getMsgType().equals(4)) {

				} else if (msgReply.getMsgType().equals(5)) {

				}
			} else {
				txtmsg.setContent("你好，欢迎来到王府井百货公众平台！");
				return MessageUtil.textMessageToXml(txtmsg);
			}
			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			// 对图文消息
			NewsMessage newmsg = new NewsMessage();
			newmsg.setToUserName(openid);
			newmsg.setFromUserName(mpid);
			newmsg.setCreateTime(new Date().getTime());
			newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

			System.out.println("==============这是图片消息！");
			Article article = new Article();
			article.setDescription("这是图文消息 1"); // 图文消息的描述
			article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); // 图文消息图片地址
			article.setTitle("图文消息 1"); // 图文消息标题
			article.setUrl("http://www.cuiyongzhi.com"); // 图文 url 链接
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			logger.info("==============这是链接消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			logger.info("==============这是位置消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
			logger.info("==============这是视频消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			logger.info("==============这是语音消息！");
		}

		return null;
	}
}