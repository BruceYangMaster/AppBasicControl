package com.scxh.android1503.ui.listview.refresh;

import java.util.List;

public class PagerBean {
	private int code;    //响应码
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<MsgBean> getContent() {
		return content;
	}
	public void setContent(List<MsgBean> content) {
		this.content = content;
	}
	private int pagecount;  //总页数
	private String message; //响应消息
	private List<MsgBean> content; 
}
