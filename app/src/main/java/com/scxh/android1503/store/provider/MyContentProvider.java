package com.scxh.android1503.store.provider;

import com.scxh.android1503.store.db.DataColumns;
import com.scxh.android1503.util.Logs;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * ContentProvider 内容提供者 四大组件之一 ContentReserver Uri :
 * content://xxxxxxxxxxxxx/path 
 * \scheme /\ Authority /\ path/
 */
public class MyContentProvider extends ContentProvider {
	private static final String AUTHORITY = "com.scxh.android1503.userprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	private SQLiteDatabase db;

	public class DBSQLiteOperHelper extends SQLiteOpenHelper {
		private final static String DB_NAME = "scxh_user.db";
		private final static int DB_VERSION = 1;

		private static final String PRIMARY_KEY = "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";

		public DBSQLiteOperHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table " + DataColumns.UserTable.TABLE_NAME
					+ " (" + DataColumns.UserTable._ID + "  " + PRIMARY_KEY
					+ " , " + DataColumns.UserTable.COLUMN_NAME_NAME
					+ " varchar(50) ,"
					+ DataColumns.UserTable.COLUMN_NAME_PASSWORD
					+ " varchar(10) )";
			
			Logs.v("SQL : "+sql);
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

	@Override
	public boolean onCreate() {
		DBSQLiteOperHelper helper = new DBSQLiteOperHelper(getContext());
		db = helper.getReadableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return db.query(DataColumns.UserTable.TABLE_NAME, projection,
				selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long id = db.insert(DataColumns.UserTable.TABLE_NAME, null, values);
		return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
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
