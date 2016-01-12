package com.scxh.android1503.receive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MyMessageAdapterActivity;

public class MyBroadCastActivity extends Activity {
	private Button mSendBroadCastBtn,mSendPhoneBtn,mSendReceiverOneBtn;
	private MyCastReceiver mReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.receiver_mybroadcast_layout);
		
		mSendBroadCastBtn = (Button) findViewById(R.id.receiver_send_broadcast_btn);
		mSendPhoneBtn = (Button) findViewById(R.id.receiver_send_phone_broadcast_btn);
		mSendReceiverOneBtn = (Button) findViewById(R.id.receiver_send_broadcast_one_btn);
		
		mSendBroadCastBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyBroadCastReceiver.ACTION_SMS_RECEIVER);
				sendBroadcast(intent);
			}
		});
		
		mSendPhoneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyBroadCastReceiver.ACTION_PHONE_RECEIVER);
				sendBroadcast(intent);
			}
		});
		
		mSendReceiverOneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyCastReceiver.ACTION_ONE);
				sendBroadcast(intent);
			}
		});
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver();
	}
	/**
	 * 动态注册广播
	 */
	public void registerReceiver(){
		IntentFilter filter = new IntentFilter();
		filter.addAction(MyCastReceiver.ACTION_ONE);
		mReceiver = new MyCastReceiver();
		registerReceiver(mReceiver, filter);
	}
	
	/**
	 * 定义广播接收者
	 *
	 */
	public class MyCastReceiver extends BroadcastReceiver{
		public static final String ACTION_ONE = "com.scxh.android1503.ACTION_ONE";
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals(ACTION_ONE)){
				Toast.makeText(context, "自定义动态广播 :"+action, Toast.LENGTH_SHORT).show();
				startActivity(new Intent(context,MyMessageAdapterActivity.class));
			}
		}
		
	}
	@Override
	protected void onStop() {
		super.onStop();
		if(mReceiver != null){
			unregisterReceiver(mReceiver);
			mReceiver = null;
		}
	}
	
}
