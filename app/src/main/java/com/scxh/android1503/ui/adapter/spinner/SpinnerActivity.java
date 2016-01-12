package com.scxh.android1503.ui.adapter.spinner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MessageBean;

public class SpinnerActivity extends Activity {
	private Spinner mOneSpinner, mTwoSpinner, mThreeSpinner, mFourSpinner,mFourSpinner5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.adapter_spinner_layout);

		mOneSpinner = (Spinner) findViewById(R.id.adapter_spinner1);
		mTwoSpinner = (Spinner) findViewById(R.id.adapter_spinner2);
		mThreeSpinner = (Spinner) findViewById(R.id.adapter_spinner3);
		mFourSpinner = (Spinner) findViewById(R.id.adapter_spinner4);
		mFourSpinner5 = (Spinner) findViewById(R.id.adapter_spinner5);

		setSpinnerOne();
		setSpinnerTwo();
		setSpinnerThree();
		setSpinnerFour();
		setSpinnerFive();
	}

	public void setSpinnerOne() {
		String[] spinnerData = { "张三", "李四", "王二", "麻子", "赵钱" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, spinnerData);

		mOneSpinner.setAdapter(adapter);
		mOneSpinner.setSelection(1,true);
		mOneSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(SpinnerActivity.this,
						"mOneSpinner onItemSelected position " + position,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(SpinnerActivity.this, " onNothingSelected ",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void setSpinnerFive() {
		String[] spinnerData = { "张三", "李四", "王二", "麻子", "赵钱" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, spinnerData);

		mFourSpinner5.setAdapter(adapter);
		mFourSpinner5.setSelection(1,true);
		mFourSpinner5.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(SpinnerActivity.this,
						"mFourSpinner5 onItemSelected position " + position,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(SpinnerActivity.this, " onNothingSelected ",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void setSpinnerThree() {
		String[] spinnerThreeData = { "美食", "娱乐", "王二", "麻子", "赵钱" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, spinnerThreeData);

		mThreeSpinner.setAdapter(adapter);
		mThreeSpinner.setSelection(1,true);
		mThreeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(SpinnerActivity.this,
						"mThreeSpinner onItemSelected position " + position,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(SpinnerActivity.this, " onNothingSelected ",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void setSpinnerFour() {
		String[] arrayss = { "体育", "NBA", "CBA", "新闻", "赵钱" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayss);

		mFourSpinner.setAdapter(adapter);
		mFourSpinner.setSelection(2,true);
		mFourSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(SpinnerActivity.this,
						"mFourSpinner onItemSelected position " + position,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(SpinnerActivity.this, " onNothingSelected ",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void setSpinnerTwo() {
		MySpinnerAdapter spinnerAdapter = new MySpinnerAdapter(this);
		mTwoSpinner.setAdapter(spinnerAdapter);

		spinnerAdapter.setListData(getData());
	}
	/**
	 * 获取数据源
	 * @return
	 */
	public ArrayList<MessageBean> getData() {
		ArrayList<MessageBean> listData = new ArrayList<MessageBean>();

		MessageBean msg = new MessageBean();
		msg.setIcon(R.drawable.m3);
		msg.setTitle("1【多店通用】乡村基");
		msg.setContent("20元代金券！全场通用，可叠加使用，提供免费WiFi！");
		msg.setPrince("9.9");
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("2【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setPrince("10");
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("3【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setPrince("7.8");
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m3);
		msg.setTitle("4【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setPrince("10.9");
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("5【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setPrince("8");
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("6【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("7【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setPrince("6.9");
		listData.add(msg);

		return listData;

	}
	/**
	 * 自定义适配器
	 *
	 */
	public class MySpinnerAdapter extends BaseAdapter {
		private ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		private LayoutInflater layoutInflater; // 将XML布局转换成View对象
		private Context context;

		public MySpinnerAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
			this.context = context;
		}

		/**
		 * 给Adapter设置数据
		 * 
		 * @param list
		 */
		public void setListData(ArrayList<MessageBean> list) {
			this.list = list;
			notifyDataSetChanged(); // 通知适配器刷新数据
		}

		/**
		 * 返回数据项个数
		 */
		@Override
		public int getCount() {
			return list.size();
		}

		/**
		 * 获取指定位置的数据
		 */
		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		/**
		 * 获取在指定位置上显示的View并设置相应数据
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			ViewHodler holder;
			if (convertView == null) {
				v = layoutInflater.inflate(
						R.layout.adapter_simple_item2_layout, null);

				holder = new ViewHodler();
				holder.iconImg = (ImageView) v.findViewById(R.id.icon_img);
				holder.titleTxt = (TextView) v.findViewById(R.id.title_txt);
				holder.contentTxt = (TextView) v.findViewById(R.id.content_txt);
				holder.princeTxt = (TextView) v.findViewById(R.id.prince_txt);

				v.setTag(holder);
			} else {
				v = convertView;
				holder = (ViewHodler) v.getTag();
			}

			MessageBean msg = (MessageBean) getItem(position);

			// holder.iconImg.setImageResource(msg.getIcon());
			holder.iconImg.setBackgroundResource(msg.getIcon());
			holder.titleTxt.setText(msg.getTitle());
			holder.contentTxt.setText(msg.getContent());
			// holder.princeTxt.setText(msg.getPrince());
			holder.princeTxt.setText(setSpannableString(context.getString(
					R.string.ui_adapter_listview_txt, msg.getPrince())));

			return v;
		}

		public class ViewHodler {
			ImageView iconImg;
			TextView titleTxt;
			TextView contentTxt;
			TextView princeTxt;
		}

		public SpannableString setSpannableString(CharSequence sequence) {
			SpannableString string = new SpannableString(sequence);
			string.setSpan(new ForegroundColorSpan(Color.GREEN), 0,
					string.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			string.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
					string.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			// 设置字体大小（绝对值,单位：像素）
			// string.setSpan(new AbsoluteSizeSpan(20), 0,string.length()-1,
			// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
			string.setSpan(new AbsoluteSizeSpan(20, true), 0,
					string.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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

}
