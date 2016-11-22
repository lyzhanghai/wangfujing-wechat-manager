package com.wfj.message.req;

/**
 * 图片消息
 * @Class Name ImageMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
