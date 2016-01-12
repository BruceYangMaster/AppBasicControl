package com.scxh.android1503.util;

import java.util.HashMap;

import android.test.AndroidTestCase;

public class HttpTestAndroidCase extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testHashMap(){
		 //http://192.168.1.156/app/login?user=张三&psw=123456"
		
		HashMap<String, Object> map = new HashMap();
		map.put("user", "张三");
		map.put("psw","123456");
		map.put("sex","男");
		
		String url = "http://192.168.1.156/app/login";
		url = url + "?";  
		
		StringBuilder sb = new StringBuilder(url);
		for(String key: map.keySet()){
			Object values = map.get(key);
			sb.append(key);
			sb.append("=");
			sb.append(values);
			sb.append("&");
		}
		url = sb.substring(0, sb.length()-1);
		
		Logs.v("url :"+url);
	}
}
