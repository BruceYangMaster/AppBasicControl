package com.scxh.android1503.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.widget.CheckBoxActivity;

public class SaveStateActivity extends Activity {
	private EditText mContentEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.event_savestate_layout);

		Log.v("SaveStateActivity", "onCreate >>>>>>>>>>>>  ");

		mContentEdit = (EditText) findViewById(R.id.event_savestate_content_edit);

		if (savedInstanceState != null) {
			String content = savedInstanceState.getString("CONENT");
			mContentEdit.setText(content);
			Log.i("SaveStateActivity", "onCreate >>>>>>>>>>>>content  "
					+ content);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.v("SaveStateActivity", "onSaveInstanceState >>>>>>>>>>>>  ");

		String content = mContentEdit.getText().toString();
		outState.putString("CONENT", content);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		Log.e("SaveStateActivity", "onRestoreInstanceState >>>>>>>>>>>>  ");

		if (savedInstanceState != null) {
			String content = savedInstanceState.getString("CONENT");
			mContentEdit.setText(content);
			Log.i("SaveStateActivity", "onCreate >>>>>>>>>>>>content  "
					+ content);
		}
	}

	public void onStartSaveStateActivityListener(View v) {
		startActivity(new Intent(this, SaveStateActivity.class));
	}

	public void onCheckBoxActivityListener(View v) {
		startActivity(new Intent(this, CheckBoxActivity.class));
	}

}
