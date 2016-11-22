package com.wfj.dto;

public class WeiXinDto {
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String appid;
	private String appSecret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	@Override
	public String toString() {
		return "WeiXinDto [signature=" + signature + ", timestamp=" + timestamp + ", nonce="
				+ nonce + ", echostr=" + echostr + ", appid=" + appid + ", appSecret=" + appSecret
				+ "]";
	}

}
