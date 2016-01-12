package com.scxh.android1503.ui.menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.scxh.android1503.R;

public class MenuOptionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_option_layout);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.option_menu, menu);
		
//		menu.add(1, 11, 1, "添加");
//		menu.add(1, 12, 2, "删除");
//		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_search:
			break;
		case R.id.action_delete:
			break;
		case R.id.action_statrt_login:
			Intent intent = new Intent();
//			intent.setComponent(new ComponentName(this, EditTextActivity.class));
			intent.setAction("com.scxh.android1503.ACTION_EDIT_TEXT");
			
			startActivity(intent);
			
//			Intent intent = new Intent(this,EditTextActivity.class);
//			startActivity(intent);
			
			break;
		case R.id.action_call_phone:
			intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:110"));
			startActivity(intent);
			
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
}
