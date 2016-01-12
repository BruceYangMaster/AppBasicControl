package com.scxh.android1503.asynctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.scxh.android1503.R;

public class CopyOfGridViewAsyncTaskActivity extends Activity {
	private GridView mGridView;
	private String[] imageThumbUrls = new String[] {
			"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
		 };
	private GridViewAdapter mAdapter;
	private Executor mExecutor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyn_grid_layout);
		mGridView = (GridView) findViewById(R.id.async_gridview);

		mAdapter = new GridViewAdapter(this);
		mGridView.setAdapter(mAdapter);

		mExecutor = new ThreadPoolExecutor(10,  150, 10,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}
	/**
	 * 通过在异步任务中循环加载图片，不但刷新适配器实现
	 * 不提倡，原因:
	 * 1.重复刷新适配器执行getView方法 
	 * 2.不能实现图片同步加载,相当于当线程执行.
	 */
	public void loadPictureByPublishProgress(){
		new AsyncTask<String, Bitmap, Void>() {

			@Override
			protected Void doInBackground(String... params) {
				for (int i = 0; i < params.length; i++) {
					Bitmap bm = downLoadPicture(params[i]);
					publishProgress(bm);
				}
				return null;
			}

			protected void onProgressUpdate(Bitmap... values) {
//				mAdapter.addDate(values[0]);
			}

		}.execute(imageThumbUrls);

	}
	
	public class MyGridViewAdapter extends BaseAdapter {
		private ArrayList<Bitmap> list = new ArrayList<Bitmap>();
		private LayoutInflater infalter;

		public void addDate(Bitmap bm) {
			list.add(bm);
			notifyDataSetChanged();
		}

		public MyGridViewAdapter(Context context) {
			infalter = LayoutInflater.from(context);
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
			final ImageView imageview;
			if (convertView == null) {
				convertView = infalter.inflate(R.layout.async_gridview_item_layout, null);
				imageview = (ImageView) convertView.findViewById(R.id.async_item_img);
				convertView.setTag(imageview);
			} else {
				imageview = (ImageView) convertView.getTag();
			}

			Bitmap bitmap = (Bitmap) getItem(position);
			imageview.setImageBitmap(bitmap);

			return convertView;
		}

	}

	public class GridViewAdapter extends BaseAdapter {
		private LayoutInflater infalter;

		public GridViewAdapter(Context context) {
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
			final ImageView imageview;
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

			new AsyncTask<String, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(String... params) {
					return downLoadPicture(params[0]);
				}

				protected void onPostExecute(Bitmap result) {
					if (result != null) {
						imageview.setImageBitmap(result);
					}
				}
			}.execute(httpUrl);  //单任务一个一个执行
			
//		    }.executeOnExecutor(mExecutor,httpUrl);  //多任务同时执行
			return convertView;
		}

	}

	/**
	 * 网络下载图片
	 * 
	 * @param url
	 */
	public Bitmap downLoadPicture(String httpUrl) {
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();

			Bitmap bitmap = BitmapFactory.decodeStream(is);

			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
