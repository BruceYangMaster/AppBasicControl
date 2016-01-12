package com.scxh.android1503.fragment.tab;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android1503.R;

public class TabThreeFragment extends Fragment {
    public static Fragment newInstance() {
        TabThreeFragment articleFragment = new TabThreeFragment();
        return articleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_article_layout, container, false);
    }
}
