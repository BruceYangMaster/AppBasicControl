package com.scxh.android1503.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class HandlerActivity extends Activity {
	private Handler mHandler3;
	private MyLoopThread myLooper;
	private TextView mMessageTxt;
	private Button mSendMsgBtn,mSendMsgOneBtn;
	private int count = 1; // 标识消息

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_handler_layout);
		mMessageTxt = (TextView) findViewById(R.id.message_txt);
		mSendMsgBtn = (Button) findViewById(R.id.send_btn);
		mSendMsgOneBtn = (Button) findViewById(R.id.send_one_btn);

		myLooper = new MyLoopThread();
		myLooper.start();

		mSendMsgBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message msg = Message.obtain();
				msg.what = 11;
				msg.arg1 = count++;
				myLooper.mHandler1.sendMessage(msg);

			}
		});

		
		mSendMsgOneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Message msg = Message.obtain();
				msg.what = 12;
				msg.arg1 = count++;
				
				myLooper.mHandler2.sendMessage(msg);
				
			}
		});
		
		mHandler3 = new Handler(getMainLooper(),new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				String obj = (String) msg.obj;
				mMessageTxt.setText("子线程发送到主线程执行 : " + obj);
				return true;
			}
		});
		
	}

	/**
	 * 消息在主线程执行的情况 消息在子线程执行的情况
	 * 
	 */
	public class MyLoopThread extends Thread {
		public Handler mHandler1;
		public Handler mHandler2;

		@Override
		public void run() {
			Looper.prepare();  //实例Looper，初始化消息队列MessageQueue

			mHandler1 = new Handler(getMainLooper(), new Callback() {

				@Override
				public boolean handleMessage(Message msg) {
					
					int arg1 = msg.arg1;
					Logs.v("消息在主线程执行的情况 :  消息 " + arg1);
					mMessageTxt.setText("主线程执行 :  消息 " + arg1);

					return true;
				}
			});
			
			
			mHandler2 = new Handler(Looper.myLooper(), new Callback() {

				@Override
				public boolean handleMessage(Message msg) {
					
					int arg1 = msg.arg1;
					Logs.v("消息在子线程执行的情况 :  消息 " + arg1);
					Message msg1 = Message.obtain();
					msg1.obj = "消息 " + arg1;
					mHandler3.sendMessage(msg1);

					return true;
				}
			});

			Looper.loop(); //循环从消息队列MessageQueue取消息分发给Handler处理
		}
	}

	/**
	 * 主线程（UI线程）也是一个Looper线程(1.有消息队列MQ,2.循环从MQ取消息)
	 */
	public void mainLooper() {
		Looper mainLooper = getMainLooper();

		Handler handler = new Handler(mainLooper, new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				// 处理消息(是耗时操作)
				return false;
			}
		});

		Message msg = Message.obtain();
		msg.what = 1;
		msg.obj = "消息1";

		handler.sendMessage(msg);
	}

	/**
	 * Android系统自带的Looper线程类（HandlerThread）
	 */
	public void mHandlerThread() {
		HandlerThread handlerThread = new HandlerThread("HandlerThread");
		handlerThread.start();

		Looper mLooper = handlerThread.getLooper();

		Handler handler = new Handler(mLooper, new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				// HandlerThread线程执行操作
				return true;
			}
		});

		handler.sendEmptyMessage(1);

		// 退出Looper循环，
		if (handlerThread != null) {
			handlerThread.quit();
		}
	}
}
