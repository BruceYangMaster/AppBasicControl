package com.scxh.android1503.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.ScxhApp;

public class EventActivity extends Activity implements OnClickListener {
	private TextView mMessageTxt;
	private Button mConfirmBtn, mHelloBtn, mInnerClassBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_layout);

//		ScxhApp app = (ScxhApp) getApplication();
		
		
		mMessageTxt = (TextView) findViewById(R.id.event_message_txt);
		mConfirmBtn = (Button) findViewById(R.id.event_conform_btn);
		mHelloBtn = (Button) findViewById(R.id.event_hello_btn);
		mInnerClassBtn = (Button) findViewById(R.id.event_innerclass_btn);

		// 事件处理方式之一 匿名内部类实现
		mConfirmBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mMessageTxt.setText("匿名内部类");
			}
		});

		mInnerClassBtn.setOnClickListener(new onButtonClickListener());

		mHelloBtn.setOnClickListener(this);// 事件处理方式之二 接口实现
	}

	@Override
	public void onClick(View v) {
		mMessageTxt.setText("接口实现方式");
	}

	class onButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			mMessageTxt.setText("内部类");
		}

	}
	
	
	public void onAndroidClickListner(View v){
		mMessageTxt.setText("android事件实现");
	}
	

}
