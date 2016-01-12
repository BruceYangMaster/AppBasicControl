package com.scxh.android1503.fragment.parmeter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scxh.android1503.R;

public class ArgmentsMainActivity extends Activity {
    private Button mSendBtn;
    private EditText mMessageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argments_main_layout);

        mSendBtn = (Button) findViewById(R.id.argment_send_btn);
        mMessageEdit = (EditText) findViewById(R.id.argments_send_edit);

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mMessageEdit.getText().toString();
                getFragmentManager().beginTransaction().add(R.id.argment_fragment_layout,MessageFragment.newInstance(message)).commit();
            }
        });
    }
}
