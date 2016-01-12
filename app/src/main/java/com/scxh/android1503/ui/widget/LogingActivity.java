package com.scxh.android1503.ui.widget;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android1503.R;

public class LogingActivity extends Activity {
	private EditText mUserNameEdit,mPassWordEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login_layout);
		
		mUserNameEdit = (EditText) findViewById(R.id.login_username_edit);
		mPassWordEdit = (EditText) findViewById(R.id.login_password_edit);
		
		
		mUserNameEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("tag", "onTextChanged s:"+s+ " start :"+start+ " , before :"+before+ " count :"+count);
				
				Toast.makeText(LogingActivity.this, "onTextChanged s "+s, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Log.v("tag", "beforeTextChanged s:"+s+ " start :"+start+ " , after :"+after+ " count :"+count);
				Toast.makeText(LogingActivity.this, "beforeTextChanged "+s, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Log.d("tag", "afterTextChanged s:"+s);
				if(!(s.toString()).startsWith("a")){
					mUserNameEdit.setError("输入a开头的用户名");
				}
				
				
				Toast.makeText(LogingActivity.this, "afterTextChanged "+s, Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
	}

	public void onBackClickListener(View v) {
		finish();
	}

}
