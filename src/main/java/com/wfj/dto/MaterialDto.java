package com.wfj.dto;

import java.util.ArrayList;
import java.util.List;

public class MaterialDto {
	private List<MediaDto> item = new ArrayList<MediaDto>();
	private int total_count; // 该类型的素材的总数
	private int item_count; // 本次调用获取的素材的数量

	public List<MediaDto> getItem() {
		return item;
	}

	public void setItem(List<MediaDto> item) {
		this.item = item;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getItem_count() {
		return item_count;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	@Override
	public String toString() {
		return "MaterialDto [item=" + item + ", total_count=" + total_count + ", item_count="
				+ item_count + "]";
	}

}
