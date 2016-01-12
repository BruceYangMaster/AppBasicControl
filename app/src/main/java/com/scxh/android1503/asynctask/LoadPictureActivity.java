package com.scxh.android1503.asynctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.scxh.android1503.R;

public class LoadPictureActivity extends Activity {
	
	private ImageView mPictureImg;
	private Button mDownLoadBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_load_picture_layout);
		
		mPictureImg = (ImageView) findViewById(R.id.async_picture_img);
		mDownLoadBtn = (Button) findViewById(R.id.async_load_picture_btn);
		
		mDownLoadBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String httpUrl = "http://img2.imgtn.bdimg.com/it/u=94536769,318259447&fm=23&gp=0.jpg";
				
//				MyAsyncTask sycn = new MyAsyncTask();
//				sycn.execute(httpUrl);
				
				new AsyncTask<String, Void, Bitmap>(){

					@Override
					protected Bitmap doInBackground(String... params) {
						return downLoadPicture(params[0]);
					}
					
					protected void onPostExecute(Bitmap result) {
						mPictureImg.setImageBitmap(result);
					}
				}.execute(httpUrl);
			}
		});
	}
	/**
	 * 网络下载图片
	 * @param url: http://xxx.png
	 */
	public Bitmap downLoadPicture(String httpUrl){
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
	public class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			Bitmap bitmap = result;
			mPictureImg.setImageBitmap(bitmap);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = downLoadPicture(url);
			return bitmap;
		}
		
	}
}
