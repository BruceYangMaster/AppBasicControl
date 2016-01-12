package com.scxh.android1503.sms;

import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

import com.scxh.android1503.store.db.DataColumns;
import com.scxh.android1503.util.Logs;

public class SmsContentObserver extends ContentObserver {
	private ContentResolver mContentResolver;
	private String[] projection = new String[] { DataColumns.SMS.ADDRESS,
			DataColumns.SMS.BODY, DataColumns.SMS.DATE, DataColumns.SMS.READ };

	private String selection = DataColumns.SMS.ADDRESS + " = ?";
	private String[] selectionArgs = new String[] { "110" };
	private Handler mHandler;

	public SmsContentObserver(Context context, Handler handler) {
		super(handler);
		mContentResolver = context.getContentResolver();
		mHandler = handler;
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);

		Logs.v("收到短消息     onchange >>>>>");

		Cursor cursor = mContentResolver.query(DataColumns.SMS.CONTENT_URI,projection, selection, selectionArgs, null);
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			String address = cursor.getString(cursor.getColumnIndex(DataColumns.SMS.ADDRESS));
			String body = cursor.getString(cursor.getColumnIndex(DataColumns.SMS.BODY));
			String date = cursor.getString(cursor.getColumnIndex(DataColumns.SMS.DATE));
			String read = cursor.getString(cursor.getColumnIndex(DataColumns.SMS.READ));
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("address", address);
			map.put("body", body);
			map.put("date", date);
			map.put("read", read);

			Message msg = Message.obtain();
			msg.what = 110;
			msg.obj = map;

			mHandler.sendMessage(msg);

		}
	}

	/**
	 * 过虑短消息交Handler处理 方法一 （不提倡）
	 */
	public void filterSmsOne() {
		Cursor cursor = mContentResolver.query(DataColumns.SMS.CONTENT_URI,
				projection, null, null, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				String address = cursor.getString(cursor
						.getColumnIndex(DataColumns.SMS.ADDRESS));
				String body = cursor.getString(cursor
						.getColumnIndex(DataColumns.SMS.BODY));
				String date = cursor.getString(cursor
						.getColumnIndex(DataColumns.SMS.DATE));
				String read = cursor.getString(cursor
						.getColumnIndex(DataColumns.SMS.READ));

				if (address.equals("110")) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("address", address);
					map.put("body", body);
					map.put("date", date);
					map.put("read", read);

					Message msg = Message.obtain();
					msg.what = 110;
					msg.obj = map;

					mHandler.sendMessage(msg);

					break;
				}
			}
		}
	}

}
