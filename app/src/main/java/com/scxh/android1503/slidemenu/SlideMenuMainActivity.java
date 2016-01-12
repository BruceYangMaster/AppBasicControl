package com.scxh.android1503.slidemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.custom.CustomMainActivity;
import com.scxh.android1503.ui.custom.TitleBarView;
import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingActivity;

public class SlideMenuMainActivity extends SlidingActivity implements MenuFragment.MenuFragmentListener{
    private TitleBarView mTitleBarView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slide_menu_main_layout);
        mTitleBarView = (TitleBarView) findViewById(R.id.sliding_menu_title_bar);
        mTitleBarView.registerOnClickListener(new TitleBarView.OnTitleBarClickListener() {
            @Override
            public void onTitleBarClick(View v) {
                switch (v.getId()) {
                    case R.id.title_left_txt:
                        toggle(); //  TODO:   开关方法 控件slidingMenu 打开关闭
                        break;
                }
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.sliding_container_layout,YuleFragment.newInstance()).commit();

        /**侧滑菜单界面*/
        setBehindContentView(R.layout.sliding_menu_layout);
        getSupportFragmentManager().beginTransaction().add(R.id.sliding_menu_contaner_layout,MenuFragment.newInstance()).commit();

        SlidingMenu sm = getSlidingMenu();
        sm.setSlidingEnabled(true);
        sm.setMode(SlidingMenu.LEFT);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setBehindScrollScale(0);
        sm.setFadeDegree(0.25f);

    }

    @Override
    public void toNewFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.sliding_container_layout,NewsFragment.newInstance()).commit();
        getSlidingMenu().showContent();
    }

    @Override
    public void toYuleFragment() {
        startActivity(new Intent(SlideMenuMainActivity.this, CustomMainActivity.class));
        getSlidingMenu().showContent();
    }

    @Override
    public void toKejiFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.sliding_container_layout, KejiFragment.newInstance()).commit();
        getSlidingMenu().showContent();
    }
}
