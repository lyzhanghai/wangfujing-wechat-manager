package com.wfj.message.resp;

/**
 * 视频消息
 * 
 * @Class Name VideoMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class VideoMessage extends BaseMessage {

	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}