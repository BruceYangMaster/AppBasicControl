package com.scxh.android1503.pattern.observer;

import com.scxh.android1503.util.Logs;

public class WangerObserver implements Observer {

	@Override
	public void update(String message) {
		Logs.v("王二收到消息 :"+message);
	}
	
	@Override
	public String toString() {
		return "王二";
	}

}
