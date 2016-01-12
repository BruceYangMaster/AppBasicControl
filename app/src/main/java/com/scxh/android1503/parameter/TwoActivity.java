package com.scxh.android1503.parameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;

public class TwoActivity extends Activity {
	private TextView mShowMessageTxt;
	Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.parameter_two_layout);
		mShowMessageTxt = ((TextView) findViewById(R.id.lauce_two_txt));

		mIntent = getIntent();
		int type = mIntent.getIntExtra(Constances.TYPE, 1);
		switch (type) {
		case Constances.ParameterType.TYPE_BASEDATE:
			setParameterByBaseDateType(mIntent);
			break;
		case Constances.ParameterType.TYPE_BUNDLE:
			setParameterByBundle(mIntent);
			break;
		case Constances.ParameterType.TYPE_Serializable:
			setParameterBySerializable(mIntent);
			break;
		case Constances.ParameterType.TYPE_Parcelable:
			setParmeterByParcelable(mIntent);
			break;
		}

	}

	public void onStartFirstActivityClickListener(View v) {
		String p  = "回传值学习";
		mIntent.putExtra(Constances.PARAMETER, p);
		
		int resultCode = 2;
		setResult(resultCode, mIntent);
		finish();
	}

	public void setParameterByBundle(Intent intent) {
		String paramter = "";

		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			paramter = bundle.getString(Constances.USER_NAME);
		}

		mShowMessageTxt.setText("paramter :" + paramter);
	}

	public void setParameterByBaseDateType(Intent intent) {
		String paramter = intent.getStringExtra(Constances.PARAMETER);
		
		
		mShowMessageTxt.setText("paramter :" + paramter );
		
		
		
	}

	public void setParameterBySerializable(Intent intent) {
		User user = (User) intent.getSerializableExtra("USER");

		mShowMessageTxt.setText("用户名 :" + user.getUserName() + ", 密码 :"
				+ user.getPassWord() + ", 年龄 :" + user.getAge());

	}
	
	public void setParmeterByParcelable(Intent intent){
		Student student = intent.getParcelableExtra("STUDENT");
		mShowMessageTxt.setText("学生姓名 :" + student.getName()+ " 学号 :"+student.getNumber()+ " 年龄 :"+student.getAge());
	}
}
