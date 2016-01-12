package com.scxh.android1503.sms;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.store.db.DataColumns;

public class SmsActivity extends Activity {
	private Button mListenerBtn;
	private TextView mShowTxt;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 110:
				HashMap<String, String> map = (HashMap<String, String>) msg.obj;
				mShowTxt.setText(map.get("address") + " "+map.get("body")+ "  "+ map.get("read")+ " "+map.get("date"));
				getContentResolver().delete(DataColumns.SMS.CONTENT_URI, "address = ?", new String[]{"110"});
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_provider_layout);
		mListenerBtn = (Button) findViewById(R.id.sms_listener_btn);
		mShowTxt = (TextView) findViewById(R.id.sms_show_txt);
		
		mListenerBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mShowTxt.setText("监听开始...");
				
				SmsContentObserver smsObserver = new SmsContentObserver(SmsActivity.this,mHandler);
				getContentResolver().registerContentObserver(DataColumns.SMS.CONTENT_URI, true, smsObserver);
			}
		});
		
	}
	
	
}
