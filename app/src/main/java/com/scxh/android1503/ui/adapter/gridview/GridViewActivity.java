package com.scxh.android1503.ui.adapter.gridview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.scxh.android1503.R;

public class GridViewActivity extends Activity {
	private GridView mGridView;
	private int[] mImages = { R.drawable.m3, R.drawable.m4, R.drawable.m8,
			R.drawable.m3, R.drawable.m4, R.drawable.m8, R.drawable.m3,
			R.drawable.m4, R.drawable.m8, R.drawable.m3, R.drawable.m4, R.drawable.m8,
			R.drawable.m3, R.drawable.m4, R.drawable.m8, R.drawable.m3,
			R.drawable.m4, R.drawable.m8 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.adapter_gridview_layout);

		/**设置ActionBar具有导航功能*/
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		
		mGridView = (GridView) findViewById(R.id.adpater_gridView);

		ImageGridViewAdapter adapter = new ImageGridViewAdapter(this);
		mGridView.setAdapter(adapter);

		adapter.setData(mImages);
		
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(GridViewActivity.this, "postion "+position, Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/**ActionBar导航点击事件*/
		case android.R.id.home:
			Toast.makeText(this, "ActionBar导航点击事件", Toast.LENGTH_SHORT).show();
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if(NavUtils.shouldUpRecreateTask(this, upIntent)){
				TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
			}else{
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	public class ImageGridViewAdapter extends BaseAdapter{
		private int[] images = {};
		private Context context;
		private LayoutInflater layoutInflater;
		
		public ImageGridViewAdapter(Context context){
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
		}
		/**
		 * 设置数据源
		 * @param images
		 */
		public void setData(int[] images){
			this.images = images;
			notifyDataSetChanged();//通知适配器刷新数据源
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
			
			if(convertView == null){
				convertView = layoutInflater.inflate(R.layout.item_imageview_layout, null);
				itemImg = (ImageView) convertView.findViewById(R.id.imageview);
				
				convertView.setTag(itemImg);
			}else{
				itemImg = (ImageView) convertView.getTag();
			}
			
//			itemImg.setImageResource((Integer)getItem(position));
			itemImg.setBackgroundResource((Integer)getItem(position));
			return convertView;
			
		}
		
	}
}
