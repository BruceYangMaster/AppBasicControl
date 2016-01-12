package com.scxh.android1503.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.scxh.android1503.R;

public class MainFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_layout);
/*
        HeadlinesFragment headlinesFragment = (HeadlinesFragment) HeadlinesFragment.newInstance();
        ArticleFragment articleFragment = (ArticleFragment) ArticleFragment.newInstance();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.headlines_fragment_id, headlinesFragment);
        fragmentTransaction.commit();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.article_fragment_id,articleFragment);
        fragmentTransaction.commit();*/

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.headlines_fragment_id, HeadlinesFragment.newInstance()).commit();
        fragmentManager.beginTransaction().add(R.id.article_fragment_id, ArticleFragment.newInstance()).commit();

    }
}
