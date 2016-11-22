package com.wfj.message.resp;

/**
 * 视频消息体
 * 
 * @Class Name Video
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class Video {

	private String MediaId;
	private String Title;
	private String Description;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}