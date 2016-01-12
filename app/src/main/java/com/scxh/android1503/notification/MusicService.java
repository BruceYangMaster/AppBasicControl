package com.scxh.android1503.notification;

import java.io.IOException;

import com.scxh.android1503.util.Logs;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
	public static final String ACTION_PLAYER = "com.scxh.android.ACTION_PLAYER";
	public static final String ACTION_PAUSE = "com.scxh.android.ACTION_PAUSE";
	
	private MediaPlayer mMediaPlayer;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int playerState = intent.getIntExtra("PLAYSER_STATE", -1);
		
		if (playerState == 1) {
			String path = intent.getStringExtra("MUSIC_PATH");
			if(mMediaPlayer == null){
				mMediaPlayer = new MediaPlayer();
			}
			try {
				mMediaPlayer.reset();
				mMediaPlayer.setDataSource(path);
				mMediaPlayer.prepare();
				mMediaPlayer.start();

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (mMediaPlayer == null) {
				return super.onStartCommand(intent, flags, startId);
			}
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
			}

		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
