package com.scxh.android1503.dataparser.json;

import java.util.ArrayList;

import com.scxh.android1503.http.MerchantBean;

public class MerchantInfo {
	private PageInfo pageInfo;
	private ArrayList<MerchantBean> merchantKey;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public ArrayList<MerchantBean> getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(ArrayList<MerchantBean> merchantKey) {
		this.merchantKey = merchantKey;
	}

}
