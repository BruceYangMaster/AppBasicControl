package com.scxh.android1503.ui.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.scxh.android1503.R;

public class MyActionProvider extends ActionProvider {
	private Context context;
	public MyActionProvider(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add("添加").setIcon(R.drawable.wallet_add_bankcard_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
						return true;
					}
				});

		subMenu.add("删除").setIcon(R.drawable.actionbar_deletetext_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
						return true;
					}
				});
	}
	
	@Override
	public boolean hasSubMenu() {
		return true;
	}
}
