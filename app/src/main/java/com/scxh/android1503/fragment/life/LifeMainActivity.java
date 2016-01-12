package com.scxh.android1503.fragment.life;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scxh.android1503.R;
import com.scxh.android1503.fragment.HeadlinesFragment;

public class LifeMainActivity extends Activity {
    private Button mAddBtn,mRemoveBtn,mReplaceBtn;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_main_layout);
        mAddBtn = (Button) findViewById(R.id.lift_add_btn);
        mRemoveBtn = (Button) findViewById(R.id.life_remove_btn);
        mReplaceBtn = (Button) findViewById(R.id.life_replace_btn);

        mFragmentManager = getFragmentManager();
        final Fragment fragment = LifeFragment.newInstance();
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.beginTransaction().add(R.id.life_layout, fragment, "lifeFragmentTag").commit();
            }
        });

        mRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.beginTransaction().remove(fragment).commit();
            }
        });

        mReplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.beginTransaction().replace(R.id.life_layout, HeadlinesFragment.newInstance()).commit();
            }
        });

    }
}
