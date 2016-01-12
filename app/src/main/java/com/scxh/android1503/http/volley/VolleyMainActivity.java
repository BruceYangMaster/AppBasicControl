package com.scxh.android1503.http.volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyErrorHelper;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class VolleyMainActivity extends Activity implements View.OnClickListener {
    private Button mJsonRequestBtn;
    private TextView mShowMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_main_layout);
        mJsonRequestBtn = (Button) findViewById(R.id.volley_string_request_btn);
        mShowMessageTxt = (TextView) findViewById(R.id.volley_textView);
        mJsonRequestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.volley_string_request_btn:
                String httpUrl = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";

                //第一步初始化请请队列
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                //第二步构造请求
                StringRequest stringRequest = new StringRequest(httpUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Logs.e("onResponse >> :"+s);
                        mShowMessageTxt.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Logs.e("onErrorResponse >> :" + VolleyErrorHelper.getMessage(volleyError, VolleyMainActivity.this));
                        Toast.makeText(
                                VolleyMainActivity.this,
                                VolleyErrorHelper.getMessage(volleyError,
                                        VolleyMainActivity.this), Toast.LENGTH_SHORT).show();
                    }
                });
                // 第三步添加请求到请求队列
                stringRequest.setShouldCache(true);
                requestQueue.add(stringRequest);
                break;
        }
    }
}
