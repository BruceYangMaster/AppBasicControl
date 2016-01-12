package com.scxh.android1503.fragment.stack;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1503.R;

public class StackMainActivity extends Activity implements View.OnClickListener {
    private Button mPopBtn, mPushBtn;
    private int mStackLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_main_layout);
        mPushBtn = (Button) findViewById(R.id.push_fragment_btn);
        mPopBtn = (Button) findViewById(R.id.pop_fragment_btn);
        mPushBtn.setOnClickListener(this);
        mPopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.push_fragment_btn:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.static_container_fragment_layout,
                        CountingFragment.newInstance(++mStackLevel));
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);/**动画效果*/
                ft.addToBackStack(null); /**添加当前Fragment到回退栈*/
                ft.commit();
                break;
            case R.id.pop_fragment_btn:
                getFragmentManager().popBackStack();  /**从Fragment回退栈弹出Fragment*/
                break;
        }
    }

    public static class CountingFragment extends Fragment {
        private int mNum;
        private static int[] sColorValue = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light};

        public static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();
            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
            TextView tv = (TextView) v;
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);
            tv.setText("Fragment #" + mNum);
            tv.setBackgroundColor(getActivity().getResources().getColor(sColorValue[mNum % 4]));
            return v;
        }
    }
}
