package com.scxh.android1503.ui.scroll;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MessageBean;
import com.scxh.android1503.util.Logs;

public class ScroViewListViewActivity extends Activity {
	private static final int TYPE_PICTURE = 0;// 图片
	private static final int TYPE_TEXT = 1; // 文本
	private GridView mGridView;
	private InnerListView mListView;
	private ScrollView mScrollView;
	private MyMessageAdapter mAdapter;
	private int[] mImages = { R.drawable.list1, R.drawable.list2, R.drawable.list3,
			R.drawable.list2, R.drawable.list1, R.drawable.m8};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_list_scrol_view_layout);
		mGridView = (GridView) findViewById(R.id.pager_gridview);
		mListView = (InnerListView) findViewById(R.id.pager_listview);
		mScrollView = (ScrollView) findViewById(R.id.pager_scrollview);

		mListView.setParentScrollView(mScrollView);
		
		ImageGridViewAdapter adapter = new ImageGridViewAdapter(this);
		mGridView.setAdapter(adapter);
		adapter.setData(mImages);

		mAdapter = new MyMessageAdapter(this);
		mListView.setAdapter(mAdapter);
		mAdapter.setListData(getData());
		
		
	}

	public ArrayList<MessageBean> getData() {
		ArrayList<MessageBean> listData = new ArrayList<MessageBean>();

		MessageBean msg = new MessageBean();
		msg.setIcon(R.drawable.m3);
		msg.setTitle("1【多店通用】乡村基");
		msg.setContent("20元代金券！全场通用，可叠加使用，提供免费WiFi！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("2【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_PICTURE);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("3【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m3);
		msg.setTitle("4【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_PICTURE);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("5【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("6【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("7【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("8【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("9【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("10【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);
		
		return listData;

	}

	public class MyMessageAdapter extends BaseAdapter {
		private ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		private LayoutInflater layoutInflater;

		public MyMessageAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
		}

		public void setListData(ArrayList<MessageBean> list) {
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
			Logs.v("getView  convertView :"+convertView+ " position :"+position);
			View v;
			ViewHodler holder;
			if (convertView == null) {
				v = layoutInflater.inflate(
						R.layout.adapter_simple_item1_layout, null);

				holder = new ViewHodler();
				holder.iconImg = (ImageView) v
						.findViewById(R.id.simple_icon_img);
				holder.titleTxt = (TextView) v
						.findViewById(R.id.simple_title_txt);
				holder.contentTxt = (TextView) v
						.findViewById(R.id.simple_content_txt);

				v.setTag(holder);
			} else {
				v = convertView;
				holder = (ViewHodler) v.getTag();
			}

			MessageBean msg = (MessageBean) getItem(position);

			if (msg.getType() == TYPE_PICTURE) {
				holder.contentTxt.setText("多优惠");
				holder.contentTxt.setBackgroundColor(getResources().getColor(
						android.R.color.holo_orange_light));
			} else {
				holder.contentTxt.setText(msg.getContent());
			}

			holder.iconImg.setImageResource(msg.getIcon());
			holder.titleTxt.setText(msg.getTitle());

			return v;
		}

		public class ViewHodler {
			ImageView iconImg;
			TextView titleTxt;
			TextView contentTxt;
		}
	}

	public class ImageGridViewAdapter extends BaseAdapter {
		private int[] images = {};
		private Context context;
		private LayoutInflater layoutInflater;

		public ImageGridViewAdapter(Context context) {
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
		}

		/**
		 * 设置数据源
		 * 
		 * @param images
		 */
		public void setData(int[] images) {
			this.images = images;
			notifyDataSetChanged();// 通知适配器刷新数据源
		}

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			return images[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView itemImg;

			if (convertView == null) {
				convertView = layoutInflater.inflate(
						R.layout.item_imageview_layout, null);
				itemImg = (ImageView) convertView.findViewById(R.id.imageview);

				convertView.setTag(itemImg);
			} else {
				itemImg = (ImageView) convertView.getTag();
			}
			itemImg.setBackgroundResource((Integer) getItem(position));
			return convertView;

		}

	}
}
