package com.scxh.android1503.ui.widget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.scxh.android1503.R;

public class RadioActivity extends Activity {
	private RadioGroup mSexRadioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.widget_radio_layout);

		mSexRadioGroup = (RadioGroup) findViewById(R.id.sex_radio_group);

		((RadioButton)mSexRadioGroup.getChildAt(0)).toggle();
		
		mSexRadioGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.man_radio:
							RadioButton manRadioBtn = (RadioButton) group.getChildAt(0);
							
							System.out.println("onCheckedChanged >>>>>>>>>> "+manRadioBtn.getText());
							
							Toast.makeText(RadioActivity.this, manRadioBtn.getText(), Toast.LENGTH_SHORT).show();
							
							break;
						case R.id.woman_radio:
							RadioButton womanRadioBtn = (RadioButton) findViewById(checkedId);
							System.out.println("onCheckedChanged >>>>>>>>>> "+womanRadioBtn.getText());
							
							Toast.makeText(RadioActivity.this, womanRadioBtn.getText(), Toast.LENGTH_SHORT).show();
							
							break;
						}

					}
				});

	}
}
