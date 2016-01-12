package com.scxh.android1503.ui.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MyMessageAdapterActivity;
import com.scxh.android1503.ui.adapter.gridview.GridViewActivity;
import com.scxh.android1503.ui.adapter.spinner.SpinnerActivity;

public class MyRadioTabActivity extends TabActivity implements OnCheckedChangeListener{
	private static final String TAB_ONE_TAG = "tab1";
	private static final String TAB_TWO_TAG = "tab2";
	private static final String TAB_THREE_TAG = "tab3";
	private RadioGroup mRadioGroup;
	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_radio_main_layout);

		mRadioGroup = (RadioGroup) findViewById(R.id.tab_radio_group);
		
		/**
		 * 实例化TabHost
		 */
		mTabHost = getTabHost();
		
		/**
		 * 构造一个页卡(TabSpec)：页卡项和页卡内容
		 * 
		 */
		TabSpec tabSpec1 = mTabHost.newTabSpec(TAB_ONE_TAG);
		tabSpec1.setIndicator("首页");
		tabSpec1.setContent(new Intent(this,GridViewActivity.class));
		
		TabSpec tabSpec2 = mTabHost.newTabSpec(TAB_TWO_TAG);
		tabSpec2.setIndicator("商家");
		tabSpec2.setContent(new Intent(this,MyMessageAdapterActivity.class));
		
		TabSpec tabSpec3 = mTabHost.newTabSpec(TAB_THREE_TAG);
		tabSpec3.setIndicator("设置");
		tabSpec3.setContent(new Intent(this,SpinnerActivity.class));
		
		/**
		 * 将页卡添加到TabHost (ViewGroup)
		 */
		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);
		mTabHost.addTab(tabSpec3);
		
		mRadioGroup.setOnCheckedChangeListener(this);
		
		/**
		 * TabHost页卡转换监听事件处理
		 */
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				Toast.makeText(MyRadioTabActivity.this, "tabId "+tabId, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * checkedId :RadioGroup子View RadioButon ID
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
		case R.id.tab_radio_one:
			mTabHost.setCurrentTabByTag(TAB_ONE_TAG);
			break;
		case R.id.tab_radio_two:
			mTabHost.setCurrentTabByTag(TAB_TWO_TAG);
			break;
		case R.id.tab_radio_three:
			mTabHost.setCurrentTabByTag(TAB_THREE_TAG);
			break;
		}
	}
}
