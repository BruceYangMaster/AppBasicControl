package com.scxh.android1503.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxh.android1503.R;

public class MyBaseAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
	private Context context;
	private LayoutInflater layoutInflater;

	public MyBaseAdapter(Context context,
			ArrayList<HashMap<String, Object>> listData) {
		this.context = context;
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// Log.v("adapter", "getCount >>>>  ");
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// Log.i("adapter", "getItem >>>>  ");
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// Log.d("adapter", "getItemId >>>>  ");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.w("adapter", "getView >>>>  " + position + " convertView :"+ convertView);
		ViewHodler holder;
		View v;
		if (convertView == null) {
			v = layoutInflater.inflate(R.layout.adapter_simple_item1_layout,null);

			holder = new ViewHodler();
			holder.iconImg = (ImageView) v.findViewById(R.id.simple_icon_img);
			holder.titleTxt = (TextView) v.findViewById(R.id.simple_title_txt);
			holder.contentTxt = (TextView) v.findViewById(R.id.simple_content_txt);

			v.setTag(holder);

		} else {
			v = convertView;
			holder = (ViewHodler) v.getTag();
		}

		HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
		int icon = (Integer) item.get("icon");
		String title = (String) item.get("title");
		String content = (String) item.get("content");

		holder.iconImg.setImageResource(icon);
		holder.titleTxt.setText(title);
		holder.contentTxt.setText(content);

		return v;
	}

	public View getViews(int position, View convertView, ViewGroup parent) {
		View v = layoutInflater.inflate(R.layout.adapter_simple_item1_layout,null);

		ImageView iconImg = (ImageView) v.findViewById(R.id.simple_icon_img);
		TextView titleTxt = (TextView) v.findViewById(R.id.simple_title_txt);
		TextView contentTxt = (TextView) v.findViewById(R.id.simple_content_txt);

		HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
		int icon = (Integer) item.get("icon");
		String title = (String) item.get("title");
		String content = (String) item.get("content");

		iconImg.setImageResource(icon);
		titleTxt.setText(title);
		contentTxt.setText(content);

		return v;
	}

	class ViewHodler {
		ImageView iconImg;
		TextView titleTxt;
		TextView contentTxt;
	}

}
