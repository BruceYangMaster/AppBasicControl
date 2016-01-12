package com.scxh.android1503.parameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;

public class FirstActivity extends Activity {
	private TextView mShowMessageTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.parameter_first_layout);

		mShowMessageTxt = ((TextView) findViewById(R.id.lauce_first_txt));
		mShowMessageTxt.setText("参数传递");
	}

	public void onStartTwoActivityBaseDataTypeClickLisenter(View v) {
		String parameter = mShowMessageTxt.getText().toString();

		Intent intent = new Intent(this, TwoActivity.class);
		intent.putExtra(Constances.TYPE, Constances.ParameterType.TYPE_BASEDATE);
		intent.putExtra(Constances.PARAMETER, parameter);

//		startActivity(intent);
		
		
		int requestCode = 1;
		startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.i("tag", "onActivityResult >>>>>>>>>  requestCode :" + requestCode + " resultCode :"+resultCode);

		String paramter = data.getStringExtra(Constances.PARAMETER);
		Log.v("tag", "onActivityResult paramter  :" + paramter);

		mShowMessageTxt.setText((paramter == null ? "" : paramter));
		
	}
	
	public void onStartTwoActivityBundelClickLisenter(View v) {
		Bundle bundle = new Bundle();
		bundle.putString(Constances.USER_NAME, "张三");

		Intent intent = new Intent(this, TwoActivity.class);
		intent.putExtra(Constances.TYPE, Constances.ParameterType.TYPE_BUNDLE);
		intent.putExtras(bundle);

		startActivity(intent);
	}

	public void onStartTwoActivitySerializableClickLisenter(View v) {
		User user = new User();
		user.setUserName("王二");
		user.setPassWord("123456");
		user.setAge(20);

		Intent intent = new Intent(this, TwoActivity.class);
		intent.putExtra(Constances.TYPE,
				Constances.ParameterType.TYPE_Serializable);
		intent.putExtra("USER", user);

		startActivity(intent);
	}

	public void onStartTwoActivityParcelableClickLisenter(View v) {
		Student student = new Student();
		student.setName("小明");
		student.setNumber("1001");
		student.setAge(18);

		Intent intent = new Intent(this, TwoActivity.class);
		intent.putExtra(Constances.TYPE,
				Constances.ParameterType.TYPE_Parcelable);
		intent.putExtra("STUDENT", student);

		startActivity(intent);
	}

}
