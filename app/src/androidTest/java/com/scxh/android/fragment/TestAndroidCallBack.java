package com.scxh.android.fragment;

import android.test.AndroidTestCase;

import com.scxh.android1503.R;
import com.scxh.android1503.pattern.callback.Boss;
import com.scxh.android1503.pattern.callback.Employer;
import com.scxh.android1503.util.Logs;

public class TestAndroidCallBack extends AndroidTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetStringRes(){
		String msg = getContext().getResources().getString(R.string.app_name);
		Logs.v(msg);
	}
	
	public void testCallBack(){
		Boss boss = new Boss();
		Employer employer = new Employer();

		employer.setCallInterfacel(boss);

		employer.doSome();
	}
	
	
	
}
