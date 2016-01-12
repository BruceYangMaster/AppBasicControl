package com.scxh.android1503.store.preferce;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.scxh.android1503.R;

public class SharePreferenceActivity extends Activity implements
		OnClickListener {
	private static final String NAME = "com.scxh.android1503.SHARE_MESSAGE";
	private static final String MESSAGE = "message";
	private static final String USER_NAME = "username";
	private Button mAddBtn, mFindBtn;
	private SharedPreferences mSharePreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.preference_view_layout);

		mAddBtn = (Button) findViewById(R.id.preference_add_btn);
		mFindBtn = (Button) findViewById(R.id.preference_find_btn);

		mAddBtn.setOnClickListener(this);
		mFindBtn.setOnClickListener(this);

		/**实例化SharePreference*/
		mSharePreferences = getSharedPreferences(NAME,MODE_PRIVATE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.preference_add_btn:
			String message = "Android数据存储学习";
			
			/**添加数据到Sharepreference*/
			SharedPreferences.Editor editor = mSharePreferences.edit();
			editor.putString(MESSAGE, message);
			editor.putString(USER_NAME, "张三");
			editor.commit();
			Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.preference_find_btn:
			
			/**从Sharepreference获取数据*/
			String msg = mSharePreferences.getString(MESSAGE, "");
			String userName = mSharePreferences.getString(USER_NAME, "");
			Toast.makeText(this, "保储信息是 :"+msg+ " 用户名 ："+userName, Toast.LENGTH_SHORT).show();
			
			break;
		}
	}
}
