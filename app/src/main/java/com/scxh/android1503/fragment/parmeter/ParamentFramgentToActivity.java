package com.scxh.android1503.fragment.parmeter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.fragment.HeadlinesFragment;

public class ParamentFramgentToActivity extends Activity implements HeadlinesFragment.OnHeadlineSelectedListener{
    private TextView mMessageTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parament_framgent_to_layout);
        mMessageTxt = (TextView) findViewById(R.id.frgment_content_txt);

        getFragmentManager().beginTransaction().add(R.id.list_framgent_layout,
                HeadlinesFragment.newInstance()).commit();

        /**
         * 注册接口  在Fragmeng与Actviity绑定过程中通过 onAttach(Activity)已实现
         */
    }

    /**
     * 实现接口
     * @param msg
     */
    @Override
    public void onArticleSelected(String msg) {
        mMessageTxt.setText(msg);
    }
}
