package com.scxh.android1503.lauchemode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scxh.android1503.R;

public class FirstActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lauchmode_singleinstance_first_layout);
		
		((TextView)findViewById(R.id.lauce_first_txt)).setText("current task id: " + this.getTaskId());  
		
	}
	
	public void onStartTwoActivityClickLisenter(View v){
		
		
		
		startActivity(new Intent(this,TwoActivity.class));
	}
}
