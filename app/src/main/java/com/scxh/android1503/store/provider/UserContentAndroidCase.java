package com.scxh.android1503.store.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.scxh.android1503.store.db.DataColumns;
import com.scxh.android1503.store.db.DataColumns.UserTable;
import com.scxh.android1503.util.Logs;

public class UserContentAndroidCase extends AndroidTestCase {
	private ContentResolver contentResolver;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		contentResolver = getContext().getContentResolver();
	}
	
	public void findUsers(){
		Cursor cursor = contentResolver.query(UserContentProvider.CONTENT_URI_USER, null, null, null, null);
		if (cursor != null && cursor.getCount()>0){
			Logs.i("用户名        密码");
			while(cursor.moveToNext()){
				String displayName = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_NAME));
				String passWord = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_PASSWORD));
				
				Logs.v(displayName+"       "+passWord);
				
			}
		}
	}
	public void findTearch(){
		Cursor cursor = contentResolver.query(UserContentProvider.CONTENT_URI_TEARCH, null, null, null, null);
		if (cursor != null && cursor.getCount()>0){
			Logs.i("用户名        性别");
			while(cursor.moveToNext()){
				String displayName = cursor.getString(cursor.getColumnIndex(DataColumns.TearchTable.COLUMN_NAME_NAME));
				String passWord = cursor.getString(cursor.getColumnIndex(DataColumns.TearchTable.COLUMN_NAME_SEX));
				
				Logs.v(displayName+"       "+passWord);
				
			}
		}
	}
	public void insertTearch(){
		ContentValues values = new ContentValues();
		values.put(DataColumns.TearchTable.COLUMN_NAME_NAME, "张三");
		values.put(DataColumns.TearchTable.COLUMN_NAME_SEX, "男");
		
		Uri uri = contentResolver.insert(UserContentProvider.CONTENT_URI_TEARCH, values);
		Logs.d(uri.toString());
	}
	
	public void insertUser(){
		ContentValues values = new ContentValues();
		values.put(UserTable.COLUMN_NAME_NAME, "小明");
		values.put(UserTable.COLUMN_NAME_PASSWORD, "123456");
		
		Uri uri = contentResolver.insert(UserContentProvider.CONTENT_URI_USER, values);
		Logs.d(uri.toString());
	}
}
