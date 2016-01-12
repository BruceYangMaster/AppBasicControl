package com.scxh.android1503.store.db;

import com.scxh.android1503.util.Logs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 封装对数据库操作，
 * 1.数据库和表创建
 * 2.数据库修改(升级)
 *
 */
public class UserDBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "myscxh_db.db";
	private static final int DB_VERSION = 1;
	private static final String PRIMARY_KEY = "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
	
	public UserDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String tearch_sql = "create table "+DataColumns.TearchTable.TABLE_NAME 
				+" ("
				+ DataColumns.TearchTable._ID +" "+PRIMARY_KEY+", "
				+ DataColumns.TearchTable.COLUMN_NAME_NAME +" varchar(50), "
				+ DataColumns.TearchTable.COLUMN_NAME_SEX +" varchar(2)" 
				+")";
		
		String user_sql = "create table " + DataColumns.UserTable.TABLE_NAME
				+ " (" + DataColumns.UserTable._ID + "  " + PRIMARY_KEY
				+ " , " + DataColumns.UserTable.COLUMN_NAME_NAME
				+ " varchar(50) ,"
				+ DataColumns.UserTable.COLUMN_NAME_PASSWORD
				+ " varchar(10) )";
		
		db.execSQL(tearch_sql);
		db.execSQL(user_sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
