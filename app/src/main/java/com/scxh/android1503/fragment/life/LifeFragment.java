package com.scxh.android1503.fragment.life;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class LifeFragment extends Fragment {
    public static LifeFragment newInstance() {
        LifeFragment fragment = new LifeFragment();
        return fragment;
    }

    /**
     * 绑定Activity
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Logs.i("onAttach>> Activity  >>>>>");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logs.e("onAttach>  Context  >>>>>>");
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logs.d("onCreate >>>>>>");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Logs.v("onCreateView >>>>>>>");
        return inflater.inflate(R.layout.fragment_life_layout, container, false);
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logs.e("onActivityCreate>>>>>>>>");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.w("onStart>>>>>>>>>>");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logs.i("onResume>>>>>>>>>>");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logs.d("onPause>>>>>>>>>>>>");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logs.v("onStop>>>>>>>>>>>>>");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logs.e("onDestroyView>>>>>>>>>>>>>");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logs.w("onDestroy>>>>>>>>>>>>>");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logs.i("onDetach>>>>>>>>>>>>>");
    }
}
