package com.scxh.android1503.ui.tab;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MyMessageAdapterActivity;
import com.scxh.android1503.ui.adapter.gridview.GridViewActivity;
import com.scxh.android1503.ui.adapter.spinner.SpinnerActivity;

public class MyTabActivity extends TabActivity implements OnTabChangeListener{
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_main_layout);

		context = this;
		
		TabHost tabHost = getTabHost();
		
		TabSpec tabSpec1 = tabHost.newTabSpec("tab1");
	
		tabSpec1.setIndicator(createIndictorView("首页"));
		tabSpec1.setContent(new Intent(this,GridViewActivity.class));
		
		TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
		tabSpec2.setIndicator(createIndictorView("商家"));
		tabSpec2.setContent(new Intent(this,MyMessageAdapterActivity.class));
		
		TabSpec tabSpec3 = tabHost.newTabSpec("tab3");
		tabSpec3.setIndicator(createIndictorView("设置"));
		tabSpec3.setContent(new Intent(this,SpinnerActivity.class));
		
		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);
		
		tabHost.setOnTabChangedListener(this);
	}
	
	public View createIndictorView(String tab){
		View v = LayoutInflater.from(context).inflate(R.layout.tab_main_item_layout, null);
		TextView tabTxt = (TextView) v.findViewById(R.id.tab_indector_txt);
		tabTxt.setText(tab);
		return v;
	}

	@Override
	public void onTabChanged(String tabId) {
		Toast.makeText(context, "tabId "+tabId, Toast.LENGTH_SHORT).show();
	}
}
