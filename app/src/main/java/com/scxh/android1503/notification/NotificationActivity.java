package com.scxh.android1503.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.gridview.GridViewActivity;
import com.scxh.android1503.ui.adapter.spinner.SpinnerActivity;

public class NotificationActivity extends Activity implements OnClickListener {
	private Button mSendNotifiyBtn, mUpdateNotifiyBtn, mClearNotifyBtn,
			mNavigationBtn;
	private Button mDownLoadBtn, mDownLoadIndeterminBtn, mBigViewBtn,
			mCustomBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_main_layout);

		mSendNotifiyBtn = (Button) findViewById(R.id.notification_send_btn);
		mUpdateNotifiyBtn = (Button) findViewById(R.id.notification_update_btn);
		mClearNotifyBtn = (Button) findViewById(R.id.notification_clear_btn);
		mNavigationBtn = (Button) findViewById(R.id.notification_navigation_btn);
		mDownLoadBtn = (Button) findViewById(R.id.notification_download_btn);
		mDownLoadIndeterminBtn = (Button) findViewById(R.id.notification_download_indetermin_btn);
		mBigViewBtn = (Button) findViewById(R.id.notification_bigview_btn);
		mCustomBtn = (Button) findViewById(R.id.notification_my_btn);
		mSendNotifiyBtn.setOnClickListener(this);
		mUpdateNotifiyBtn.setOnClickListener(this);
		mClearNotifyBtn.setOnClickListener(this);
		mNavigationBtn.setOnClickListener(this);
		mDownLoadBtn.setOnClickListener(this);
		mDownLoadIndeterminBtn.setOnClickListener(this);
		mBigViewBtn.setOnClickListener(this);
		mCustomBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notification_send_btn:
			sendNotification();
			break;
		case R.id.notification_update_btn:
			updateNotifiction();
			break;
		case R.id.notification_clear_btn:
			clearNotification();
			break;
		case R.id.notification_navigation_btn:
			sendNavigationNotification();
			break;
		case R.id.notification_download_btn:
			downLoadNotification();
			break;
		case R.id.notification_download_indetermin_btn:
			downLoadindetermin();
			break;
		case R.id.notification_bigview_btn:
			bigViewNotification();
			break;
		case R.id.notification_my_btn:
			myNotification();
			break;
		}

	}

	/**
	 * 新建发送通知
	 */
	public void sendNotification() {
		/** 构造通知notification界面 */
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle(getString(R.string.notifiy_title));
		builder.setContentText(getString(R.string.notifiy_content));
		builder.setTicker(getString(R.string.notifiy_content));
		builder.setAutoCancel(true);

		/** 通知行为 */
		Intent intents = new Intent(this, SpinnerActivity.class);

		/** 设置Notification启动的特定Activity在一个新的任务栈中 */
		intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);

		PendingIntent pendingIntnIntent = PendingIntent.getActivity(this, 0,
				intents, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pendingIntnIntent);

		/** 发送通知Notification */
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int notificationId = 11;
		Notification notification = builder.build();
		manager.notify(notificationId, notification);
	}

	/**
	 * 更新通知
	 */
	public void updateNotifiction() {
		/** 构造通知notification界面 */
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.m3);
		builder.setContentTitle("相亲");
		builder.setContentText("成都相亲会，在软件园举行");
		builder.setTicker("成都相亲会，在软件园举行");
		builder.setAutoCancel(true);

		Notification notification = builder.build();
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int notificationId = 12;
		manager.notify(notificationId, notification);
	}

	/**
	 * 清除通知
	 */
	public void clearNotification() {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int notificationId = 12;
		// manager.cancel(notificationId);
		manager.cancelAll();
	}

	/**
	 * 发送导航通知
	 */
	public void sendNavigationNotification() {
		/** 构造通知notification界面 */
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle(getString(R.string.notifiy_title));
		builder.setContentText("导航通知" + getString(R.string.notifiy_content));
		builder.setTicker("导航通知" + getString(R.string.notifiy_content));
		builder.setAutoCancel(true);

		/** 通知行为 构造任务返回栈 */
		Intent intents = new Intent(this, GridViewActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(GridViewActivity.class);
		stackBuilder.addNextIntent(intents);

		PendingIntent pendingIntnIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);

		builder.setContentIntent(pendingIntnIntent);

		/** 发送通知Notification */
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int notificationId = 131;
		Notification notification = builder.build();
		manager.notify(notificationId, notification);
	}

	/**
	 * 模拟下载,通知显示下载进度.
	 */
	public void downLoadNotification() {
		final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		final int notificationId = 111;

		final NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.m8);
		builder.setContentTitle("下载文件");
		builder.setContentText("文件下载开始");
		builder.setTicker("文件下载开始");
		builder.setAutoCancel(true);

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					builder.setProgress(100, i, false); // 设置加载进度确定模式
					builder.setContentText("文件下载中...");
					manager.notify(notificationId, builder.build());

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				builder.setContentText("文件下载完成");
				builder.setProgress(0, 0, false);
				manager.notify(notificationId, builder.build());

			}
		}).start();
	}

	public void downLoadindetermin() {
		final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		final int notificationId = 112;

		final NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.m8);
		builder.setContentTitle("下载文件");
		builder.setContentText("文件下载开始");
		builder.setTicker("文件下载开始");
		builder.setAutoCancel(true);

		builder.setProgress(0, 0, true); // 设置进度为不确定模式
		builder.setContentText("文件下载中...");
		manager.notify(notificationId, builder.build());
	}

	public void bigViewNotification() {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		/** 构造通知notification界面 */
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("起床提醒");
		builder.setContentText("时间到快起床了");
		builder.setTicker("时间到快起床了");
		builder.setAutoCancel(true);
		builder.setStyle(new NotificationCompat.BigTextStyle()
				.bigText("时间到快起床了"));

		/** 播放动作 1表示播放，0表示停止 */
		Intent playerIntent = new Intent(this, MusicService.class);
		/** 通过Notification启动服务必须设置action属性 */
		playerIntent.setAction(MusicService.ACTION_PLAYER);
		playerIntent.putExtra("PLAYSER_STATE", 1);
		playerIntent
				.putExtra(
						"MUSIC_PATH",
						"file://"
								+ Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/jinsha.mp3");
		PendingIntent playerPendingIntent = PendingIntent.getService(this, 0,
				playerIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		/** 暂停动作 */
		Intent pauseintents = new Intent(this, MusicService.class);
		pauseintents.setAction(MusicService.ACTION_PAUSE);
		pauseintents.putExtra("PLAYSER_STATE", 0);
		pauseintents
				.putExtra(
						"MUSIC_PATH",
						"file://"
								+ Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/jinsha.mp3");

		PendingIntent pausePIntent = PendingIntent.getService(this, 0,
				pauseintents, PendingIntent.FLAG_UPDATE_CURRENT);

		builder.addAction(R.drawable.player_play, "播放", playerPendingIntent);
		builder.addAction(R.drawable.player_pause, "停止", pausePIntent);

		manager.notify(121, builder.build());

	}

	/**
	 * 自定义通知界面
	 */
	public void myNotification() {
		/** 播放动作 1表示播放，0表示停止 */
		Intent playerIntent = new Intent(this, MusicService.class);
		/** 通过Notification启动服务必须设置action属性 */
		playerIntent.setAction(MusicService.ACTION_PLAYER);
		playerIntent.putExtra("PLAYSER_STATE", 1);
		playerIntent
				.putExtra(
						"MUSIC_PATH",
						"file://"
								+ Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/jinsha.mp3");
		PendingIntent playerPendingIntent = PendingIntent.getService(this, 0,
				playerIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		/** 暂停动作 */
		Intent pauseintents = new Intent(this, MusicService.class);
		pauseintents.setAction(MusicService.ACTION_PAUSE);
		pauseintents.putExtra("PLAYSER_STATE", 0);
		pauseintents
				.putExtra(
						"MUSIC_PATH",
						"file://"
								+ Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/jinsha.mp3");
		PendingIntent pausePIntent = PendingIntent.getService(this, 0,
				pauseintents, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.ic_launcher); // 自定义通知必须加上icon
		builder.setTicker("时间到快起床了");

		RemoteViews view = new RemoteViews(getPackageName(),
				R.layout.notification_music_layout);
		builder.setContent(view);
		view.setOnClickPendingIntent(R.id.player_pause_img, playerPendingIntent);
		view.setOnClickPendingIntent(R.id.player_next_img, pausePIntent);

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.notify(321, builder.build());
	}
}
