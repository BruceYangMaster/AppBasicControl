package com.scxh.android1503.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.test.AndroidTestCase;

import com.scxh.android1503.store.db.DataColumns;
import com.scxh.android1503.util.Logs;

public class SmsAndroidCase extends AndroidTestCase {
	private Context mContext;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mContext = getContext();
	}
	
	public void searchSms(){
		Uri uri = Uri.parse("content://sms");
		Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
		
		if(cursor.getCount() > 0){
			Logs.v("地址"+"    "+ "内容");
			while(cursor.moveToNext()){
				String address = cursor.getString(cursor.getColumnIndex(DataColumns.SMS.ADDRESS));
				String body = cursor.getString(cursor.getColumnIndex(DataColumns.SMS.BODY));
				
				Logs.i(address + "     "+ body);
			}
		}
	}
	
	public void testSmsContentobserver(){
//		Handler handler = new Handler();
//		SmsContentObserver smsObserver = new SmsContentObserver(chandler);
//		mContext.getContentResolver().registerContentObserver(DataColumns.SMS.CONTENT_URI, true, smsObserver);
//		
		
		
	}
}
