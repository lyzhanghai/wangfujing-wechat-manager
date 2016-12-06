package com.wfj.dto;

import java.util.ArrayList;
import java.util.List;

public class MediaDto {
	private String media_id;
	private String url;
	private String name;
	private String update_time;
	private List<ArticleDto> content = new ArrayList<ArticleDto>();

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public List<ArticleDto> getContent() {
		return content;
	}

	public void setContent(List<ArticleDto> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MediaDto [media_id=" + media_id + ", url=" + url + ", name=" + name
				+ ", update_time=" + update_time + ", content=" + content + "]";
	}

}
