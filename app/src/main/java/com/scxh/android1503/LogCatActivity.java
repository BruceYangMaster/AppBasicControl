package com.scxh.android1503;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LogCatActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.simple_list_item_1);
        TextView textView = (TextView) findViewById(android.R.id.text1);
        StringBuilder builder = new StringBuilder();
        builder.append("用来记录详细信息的Log.v()");
        builder.append("\n");
        builder.append("用来记录详细信息的Log.d()");
        builder.append("\n");
        builder.append("用来记录详细信息的Log.i()");
        builder.append("\n");
        builder.append("用来记录详细信息的Log.w()");
        builder.append("\n");
        builder.append("用来记录详细信息的Log.e()");
        builder.append("\n");

        textView.setText(builder.toString());

    }
}
