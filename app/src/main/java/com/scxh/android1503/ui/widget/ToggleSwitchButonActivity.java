package com.scxh.android1503.ui.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.scxh.android1503.R;

public class ToggleSwitchButonActivity extends Activity {
	private Switch mSwitchBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_togleswitch_layout);
		
		mSwitchBtn = (Switch) findViewById(R.id.widget_switch1);
		
		mSwitchBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					Toast.makeText(ToggleSwitchButonActivity.this, "Switch 已打开", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(ToggleSwitchButonActivity.this, "Switch 已关闭", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}

	public void onTogglesClicked(View v) {
		boolean isChecked = ((ToggleButton) v).isChecked();

		if (isChecked) {
			Toast.makeText(this, "ToggleButton 已打开", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "ToggleButton 已关闭", Toast.LENGTH_SHORT).show();
		}
	}

}
