package com.scxh.android1503.ui.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.Window;
import android.widget.TextView;

import com.scxh.android1503.R;

/**
 * TextView
 * 
 */
public class TextViewActivity extends Activity {
	private TextView mSpannableTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.widget_textview_layout);

		mSpannableTxt = (TextView) findViewById(R.id.three_txt);

		String sequenece = mSpannableTxt.getText().toString();

		SpannableString s = new SpannableString(sequenece);
		s.setSpan(new ForegroundColorSpan(getResources().getColor(
						android.R.color.holo_red_dark)), 12, 14,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		s.setSpan(new AbsoluteSizeSpan(35,true), 12, 14,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		s.setSpan(new StyleSpan(Typeface.BOLD), 12, 14,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		s.setSpan(new StrikethroughSpan(), 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		s.setSpan(new ForegroundColorSpan(Color.GREEN), 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		mSpannableTxt.setText(s);
	}

	public SpannableString setSpannableString(CharSequence sequence) {
		SpannableString string = new SpannableString(sequence);
		string.setSpan(new ForegroundColorSpan(Color.GREEN), 0,
				string.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		string.setSpan(new StyleSpan(Typeface.BOLD), 0,
				string.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 设置字体大小（绝对值,单位：像素）
		// string.setSpan(new AbsoluteSizeSpan(20), 0,string.length()-1,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
		string.setSpan(new AbsoluteSizeSpan(20, true), 0, string.length() - 1,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//
		// //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
		// string.setSpan(new RelativeSizeSpan(0.5f), 0,string.length()-1,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //0.5f表示默认字体大小的一半
		// string.setSpan(new RelativeSizeSpan(2.0f), 0,string.length()-1,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //2.0f表示默认字体大小的两倍

		// 设置删除线
		string.setSpan(new StrikethroughSpan(), 0, string.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return string;
	}
}
