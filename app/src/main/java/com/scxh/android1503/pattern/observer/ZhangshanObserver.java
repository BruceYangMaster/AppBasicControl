package com.scxh.android1503.pattern.observer;

import com.scxh.android1503.util.Logs;

public class ZhangshanObserver implements Observer {

	@Override
	public void update(String message) {
		Logs.v("张三收到消息 :"+message);
	}

}
