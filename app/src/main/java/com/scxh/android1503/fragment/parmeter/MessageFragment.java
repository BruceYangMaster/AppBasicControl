package com.scxh.android1503.fragment.parmeter;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class MessageFragment extends Fragment {
    private String message = "";
    public MessageFragment() {
        // Required empty public constructor
    }
    public static MessageFragment newInstance(String msg) {
        Bundle args = new Bundle();
        args.putString("MESSAGE",msg);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        Logs.v(message+"onAttach>>>>>>>>>>>>>");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logs.v(message+"onCreate>>>>>>>>>>>>>");
        if(getArguments() != null){
            message = getArguments().getString("MESSAGE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logs.v(message+"onCreateView>>>>>>>>>>>>>");
        View v = inflater.inflate(R.layout.fragment_message_layout, container, false);
        TextView msgTxt = (TextView) v.findViewById(R.id.argment_show_msg_txt);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.replace_framgent_layout);
        if(message.contains("一")){
            layout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        }else if (message.contains("二")){
            layout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }else if (message.contains("三")){
            layout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }
        /**获取从Activity传来的值*/


        msgTxt.setText(message);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logs.v(message+"onActivityCreated>>>>>>>>>>>>>");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.w(message+"onStart>>>>>>>>>>");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logs.i(message+"onResume>>>>>>>>>>");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logs.d(message+"onPause>>>>>>>>>>>>");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logs.v(message+"onStop>>>>>>>>>>>>>");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logs.e(message+"onDestroyView>>>>>>>>>>>>>");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logs.w(message+"onDestroy>>>>>>>>>>>>>");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logs.i(message+"onDetach>>>>>>>>>>>>>");
    }
}
