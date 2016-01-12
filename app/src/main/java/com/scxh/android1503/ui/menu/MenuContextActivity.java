package com.scxh.android1503.ui.menu;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.scxh.android1503.R;

public class MenuContextActivity extends Activity {
	private ListView mListView;
	private String[] mData = { "选项菜单", "上下文菜单", "弹出菜单" };
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu_context_layout);
		mListView = (ListView) findViewById(R.id.menu_listview);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setOverFlowShowingAlways();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mData);

		mListView.setAdapter(adapter);

		registerForContextMenu(mListView);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				TaskStackBuilder.create(this)
						.addNextIntentWithParentStack(upIntent)
						.startActivities();
			} else {
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater menuInfalter = getMenuInflater();
		menuInfalter.inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		String itemName = "";
		switch (item.getItemId()) {
		case R.id.action_context_add:
			itemName = (String) adapter.getItem(info.position);
			Toast.makeText(this, "添加成功" + itemName, Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_context_delete:
			itemName = (String) adapter.getItem(info.position);
			Toast.makeText(this, "删除成功" + itemName, Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onContextItemSelected(item);
	}

	public void onShowPopMenuClickLisetner(View v) {

		PopupMenu popMenu = new PopupMenu(this, v);

		// MenuInflater menuInflater = getMenuInflater();
		// menuInflater.inflate(R.menu.pop_menu, popMenu.getMenu());

		popMenu.inflate(R.menu.pop_menu);

		popMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.action_pop_reply:
					Toast.makeText(MenuContextActivity.this, "回复成功",
							Toast.LENGTH_SHORT).show();
					break;
				case R.id.action_pop_send:
					Toast.makeText(MenuContextActivity.this, "转发成功",
							Toast.LENGTH_SHORT).show();
					break;
				}

				return false;
			}
		});

		popMenu.show();

	}

	private void setOverFlowShowingAlways() {
		ViewConfiguration config = new ViewConfiguration().get(this);
		Field menuKeyField = null;
		try {
			menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");

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
