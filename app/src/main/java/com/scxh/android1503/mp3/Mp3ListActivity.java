package com.scxh.android1503.mp3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class Mp3ListActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private Button mSearchMusicBtn, mPlayerMusicBtn;
	private Button mPauseMusicBtn;
	private SeekBar mSeekBar;
	private ListView mListView;
	private MediaPlayer mMediaPlayer = new MediaPlayer();;
	private ArrayList<File> mFileList = new ArrayList<File>();
	private FileAdapter mAdapter;
	private boolean flag = true;
	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mp3_media_list_layout);
		mListView = (ListView) findViewById(R.id.mp3_listview);
		mSearchMusicBtn = (Button) findViewById(R.id.mp3_music_search_btn);
		mPlayerMusicBtn = (Button) findViewById(R.id.mp3_music_player_btn);
		mPauseMusicBtn = (Button) findViewById(R.id.mp3_music_pasue_btn);
		mSeekBar = (SeekBar) findViewById(R.id.mp3_seekbar);

		mSearchMusicBtn.setOnClickListener(this);
		mPlayerMusicBtn.setOnClickListener(this);
		mPauseMusicBtn.setOnClickListener(this);
		mListView.setOnItemClickListener(this);

		mAdapter = new FileAdapter(this);
		mListView.setAdapter(mAdapter);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (flag) {
					if (mMediaPlayer.isPlaying()) {

						mSeekBar.setMax(mMediaPlayer.getDuration());
						mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
					}
				}
			}
		}).start();
		
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				playerMusic("");
			}
		});

	}
	/**
	 * 设置透明度（这是窗体本身的透明度，非背景）
	 */
	public void setWindowAlpha() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.8f;/** alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明*/
		getWindow().setAttributes(lp);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mp3_music_search_btn:
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					scanFileList(Environment.getExternalStorageDirectory());
					
					/**ListView刷新必须在UI线程中 通过Handler消息机制发送刷新代码到UI主线程执行*/
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							mAdapter.setListData(mFileList);
						}
					});
				}
			}).start();
			
			break;
		case R.id.mp3_music_pasue_btn:
			mMediaPlayer.pause();
			break;
		case R.id.mp3_music_player_btn:
			mMediaPlayer.start();

			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		File file = (File) parent.getAdapter().getItem(position);
		playerMusic(file.getAbsolutePath());
	}

	public void playerMusic(String path) {

		try {
			// if(mMediaPlayer.isPlaying()){
			mMediaPlayer.reset();
			// }

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
	}

	/**
	 * 扫描Sdcard（外部存储）下所有文件
	 */
	public void scanFileList(File parentFile) {
		File[] listFile = parentFile.listFiles();

		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File file = listFile[i];
				if (file.isDirectory()) {
					scanFileList(file);
				} else {
					if (file.getName().endsWith("mp3")) {
						Logs.v("文件名 :" + file.getName());
						Logs.i("路径 :" + file.getAbsolutePath());
						mFileList.add(file);
					}
				}
			}
		}

	}

	public class FileAdapter extends BaseAdapter {
		private ArrayList<File> list = new ArrayList<File>();
		private LayoutInflater layoutInflater;

		public FileAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
		}

		public void setListData(ArrayList<File> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = (TextView) layoutInflater.inflate(
						android.R.layout.simple_list_item_1, null);
			}

			File file = (File) getItem(position);

			TextView nameTxt = (TextView) convertView;
			nameTxt.setText(file.getName());

			return convertView;
		}
	}

}
