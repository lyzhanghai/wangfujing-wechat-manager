package com.wfj.message.req;

public class StoreInfoDto {
	/**
	 * 门店编号
	 */
	private String storeCode;

	/**
	 * 门店名称
	 */
	private String stoceName;

	/**
	 * appid
	 */
	private String appId;

	/**
	 * secret
	 */
	private String secret;

	/**
	 * isChannel
	 */
	private String isChannel;

	/**
	 * storeFlag 64位编码
	 */
	private String storeFlag;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoceName() {
		return stoceName;
	}

	public void setStoceName(String stoceName) {
		this.stoceName = stoceName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getIsChannel() {
		return isChannel;
	}

	public void setIsChannel(String isChannel) {
		this.isChannel = isChannel;
	}

	public String getStoreFlag() {
		return storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

	@Override
	public String toString() {
		return "StoreInfoDto [storeCode=" + storeCode + ", stoceName=" + stoceName + ", appId="
				+ appId + ", secret=" + secret + ", isChannel=" + isChannel + ", storeFlag="
				+ storeFlag + "]";
	}

}
