package com.scxh.android1503.pattern.callback;

import com.scxh.android1503.util.Logs;

public class Employer {

	public CallInterfacel mCallInterfacel;
	
	public void setCallInterfacel(CallInterfacel callInterfacel){
		this.mCallInterfacel = callInterfacel;
	}
	
	public void doSome(){
		for(int i = 0; i < 10; i++){
			Logs.v("雇员做事 "+i);
		}
		Logs.v("雇员做完事情通知老板 ");
		mCallInterfacel.call("事情完成"); //事情完成通知老板
		
	}
}
