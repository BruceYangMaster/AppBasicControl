package com.scxh.android1503.slidemenu;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scxh.android1503.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private Button mYuleBtn,mNewsBtn,mKejiBtn;

    public MenuFragmentListener mMenuFragmentListener;
    public interface MenuFragmentListener{
        public void toNewFragment();
        public void toYuleFragment();
        public void toKejiFragment();
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof  MenuFragment.MenuFragmentListener){
            mMenuFragmentListener = (MenuFragmentListener)context;
        }else{
            throw new RuntimeException("context is not instance of MenuFragmentListener");
        }
    }

    public static MenuFragment newInstance() {
        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mYuleBtn = (Button) getView().findViewById(R.id.sliding_menu_yule_btn);
        mYuleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuFragmentListener.toYuleFragment();
            }
        });

        mNewsBtn = (Button)  getView().findViewById(R.id.sliding_menu_news_btn);
        mNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuFragmentListener.toNewFragment();
            }
        });

        mKejiBtn = (Button) getView().findViewById(R.id.sliding_menu_keji_btn);
        mKejiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuFragmentListener.toKejiFragment();
            }
        });
    }
}
