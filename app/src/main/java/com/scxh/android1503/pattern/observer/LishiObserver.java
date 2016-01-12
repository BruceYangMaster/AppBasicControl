package com.scxh.android1503.pattern.observer;

import com.scxh.android1503.util.Logs;

public class LishiObserver implements Observer {

	@Override
	public void update(String message) {
		Logs.v("李四收到消息 :"+message);
	}

}
