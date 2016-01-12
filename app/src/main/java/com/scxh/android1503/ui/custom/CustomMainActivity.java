package com.scxh.android1503.ui.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.scxh.android1503.R;

public class CustomMainActivity extends Activity implements TitleBarView.OnTitleBarClickListener {
    private TitleBarView mTitleBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_main_layout);

        mTitleBarView = (TitleBarView) findViewById(R.id.my_titlebarview);
        mTitleBarView.registerOnClickListener(this);
    }

    @Override
    public void onTitleBarClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_txt:
                Toast.makeText(CustomMainActivity.this, "返回", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.title_right_txt:
                Toast.makeText(CustomMainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
