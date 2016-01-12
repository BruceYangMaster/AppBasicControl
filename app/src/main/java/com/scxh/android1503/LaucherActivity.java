package com.scxh.android1503;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class LaucherActivity extends Activity {
	private Handler mHandler3 = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_laucher_layout);
		starMainByHandler();
	}

	/**
	 * 通过Handler方式启动主界面
	 */
	public void starMainByHandler() {
		mHandler3.postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(LaucherActivity.this,MainActivity.class));
				finish();
			}
		}, 2000);
	}

	/**
	 * 启动主界面通过JAVA线程方式
	 */
	public void startMainByThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				startActivity(new Intent(LaucherActivity.this,MainActivity.class));
				finish();
			}
		}).start();
	}

}
