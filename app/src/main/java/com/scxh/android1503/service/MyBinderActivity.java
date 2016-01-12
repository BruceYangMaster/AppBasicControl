package com.scxh.android1503.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.service.MyBinderService.IBCount;
import com.scxh.android1503.util.Logs;

/**
 * Activity组件类 1.声明交互接口 2.实现ServiceConnection类
 * ,在onServiceConnected方法中接收IBinder对象赋值给交互接口
 */
public class MyBinderActivity extends Activity implements OnClickListener {
	private Button mSetCountBtn;
	private Button mGetCountBtn;
	private TextView mShowCountTxt;

	private IBCount mIBCount;
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mIBCount = null;
			Logs.v("onServiceDisconnected >>>  ");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIBCount = (IBCount) service;
			Logs.v("onServiceConnected >>>  mIBCount:  " + mIBCount);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_binder_service_layout);

		mSetCountBtn = (Button) findViewById(R.id.binder_set_count_btn);
		mGetCountBtn = (Button) findViewById(R.id.binder_get_count_btn);
		mShowCountTxt = (TextView) findViewById(R.id.binder_show_count_txt);

		mSetCountBtn.setOnClickListener(this);
		mGetCountBtn.setOnClickListener(this);

		Intent intent = new Intent(this, MyBinderService.class);
		bindService(intent, serviceConnection, BIND_AUTO_CREATE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.binder_set_count_btn:
			int count = 30;
			mIBCount.setCount(count);

			break;
		case R.id.binder_get_count_btn:
			int counts = mIBCount.getCount();
			mShowCountTxt.setText("" + counts);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (serviceConnection != null)
			unbindService(serviceConnection);
	}
}
