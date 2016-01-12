package com.scxh.android1503.ui.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MessageBean;
import com.scxh.android1503.ui.adapter.MyMessageAdapterActivity.MyMessageAdapter.ViewHodler;

public class EditTextActivity extends Activity {
	private AutoCompleteTextView mMailAutoTxt;

	private String[] array = { "abc@qq.com", "abdcd@qq.com", "afjb@qq.com",
			"dfkh@qq.com" };

	private String[] jarray = { "jabc@qq.com", "jabdcd@qq.com", "jafjb@qq.com",
			"jdfkh@qq.com" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("tag", "EditTextActivity onCreate >>>>>>>");

		setContentView(R.layout.widget_edittext_layout);

		mMailAutoTxt = (AutoCompleteTextView) findViewById(R.id.mail_edit);


		mMailAutoTxt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String key = s.toString();

				if (key.equals("j")) {
					ArrayAdapter<String> jadapter = new ArrayAdapter<String>(
							EditTextActivity.this,
							android.R.layout.simple_list_item_1, jarray);

					mMailAutoTxt.setAdapter(jadapter);
					
					
					MyMessageAdapter adapter = new MyMessageAdapter(EditTextActivity.this);
					mMailAutoTxt.setAdapter(adapter);
					
				} else if (key.equals("a")) {
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							EditTextActivity.this,
							android.R.layout.simple_list_item_1, array);
					
					mMailAutoTxt.setAdapter(adapter);
					
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				

			}
		});
		
		mMailAutoTxt.setThreshold(1);
	}
	
	public class MyArrayAdapter extends ArrayAdapter<String>{

		public MyArrayAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}
		
	}
	
	
	public class MyMessageAdapter extends BaseAdapter implements Filterable{
		private ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		private LayoutInflater layoutInflater;
		
		public MyMessageAdapter(Context context){
			layoutInflater = LayoutInflater.from(context);
		}
		
		public void setListData(ArrayList<MessageBean> list){
			this.list = list;
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			ViewHodler holder;
			if(convertView == null){
				v = layoutInflater.inflate(R.layout.adapter_simple_item1_layout, null);
				
				holder = new ViewHodler();
				holder.iconImg = (ImageView) v.findViewById(R.id.simple_icon_img);
				holder.titleTxt = (TextView) v.findViewById(R.id.simple_title_txt);
				holder.contentTxt = (TextView) v.findViewById(R.id.simple_content_txt);
				
				v.setTag(holder);
			}else{
				v = convertView;
				holder = (ViewHodler) v.getTag();
			}
			
			MessageBean msg = (MessageBean) getItem(position);
			holder.iconImg.setImageResource(msg.getIcon());
			holder.titleTxt.setText(msg.getTitle());
			holder.contentTxt.setText(msg.getContent());
			
			return v;
		}
		public class ViewHodler{
			ImageView iconImg;
			TextView titleTxt;
			TextView contentTxt;
		}
		@Override
		public Filter getFilter() {
			return null;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("tag", "EditTextActivity onStart >>>>>>>>");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("tag", "EditTextActivity onResume >>>>>>>");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.w("tag", "EditTextActivity onPause >>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e("tag", "EditTextActivity onStop >>>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("tag", "EditTextActivity onDestroy >>>>>>>>>");
	}

	public void onBackClickListener(View v) {
		finish();
	}

}
