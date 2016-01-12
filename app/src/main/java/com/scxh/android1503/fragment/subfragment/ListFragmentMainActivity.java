package com.scxh.android1503.fragment.subfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scxh.android1503.R;

public class ListFragmentMainActivity extends Activity {
    private Button mDialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment_main_layout);
        mDialogBtn = (Button) findViewById(R.id.fragment_dialog_btn);

        mDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .add(MyAlertFragment.newInstance(), "dialogFragment").commit();
            }
        });

    }
}
