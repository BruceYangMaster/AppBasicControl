package com.scxh.android1503.mp3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class MusicPlayerList extends ListActivity {
	public Handler mHandler = new Handler();

	public BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {

				Toast.makeText(context, "开始扫描", Toast.LENGTH_SHORT).show();

			} else if (intent.getAction().equals(
					Intent.ACTION_MEDIA_SCANNER_FINISHED)) {

				Toast.makeText(context, "完成扫描", Toast.LENGTH_SHORT).show();
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mp3_mediastore_layout);
		List<Mp3Info> list = getMp3Infos();

		for (int i = 0; i < list.size(); i++) {
			Mp3Info mp3 = list.get(i);
			Logs.v(" " + mp3.getTitle() + ", " + mp3.getArtist() + " ,"
					+ mp3.getAlbum() + " ," + mp3.getUrl());
		}

		MyBaseAdapter adapter = new MyBaseAdapter(this);
		setListAdapter(adapter);
		adapter.setData(list);

		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		filter.addDataScheme("file");
		registerReceiver(receiver, filter);
		
	}

	/**
	 * 按钮事件处理
	 * 
	 * @param v
	 */
	public void onScannerMediaStoreLisentner(View v) {
		// String path =
		// Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+
		// "/dzw.mp3";
		// scanFileAsync(this, path);

		// String dir =
		// ""+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
		// scanDirAsync(this,dir);

		String paths = "file://"
				+ Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/dzw.mp3";
		scannerFile(paths);

		// String pathdel =
		// Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+
		// "/dzw.mp3";
		// deleteMediaFile(pathdel);
		
//		String path = "file://" + Environment.getExternalStorageDirectory();
//		scanFileDirMount(path);
	}

	/**
	 * 在4.4之后扫描音乐媒体库方法 立即执行扫描指定文件
	 */
	public void scannerFile(final String path) {
		Logs.v("扫描指定文件 MediaScannerConnection >>> :" + path);
		mHandler.post(new Runnable() {

			@Override
			public void run() {
//				MediaScannerConnection.MediaScannerConnectionClient client = new MediaScannerConnectionClient() {
//					
//					@Override
//					public void onScanCompleted(String path, Uri uri) {
//						Logs.v("onScanCompleted >>> :" + path);
//					}
//					
//					@Override
//					public void onMediaScannerConnected() {
//						Logs.v("onMediaScannerConnected >>> :" + path);
//					}
//				};
//				MediaScannerConnection connetion = new MediaScannerConnection(MusicPlayerList.this, client);
			
				MediaScannerConnection.scanFile(MusicPlayerList.this,
						new String[] { path }, null,
						new OnScanCompletedListener() {

							@Override
							public void onScanCompleted(String path, Uri uri) {
								Toast.makeText(MusicPlayerList.this,
										"onScanCompleted :" + path,
										Toast.LENGTH_SHORT).show();
							}
						});

			}
		});

	}

	/**
	 * 在4.4版本之前，我们可以使用发送广播的方式，强制刷新多媒体库
	 */
	public void scanFileDirMount(String path) {
		Logs.v("在4.4版本之前 扫描指定文件 >>> :" + path);
		sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,Uri.parse(path)));
	}

	/**
	 * 扫描音乐媒体库方法之二 扫描指定文件
	 * 
	 * @param ctx
	 * @param filePath
	 */
	public void scanFileAsync(Context ctx, String filePath) {
		Logs.v("扫描指定文件 >>> :" + filePath);
		Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		scanIntent.setData(Uri.fromFile(new File(filePath)));
		ctx.sendBroadcast(scanIntent);
	}

	/**
	 * 扫描音乐媒体库方法之二 扫描指定文件目录
	 * 
	 * @param ctx
	 * @param dir
	 */
	public void scanDirAsync(Context ctx, String dir) {
		Logs.v("扫描指定文件目录 >>> :" + dir);
		String ACTION_MEDIA_SCANNER_SCAN_DIR = "android.intent.action.MEDIA_SCANNER_SCAN_DIR";
		Intent scanIntent = new Intent(ACTION_MEDIA_SCANNER_SCAN_DIR);
		scanIntent.setData(Uri.fromFile(new File(dir)));
		ctx.sendBroadcast(scanIntent);
	}

	/**
	 * 删除媒体库音乐资源
	 */
	public void deleteMediaFile(String path) {
		Logs.v("删除媒体库音乐资源 >>> :" + path);
		if (!path.startsWith("file://")) {
			path = "file://" + path;
		}
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		getContentResolver().delete(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				MediaStore.Audio.Media.DATA + " = ?", new String[] { path });
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	class MyBaseAdapter extends BaseAdapter {
		private Context mContext;
		private List<Mp3Info> mList = new ArrayList<Mp3Info>();
		private LayoutInflater mInflater; // 把xml布局文件转换成View对象
		private int[] playerArray;

		public MyBaseAdapter(Context context) {
			mContext = context;
			mInflater = LayoutInflater.from(context);
		}

		/**
		 * 设置数据源，刷新适配器
		 * 
		 * @param list
		 */
		public void setData(List<Mp3Info> list) {
			mList = list;

			playerArray = new int[mList.size()];
			for (int i = 0; i < playerArray.length; i++) {
				playerArray[i] = 0;
			}
			notifyDataSetChanged();// 通知刷新适配器数据
		}

		public void setPlayerArray(int[] playerArray) {
			this.playerArray = playerArray;
			notifyDataSetChanged();// 通知刷新适配器数据
		}

		/**
		 * 返回容器中元素个数
		 */
		@Override
		public int getCount() {
			return mList.size();
		}

		/**
		 * 返回容器中指定位置的数据项
		 */
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		/**
		 * 返回容器中指定位置的ID
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		/**
		 * 返回表示行的view
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHodler = null;
			if (convertView == null) {
				// 将第一个参数指定的布局文件转换成View对象,如果第二参数ViewGroup不为空，则把view对象添加到该ViewGroup中
				convertView = mInflater.inflate(
						R.layout.item_player_listview_layout, null);

				viewHodler = new ViewHolder();
				viewHodler.iconImg = (ImageView) convertView
						.findViewById(R.id.icon_img);
				viewHodler.titleTxt = (TextView) convertView
						.findViewById(R.id.title_txt);
				viewHodler.contentTxt = (TextView) convertView
						.findViewById(R.id.content_txt);

				convertView.setTag(viewHodler);
			} else {
				viewHodler = (ViewHolder) convertView.getTag();
			}

			Mp3Info content = (Mp3Info) getItem(position);

			if (playerArray[position] == 1) {
				viewHodler.iconImg.setVisibility(View.VISIBLE);
			} else {
				viewHodler.iconImg.setVisibility(View.INVISIBLE);
			}
			viewHodler.titleTxt.setText(content.getTitle());
			viewHodler.contentTxt.setText(content.getArtist());

			return convertView;
		}

		class ViewHolder {
			ImageView iconImg;
			TextView titleTxt;
			TextView contentTxt;
		}

	}

	public List<Mp3Info> getMp3Infos() {
		Cursor cursor = getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();

		while (cursor.moveToNext()) {
			long id = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
			String title = cursor.getString((cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
			String artist = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
			String album = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // 专辑
			String displayName = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
			long albumId = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
			long duration = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
			long size = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
			String url = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
			int isMusic = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐
			if (isMusic != 0) { // 只把音乐添加到集合当中
				Mp3Info mp3Info = new Mp3Info();
				mp3Info.setId(id);
				mp3Info.setTitle(title);
				mp3Info.setArtist(artist);
				mp3Info.setAlbum(album);
				mp3Info.setDisplayName(displayName);
				mp3Info.setAlbumId(albumId);
				mp3Info.setDuration(duration);
				mp3Info.setSize(size);
				mp3Info.setUrl(url);

				mp3Infos.add(mp3Info);
			}
		}
		cursor.close();
		return mp3Infos;
	}

}
