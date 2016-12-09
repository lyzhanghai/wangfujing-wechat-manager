package com.wfj.dto;

public class Result {
	
	/**
	 * 趋势图x坐标
	 */
	private Object xAxis;
	
	/**
	 * 趋势图y坐标
	 */
	private Object yAxis;
	
	/**
	 * 趋势图数据
	 */
	private Object data;

	public Object getxAxis() {
		return xAxis;
	}

	public void setxAxis(Object xAxis) {
		this.xAxis = xAxis;
	}

	public Object getyAxis() {
		return yAxis;
	}

	public void setyAxis(Object yAxis) {
		this.yAxis = yAxis;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [xAxis=" + xAxis + ", yAxis=" + yAxis + ", data=" + data
				+ "]";
	}
	
	
}
