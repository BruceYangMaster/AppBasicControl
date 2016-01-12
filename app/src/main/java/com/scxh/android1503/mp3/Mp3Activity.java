package com.scxh.android1503.mp3;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android1503.R;

public class Mp3Activity extends Activity implements OnClickListener {
	private Button mPlayerMusicOneBtn, mPlayerMusicTwoBtn,
			mPlayerMusicThreeBtn;
	private Button mPauseMusicBtn;
	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mp3_media_layout);
		mPlayerMusicOneBtn = (Button) findViewById(R.id.mp3_music_one_btn);
		mPlayerMusicTwoBtn = (Button) findViewById(R.id.mp3_music_two_btn);
		mPlayerMusicThreeBtn = (Button) findViewById(R.id.mp3_music_three_btn);
		mPauseMusicBtn = (Button) findViewById(R.id.mp3_music_pasue_btn);
		mPlayerMusicOneBtn.setOnClickListener(this);
		mPlayerMusicTwoBtn.setOnClickListener(this);
		mPlayerMusicThreeBtn.setOnClickListener(this);
		mPauseMusicBtn.setOnClickListener(this);
		
		initMusic();
	}

	public void initMusic() {
		String path = "file://" + Environment.getExternalStorageDirectory()+ "/music/BLUE.mp3";
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(path);

			mMediaPlayer.prepare();

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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mp3_music_one_btn:
			playerMp3One();
			break;
		case R.id.mp3_music_two_btn:
			playerMp3ByMediaRaw();
			break;
		case R.id.mp3_music_three_btn:
			playerMusic();
			break;
		case R.id.mp3_music_pasue_btn:
			puaseMusic();
			break;

		}
	}

	public void playerMp3One() {
		String mp3FileDir = "file://"
				+ Environment.getExternalStorageDirectory() + "/music/"
				+ "qlyh.mp3";
		Uri data = Uri.parse(mp3FileDir);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(data, "audio/mp3");
		startActivity(intent);
	}

	public void playerMp3ByMediaRaw() {
		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.dzw);
		mediaPlayer.start();
	}

	/**
	 * 播放音乐
	 */
	public void playerMusic() {
		mMediaPlayer.start();
	}

	/**
	 * 暂停音乐
	 */
	public void puaseMusic() {
		mMediaPlayer.pause();
	}

}
