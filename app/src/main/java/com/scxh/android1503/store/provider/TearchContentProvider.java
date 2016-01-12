package com.scxh.android1503.store.provider;

import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;


public class TearchContentProvider extends ContentProvider {
	private static final String AUTHORITY = "com.scxh.android.TearchProvider";
	public static Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY);
	private SharedPreferences mSharePreferences;
	
	@Override
	public boolean onCreate() {
		mSharePreferences = getContext().getSharedPreferences("com.scxh.android1503.Tearch_PREFERECNES",
				getContext().MODE_PRIVATE);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String[] columnNames = new String[]{"username","password"};
		MatrixCursor cursor = new MatrixCursor(columnNames);
		
		Map<String,?> map = mSharePreferences.getAll();
		String[] array = new String[2];
		int i = 0;
		for(String key : map.keySet()){
			String value = (String) map.get(key);
			array[i++] = value;
		}
		cursor.addRow(array);
		
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		Editor editor = mSharePreferences.edit();

		for (String key : values.keySet()) {
			editor.putString(key, values.getAsString(key));
		}
		editor.commit();

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}
}
