package com.scxh.android1503.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {
	/** 定义接收短信广播 */
	public static final String ACTION_SMS_RECEIVER = "com.scxh.android1503.receiver.ACTION_SMS_RECEIVER";
	/** 定义接收电话广播 */
	public static final String ACTION_PHONE_RECEIVER = "com.scxh.android1503.receiver.ACTION_PHONE_RECEIVER";
	public static final String INTENT_ACTION_SMS_RECEIVER = "android.provider.Telephony.SMS_RECEIVED";

	/**
	 * 广播接收者，用于接收,过虑并处理广播.
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(ACTION_SMS_RECEIVER)) {
			// 处理短信
			Toast.makeText(context, "接收到处理短信广播", Toast.LENGTH_SHORT).show();
		} else if (action.equals(ACTION_PHONE_RECEIVER)) {
			// 处理电话
			Toast.makeText(context, "接收到处理电话广播", Toast.LENGTH_SHORT).show();
		} else if (action.equals(INTENT_ACTION_SMS_RECEIVER)) {
			Toast.makeText(context, "接收到系统短信广播", Toast.LENGTH_SHORT).show();
		}
	}

}
