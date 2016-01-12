package com.scxh.android1503.pattern.callback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class CallBackActivity extends Activity{
	private ProgressBar mProgressBar;
	private Button mStartBtn;
	
	public interface ProgressBarCallInterface {
		public void execute(String message);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.pattner_view_layout);
		mProgressBar = (ProgressBar) findViewById(R.id.pattern_progressbar);
		mStartBtn = (Button) findViewById(R.id.pattern__start_btn);

		mStartBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CallBackThread callbackThread = new CallBackThread();
				callbackThread.setCallInterface(new ProgressBarCallInterface() {
					
					@Override
					public void execute(final String message) {
						Logs.i("execute :"+message);
					}
				});
				callbackThread.start();
			}
		});

	}

	public class CallBackThread extends Thread {
		public ProgressBarCallInterface mProgressBarCallInterface;

		public void setCallInterface(ProgressBarCallInterface progressBarCallInterface) {
			this.mProgressBarCallInterface = progressBarCallInterface;
		}

		@Override
		public void run() {
			for (int i = 1; i <= 100; i++) {
				mProgressBar.setProgress(i);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				downFile(i);
			}
			
			if (mProgressBarCallInterface != null)
				mProgressBarCallInterface.execute("下载完成");
		}
	}
	
	public void downFile(int i){
		Logs.v("文件下载...."+i+"%");
	}
	
	public void testCallBack() {
		Boss boss = new Boss();
		Employer employer = new Employer();

		employer.setCallInterfacel(boss);

		employer.doSome();
	}
}
