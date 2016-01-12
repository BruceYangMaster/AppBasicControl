package com.scxh.android1503.slidemenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android1503.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YuleFragment extends Fragment {


    public static YuleFragment newInstance() {
        
        Bundle args = new Bundle();
        
        YuleFragment fragment = new YuleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yule_layout, container, false);
    }

}
