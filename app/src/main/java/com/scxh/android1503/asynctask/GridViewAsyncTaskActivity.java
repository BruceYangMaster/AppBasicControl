package com.scxh.android1503.asynctask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;
import com.squareup.picasso.Picasso;

public class GridViewAsyncTaskActivity extends Activity {
	private GridView mGridView;
//	private String[] imageThumbUrls = new String[] {
//			"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
//			"http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
//		 };
	
	private String[] imageThumbUrls = Constances.imageThumbUrls;
		 
	private GridViewAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyn_grid_layout);
		mGridView = (GridView) findViewById(R.id.async_gridview);

		mAdapter = new GridViewAdapter(this);
		mGridView.setAdapter(mAdapter);
		

	}

	public class GridViewAdapter extends BaseAdapter {
		private LayoutInflater infalter;
		private Context context;

		public GridViewAdapter(Context context) {
			this.context = context;
			infalter = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return imageThumbUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return imageThumbUrls[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageview;
			if (convertView == null) {
				convertView = infalter.inflate(
						R.layout.async_gridview_item_layout, null);
				imageview = (ImageView) convertView
						.findViewById(R.id.async_item_img);
				convertView.setTag(imageview);
			} else {
				imageview = (ImageView) convertView.getTag();
			}

			String httpUrl = (String) getItem(position);
			
			Picasso.with(context).load(httpUrl).into(imageview);
			
			
			return convertView;
		}

	}

}
