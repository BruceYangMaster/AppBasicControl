package com.scxh.android1503.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class AsyncTaskActivity extends Activity implements OnClickListener{
	private SeekBar mSeekBar;
	private Button mStartDownLoadBtn,mAsyncTaskLoadBtn,mStopLoadBtn;
	private TextView mShowMessageTxt;
	private MyAsyncTask mAsyncTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask_download_layout);
		mSeekBar = (SeekBar) findViewById(R.id.async_seekbar);
		mStartDownLoadBtn = (Button) findViewById(R.id.async_start_btn);
		mAsyncTaskLoadBtn = (Button) findViewById(R.id.async_load_btn);
		mStopLoadBtn = (Button) findViewById(R.id.async_stop_btn);
		mShowMessageTxt = (TextView) findViewById(R.id.async_show_message_txt);
		
		mStartDownLoadBtn.setOnClickListener(this);
		mAsyncTaskLoadBtn.setOnClickListener(this);
		mStopLoadBtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.async_start_btn:
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int i = 0; i < 100; i++){
						mSeekBar.setProgress(i);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			break;
			
		case R.id.async_load_btn:
			String url = "http://www.baidu.com/mm.png";
			String url1 = "http://www.baidu.com/mm1.png";
			mAsyncTask = new MyAsyncTask(); 
			mAsyncTask.execute(url,url1);   /**默认排队执行异步任务，  每执行一次异步任务将被放任务队列*/
			break;
		case R.id.async_stop_btn:
			Logs.v("mAsyncTask.isCancelled() :"+mAsyncTask.isCancelled());
			if(!mAsyncTask.isCancelled()){
				mAsyncTask.cancel(true); //停止异步任务
				Logs.v("mAsyncTask.cancel(true); //停止异步任务");
			}
			break;
		}
 	}
	/**
	 * AsyncTask<String, Integer, String>
	 * Params 第一个参数: 传入异步任务中的数据类型
	 * Progress 第二个参数:  异步任务执行过程中发送数据类型
	 * Result  第三个参数:   异步任务执行完成后返回数据类型
	 * 
	 * 注:如果类型为 Void  表示没有参数
	 */
	public class MyAsyncTask extends AsyncTask<String, Integer, String>{
		/**
		 * 执行异步任务前调用方法
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Logs.v("onPreExecute >>>>>> ");
		}
		/**
		 * 后台（子线程）执行异步任务
		 */
		@Override
		protected String doInBackground(String... params) {
			Logs.v("doInBackground >>>>>> ");
			String url1 = params[0];
			String url2 = params[1];
			Logs.v("url1 : "+url1 + "\n  url2 :"+url2 );
			
			for(int i = 1; i <= 100; i++){
				
				publishProgress(i);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String result = "下载成功";
			return result;
		}
		/**
		 * 异步任务执行过程中调用方法.
		 * 必须在doInBackground方法中调用publishProgress()方法发送消息
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			int progress = values[0];
			Logs.v("onProgressUpdate >>>>>> "+progress);
			mSeekBar.setProgress(progress);
			mShowMessageTxt.setText(""+progress);
		}
		
		/**
		 * 异步任务执行完成后调用方法
		 */
		@Override
		protected void onPostExecute(String result) {
			Logs.v("onPostExecute >>>>>> ");
			super.onPostExecute(result);
			mShowMessageTxt.setText(result);
		}
		
	}
}
