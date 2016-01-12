package com.scxh.android1503.ui.menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.widget.EditTextActivity;

import java.lang.reflect.Field;

public class MenuActionBarActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_option_layout);

//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		
		setOverFlowShowingAlways();
	}

	public void onNextClickLisentner(View v) {
		startActivity(new Intent(this, MenuContextActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.actionbar_menu, menu);
		
//		MenuItem menuItem = menu.findItem(R.id.action_share);
//		ShareActionProvider provider = (ShareActionProvider) menuItem.getActionProvider();
//		provider.setShareIntent(getShareDefaut());
		
		return super.onCreateOptionsMenu(menu);
	}

	public Intent getShareDefaut(){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_delete:
			Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_statrt_login:
			Intent intent = new Intent(this, EditTextActivity.class);
			startActivity(intent);
			break;
		case R.id.action_call_phone:
			intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:110"));
			startActivity(intent);
			break;
		case android.R.id.home:
//			finish();
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if(NavUtils.shouldUpRecreateTask(this, upIntent)){
				TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
			}else{
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * ActionBar动作条菜单什么时候显示?
	 * 1.跟手机硬件有关,如果手机硬件有菜单Menu键，那么ActionBar作为选项菜单形式显示.
	 * 如下方式实现手机硬件有菜单Menu键也作为ActionBar动作条形式显示
	 */
	private void setOverFlowShowingAlways() {
		ViewConfiguration config = new ViewConfiguration().get(this);
		Field menuKeyField = null;
		try {
			menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
