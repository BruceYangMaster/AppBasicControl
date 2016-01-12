package com.scxh.android1503.store.provider;

import java.util.Map;

import com.scxh.android1503.store.db.DataColumns;
import com.scxh.android1503.util.Logs;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;

public class ContactProviderAndroidCase extends AndroidTestCase {
	ContentResolver contentResolver;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		contentResolver = getContext().getContentResolver();
	}
	
	public void testFindContact(){
//		String uriString = "content://com.android.contacts/contacts";
//		Uri uri = Uri.parse(uriString);
		
		Uri uri = ContactsContract.Contacts.CONTENT_URI;   // content://com.android.contacts/contacts
		
		ContentResolver contentResolver = getContext().getContentResolver();
		
		Cursor c = contentResolver.query(uri, null, null, null, null);
		
		if(c != null && c.getCount()>0){
			Logs.i("显示名 ");
			while(c.moveToNext()){
				
				String displayName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				Logs.v(displayName);
				
			}
		}
	}
	
	public void testInsertUser(){
		addUser("李四","abcd");
		findUser();
	}
	
	public void addUser(String name,String password){
		ContentValues values = new ContentValues();
		values.put(DataColumns.UserTable.COLUMN_NAME_NAME, name);
		values.put(DataColumns.UserTable.COLUMN_NAME_PASSWORD, password);
		
		Uri uri = contentResolver.insert(MyContentProvider.CONTENT_URI, values);
		Logs.v("uri :"+uri.toString());
	}
	
	public void findUser(){
		Cursor c = contentResolver.query(MyContentProvider.CONTENT_URI, null, null, null, null);
		if (c != null && c.getCount()>0){
			Logs.i("姓名        密码");
			while(c.moveToNext()){
				
				String displayName = c.getString(c.getColumnIndex(DataColumns.UserTable.COLUMN_NAME_NAME));
				String passWord = c.getString(c.getColumnIndex(DataColumns.UserTable.COLUMN_NAME_PASSWORD));
				
				Logs.v(displayName+"       "+passWord);
				
			}
		}
	}
	
	
	public void testKeySet(){
		ContentValues values = new ContentValues();
		values.put("name","张三");
		values.put("password", "123456");
		
		for(String key : values.keySet()){
			String name = values.getAsString(key);
			Logs.v("key :"+key+ "  name :"+name);
		}
	}
	
	public void testCursor(){
		String[] columnNames = new String[]{"username","password"};
		MatrixCursor cursor = new MatrixCursor(columnNames);
		cursor.addRow(new String[]{"张三","123456"});
		cursor.addRow(new String[]{"李四","abcd"});
		
		
		
		if (cursor != null && cursor.getCount()>0){
			Logs.i("username        password");
			while(cursor.moveToNext()){
				String displayName = cursor.getString(cursor.getColumnIndex("username"));
				String passWord = cursor.getString(cursor.getColumnIndex("password"));
				Logs.v(displayName+"       "+passWord);
			}
		}
		
	}
	
	public void testMap(){
		SharedPreferences sharedPreferences = getContext().getSharedPreferences("testscxh",getContext().MODE_PRIVATE);
		sharedPreferences.edit().putString("username", "张三").putString("password", "123456").commit();
		
		Map<String,?> map = sharedPreferences.getAll();
		Logs.i("key      value");
		for(String key : map.keySet()){
			String value = (String) map.get(key);
			Logs.v(key+  "    "+ value);
			
		}
	}
	
	
	public void testTearchProvider(){
		ContentValues values = new ContentValues();
		values.put("username","张三");
		values.put("password", "123456");
		contentResolver.insert(TearchContentProvider.CONTENT_URI, values);
	}
	
	public void testFindTearch(){
		Cursor cursor = contentResolver.query(TearchContentProvider.CONTENT_URI, null, null, null, null);
		if (cursor != null && cursor.getCount()>0){
			Logs.i("username        password");
			while(cursor.moveToNext()){
				
				String displayName = cursor.getString(cursor.getColumnIndex("username"));
				String passWord = cursor.getString(cursor.getColumnIndex("password"));
				
				Logs.v(displayName+"       "+passWord);
				
			}
		}
	}
	
}
