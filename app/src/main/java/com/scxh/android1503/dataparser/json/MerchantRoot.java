package com.scxh.android1503.dataparser.json;

public class MerchantRoot {
	private int resultCode;
	private String resultInfo;
	private MerchantInfo info;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	public MerchantInfo getInfo() {
		return info;
	}

	public void setInfo(MerchantInfo info) {
		this.info = info;
	}
}
