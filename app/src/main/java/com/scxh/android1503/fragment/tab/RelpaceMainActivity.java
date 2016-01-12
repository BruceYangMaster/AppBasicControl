package com.scxh.android1503.fragment.tab;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scxh.android1503.R;
import com.scxh.android1503.fragment.parmeter.MessageFragment;
import com.scxh.android1503.util.Logs;

public class RelpaceMainActivity extends Activity {
    private RadioGroup mRadioGroup;
    private  MessageFragment messageFragmentOne,messageFragmentTwo,messageFragmentThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relpace_main_layout);

        messageFragmentOne = MessageFragment.newInstance("第一个Fragment");
        getFragmentManager().beginTransaction().replace(R.id.replace_container_layout, messageFragmentOne).commit();

        mRadioGroup = (RadioGroup) findViewById(R.id.replace_tab_radio_group);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.tab_radio_one:
                        Logs.v("messageFragmentOne >>> :"+messageFragmentOne);
                        if(messageFragmentOne == null){
                            messageFragmentOne = MessageFragment.newInstance("第一个Fragment");
                        }
                        ft.replace(R.id.replace_container_layout, messageFragmentOne);
                        break;
                    case R.id.tab_radio_two:
                        if(messageFragmentTwo == null){
                            messageFragmentTwo = MessageFragment.newInstance("第二个Fragment");
                        }
                        ft.replace(R.id.replace_container_layout, messageFragmentTwo);
                        break;
                    case R.id.tab_radio_three:
                        if(messageFragmentThree == null){
                            messageFragmentThree = MessageFragment.newInstance("第三个Fragment");
                        }
                        ft.replace(R.id.replace_container_layout, messageFragmentThree);

                        break;
                }
                ft.setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out);
                ft.commit();
            }
        });
        ((RadioButton) mRadioGroup.getChildAt(0)).toggle(); // 默认选中第一项
    }
}
