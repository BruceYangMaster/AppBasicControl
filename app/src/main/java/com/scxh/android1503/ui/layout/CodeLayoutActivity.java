package com.scxh.android1503.ui.layout;

import com.scxh.android1503.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 1.代码实现布局
 * 2.资源引用(string,color,dimen,drawable)方式:
 *    1>在xml文件中引用
 *    2>在代码中引用
 *  
 */
public class CodeLayoutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout. LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(layoutParams);
		
		Resources res = getResources();
		
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setBackgroundColor(res.getColor(R.color.blue));
		
		
		TextView textView = new TextView(this);
		textView.setText(res.getString(R.string.first_txt));
		textView.setTextColor(res.getColor(R.color.white));
		textView.setTextSize(res.getDimension(R.dimen.widget_text_size));
		
		
		TextView textView1 = new TextView(this);
		textView1.setText(res.getString(R.string.two_txt));
		textView1.setTextColor(res.getColor(R.color.white));
		textView1.setTextSize(res.getDimension(R.dimen.widget_text_size));
		
		layout.addView(textView);
		layout.addView(textView1);
		
		setContentView(layout);

	}
}
