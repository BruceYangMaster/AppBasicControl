package com.scxh.android1503.fragment.subfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.scxh.android1503.R;

public class MyAlertFragment extends DialogFragment {
    public static MyAlertFragment newInstance() {

        Bundle args = new Bundle();

        MyAlertFragment fragment = new MyAlertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher)
                .setMessage("你好我是Fragment对话框")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                    }
                }).create();

    }

}
