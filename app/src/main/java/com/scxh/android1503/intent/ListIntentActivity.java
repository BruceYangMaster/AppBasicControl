package com.scxh.android1503.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;

public class ListIntentActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.intent_a_layout);
	}

	public void onActionViewOneClickLisenter(View v) {
		// Intent intent = new Intent(this,DetailIntentActivity.class);

		Intent intent = new Intent();
		ComponentName component = new ComponentName(this,DetailIntentActivity.class);
		intent.setComponent(component);

		startActivity(intent);
	}

	public void onActionViewTwoClickLisenter(View v) {
		Intent intent = new Intent();
		intent.setAction(Constances.ACTION_INTENT.INTENT_DETAIL);
		startActivity(intent);
	}
	
	public void onCallPhoneClickLisenter(View v){
		Uri data = Uri.parse("tel:18564564578");
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_DIAL);
		intent.setData(data);
		startActivity(intent);
	}
}
