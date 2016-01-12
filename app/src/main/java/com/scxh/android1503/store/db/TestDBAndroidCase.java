package com.scxh.android1503.store.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.scxh.android1503.util.Logs;

public class TestDBAndroidCase extends AndroidTestCase {
	private Context mContext;
	private SQLiteDatabase db;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		Logs.v("setUp ");
		
		mContext = getContext();
		MySQLiteDBHelper dbHelper = new MySQLiteDBHelper(mContext);
		db = dbHelper.getReadableDatabase();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
//		Logs.v("tearDown ");
		
		db.close();
	}
	
	public void testCreateDB(){
		SQLiteDatabase  db = mContext.openOrCreateDatabase("scxh1503.db",mContext.MODE_PRIVATE,null);
		db.execSQL("create table student (id int,name varchar(50),age int)");
		db.execSQL("insert into student values(1,'小明',18)");
	}
	public void testDBHelper(){
		MySQLiteDBHelper dbHelper = new MySQLiteDBHelper(mContext);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
	}
	
	public void testAddUser(){
		ContentValues values = new ContentValues();
		values.put("id", "100");
		values.put("username", "张三");
		values.put("number", "1001");
		
		db.insert("user", null, values);
	}
	
	
	public void testUpdateUser(){
		ContentValues values = new ContentValues();
		values.put("password", "123456");
		
		String whereClause = "id = ?";
		String[] whereArgs = new String[]{"100"};
		
		db.update("user", values, whereClause, whereArgs);
	}
	
	public void addUserBySql(){
		db.execSQL("insert into user values(101,'李四','abcd','1002')");
	}
	
	public void deleteUser(){
		String whereClause = "username = ?";
		String[] whereArgs = new String[]{"张三"};
		db.delete("user", whereClause, whereArgs);
	}
	
	public void findUser(){
		String[] columns = new String[]{"username","password"};
		String selection = "id = ? ";
		String[] selectionArgs = new String[]{"101"};
		Cursor  cursor = db.query("user",columns , selection, selectionArgs, null, null, "id asc");
		
		if(cursor.getCount() > 0){
			Logs.v("用户名"+"    "+ "密码");
			while(cursor.moveToNext()){
				String userName = cursor.getString(cursor.getColumnIndex("username"));
				String passWord = cursor.getString(cursor.getColumnIndex("password"));
				
				Logs.i(userName + "     "+ passWord);
			}
		}
		
	}
	
}
