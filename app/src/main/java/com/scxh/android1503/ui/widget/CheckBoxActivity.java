package com.scxh.android1503.ui.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.scxh.android1503.MainActivity;
import com.scxh.android1503.R;

public class CheckBoxActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_check_layout);

	}

	public void onCheckboxClicked(View v) {
		CheckBox checkBox = (CheckBox) v;

		switch (v.getId()) {
		case R.id.readbook_checkbox:
			if (checkBox.isChecked()) {
				Toast.makeText(this, "读书 选中", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "读书 取消选中", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.sport_checkbox:
			if (checkBox.isChecked()) {
				Toast.makeText(this, "运动 选中", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "运动 取消选中", Toast.LENGTH_SHORT).show();

			}
			break;
		case R.id.game_checkbox:
			if (checkBox.isChecked()) {
				Toast.makeText(this, "游戏 选中", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "游戏 取消选中", Toast.LENGTH_SHORT).show();

			}
			break;
		}

		
		
		
	}
	public void onMyCheckboxClicked(View v) {
		CheckBox checkBox = (CheckBox) v;

		switch (v.getId()) {
		case R.id.my_readbook_checkbox:
			if (checkBox.isChecked()) {
				Toast.makeText(this, "my_读书 选中", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "my_读书 取消选中", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.my_sport_checkbox:
			if (checkBox.isChecked()) {
				Toast.makeText(this, "my_运动 选中", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "my_运动 取消选中", Toast.LENGTH_SHORT).show();

			}
			break;
		case R.id.my_game_checkbox:
			if (checkBox.isChecked()) {
				Toast.makeText(this, "my_游戏 选中", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "my_游戏 取消选中", Toast.LENGTH_SHORT).show();

			}
			break;
		}
	}
	
	public void onMainActivityLisetner(View v){
		startActivity(new Intent(this,MainActivity.class));
	}
}
