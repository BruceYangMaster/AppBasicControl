package com.scxh.android1503.fragment.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scxh.android1503.R;
import com.scxh.android1503.fragment.viewpager.ImageLoaderMainActivity;
import com.scxh.android1503.ui.custom.TitleBarView;

public class TabMainActivity extends FragmentActivity {
    private RadioGroup mRadioGroup;
    private FragmentTabHost mTabHost;
    private TitleBarView mTitleBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_main_layout);
        mTitleBarView = (TitleBarView) findViewById(R.id.my_titleview);
        mRadioGroup = (RadioGroup) findViewById(R.id.tab_radio_group);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("首页"), TabOneFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("搜索"), TabTwoFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("设置"),TabOneFragment.class, null);

        mTitleBarView.registerOnClickListener(new TitleBarView.OnTitleBarClickListener() {
            @Override
            public void onTitleBarClick(View v) {
                switch (v.getId()) {
                    case R.id.title_left_txt:
                        finish();
                        break;
                    case R.id.title_right_txt:
                        startActivity(new Intent(TabMainActivity.this, ImageLoaderMainActivity.class));
                        break;
                }
            }
        });


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_radio_one:
                        mTabHost.setCurrentTabByTag("tab1");
                        break;
                    case R.id.tab_radio_two:
                        mTabHost.setCurrentTabByTag("tab2");
                        break;
                    case R.id.tab_radio_three:
                        mTabHost.setCurrentTabByTag("tab3");
                        break;
                }

            }
        });
        ((RadioButton) mRadioGroup.getChildAt(0)).toggle(); //默认选中第一项
    }
}
