package com.scxh.android1503.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;

import com.scxh.android1503.util.Logs;

public class MyService extends Service {
	public static final int PLAYER_STATE = 1;// 播放
	public static final int PASUE_STATE = 2; // 停止
	private boolean flag = true;
	private MediaPlayer mMediaPlayer;

	@Override
	public void onCreate() {
		super.onCreate();
		Logs.v("onCreate >>>>>>>>>>");

		intiMusic();
	}

	public void intiMusic() {
//		String path = "file://" + Environment.getExternalStorageDirectory()
//				+ "/music/BLUE.mp3";
		String path = "file://" + Environment.getExternalStorageDirectory()
				+ "/music/qianlvyouhun.mp3";
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(path);

			mMediaPlayer.prepare();

			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					stopSelf();
				}
			});
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (flag) {
						if (mMediaPlayer.isPlaying()) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							int duration = mMediaPlayer.getDuration();
							int current = mMediaPlayer.getCurrentPosition();
							
							Message msg  = Message.obtain();
							msg.what = 1;
							msg.arg1 = duration;
							msg.arg2 = current;
							TestServcieActivity.mHandler.sendMessage(msg);
						}
					}
				}
			}).start();
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logs.d("onStartCommand >>>>>>>>>>");
		if (intent == null) {
			return super.onStartCommand(intent, flags, startId);
		}

		int playState = intent.getIntExtra("PLAYER", 0);
		switch (playState) {
		case PLAYER_STATE:
			mMediaPlayer.start();
			break;
		case PASUE_STATE:
			mMediaPlayer.pause();
			break;
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Logs.i("onBind >>>>>>>>>>");
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Logs.w("onUnbind >>>>>>>>>>");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Logs.e("onDestroy >>>>>>>>>>");
		super.onDestroy();
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer = null;
		}
	}

}
