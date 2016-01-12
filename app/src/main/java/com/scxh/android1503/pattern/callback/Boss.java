package com.scxh.android1503.pattern.callback;

import com.scxh.android1503.util.Logs;

public class Boss implements CallInterfacel{

	@Override
	public void call(String message) {
		Logs.v("boss收到消息 :"+message);
		//继续做下一步事
	}

}
