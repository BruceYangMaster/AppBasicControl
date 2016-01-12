package com.scxh.android1503.ui.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.scxh.android1503.R;

public class ProgressBarActivity extends Activity {
	private static final int MSG_TAOST = 10;
	private ProgressBar mProgressBar;
	private SeekBar mSeekBar;
	private Button mStartBtn, mStartSeekBarBtn;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MSG_TAOST:
				String obj = (String) msg.obj;
				Toast.makeText(ProgressBarActivity.this, obj, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bar_progress_layout);

		new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				
				return false;
			}
		});
		
		
		mProgressBar = (ProgressBar) findViewById(R.id.bar_progressbar);
		mSeekBar = (SeekBar) findViewById(R.id.bar_seekbar);
		mStartBtn = (Button) findViewById(R.id.bar_start_btn);
		mStartSeekBarBtn = (Button) findViewById(R.id.bar_seek_start_btn);

		mStartBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new Thread(new Runnable() {

					@Override
					public void run() {
						for (int i = 1; i <= 100; i++) {

							mProgressBar.setProgress(i);

							// mProgressBar.setSecondaryProgress(i+2);

							// mProgressBar.incrementProgressBy(1);

							Log.v("tag", "1加载 :  " + mProgressBar.getProgress());
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						Log.v("tag", "1加载完成");
						
						
						Message msg = Message.obtain();
						msg.what = MSG_TAOST;
						msg.obj = "加载完成!";
						mHandler.sendMessage(msg);
						
					}
				}).start();
			}
		});

		mStartSeekBarBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						for(int i = 1; i<=mSeekBar.getMax(); i++){
							mSeekBar.setProgress(i);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						Log.i("tag", "seekbar加载完成");
						
						mHandler.post(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(ProgressBarActivity.this, "seekbar加载完成", Toast.LENGTH_SHORT).show();
							}
						});
					}
				}).start();
			}
		});
		
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.d("tag", "onStopTrackingTouch   ");
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.d("tag", "onStartTrackingTouch   ");
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.d("tag", "onProgressChanged  progress "+progress);
			}
		});
		
	}

	public class MyThread implements Runnable {
		@Override
		public void run() {

			for (int i = 1; i <= 100; i++) {
				mProgressBar.setProgress(i);
				Log.v("tag", "加载 :" + i);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Log.v("tag", "加载完成");
		}

	}
}
