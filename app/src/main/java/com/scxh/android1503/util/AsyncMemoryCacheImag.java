package com.scxh.android1503.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;
import android.widget.ImageView;

public class AsyncMemoryCacheImag {
	private	LruCache<String, Bitmap> mMemoryCache;
	private Context context;
	private File mFile;
	
	public AsyncMemoryCacheImag(Context context){
		this.context = context;
		initLruCache();
		initFileCache();
	}
	
	public void initFileCache(){
		mFile = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
	}
	
	public String getFileName(String url){
		//http://www.scxh.com/m4.png
		int index = url.lastIndexOf("/");
		return (String) url.subSequence(index, url.length());
		
	}
	
	/**
	 * java 写内部存储文件方式
	 */
	public void writeFile(String url,Bitmap bitmap) {
		try {
			
			File file = new File(mFile, getFileName(url));
			FileOutputStream os = null;
			try {
				os = new FileOutputStream(file);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
				
				os.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Bitmap readFile(String url){
		File file = new File(mFile, getFileName(url));
		Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
		return bitmap;
	}
	
	/**
	 * 初始化LRUCache
	 */
	public void initLruCache(){
		// 获取应用程序最大可用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 32;
		// 设置图片缓存大小为程序最大可用内存的1/8
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
		
		
	}
	/**
	 * 添加图片到内存缓存
	 */
	public void addBitmapToCache(String url,Bitmap bitmap){
		if(getCacheBitmap(url) == null){
			mMemoryCache.put(url, bitmap);
		}
	}
	/**
	 * 从缓存中获取图片
	 * @param url
	 * @return
	 */
	public Bitmap getCacheBitmap(String url){
		Bitmap bitmap = mMemoryCache.get(url);
		return bitmap;
	}
	
	/**
	 * 通常类似 ListView 与 GridView 等视图组件在使用上面演示的AsyncTask 方法时会同时带来另外一个问题。
	 * 为了更有效的处理内存，那些视图的子组件会在用户滑动屏幕时被循环使用。如果每一个子视图都触发一个AsyncTask ，
	 * 那么就无法确保当前视图在结束task时，分配的视图已经进入循环队列中给另外一个子视图进行重用。 而且, 无法确保所有的
	 * 异步任务能够按顺序执行完毕。
	 * 
	 * @param imageUrl
	 * @param imageView
	 * @param resId
	 *            默认图片资源
	 */
	public void loadBitmap(Resources res, String imageUrl, ImageView imageView,int resId) {
		// 第一步：根据图片地址，判断图片是否被缓存在内存
		Bitmap bitmap = getCacheBitmap(imageUrl);
		if(bitmap != null){
			imageView.setImageBitmap(bitmap);
			Logs.v("从内存缓存获取图片");
			return;
		}
		
		bitmap = readFile(imageUrl);
		if(bitmap != null){
			imageView.setImageBitmap(bitmap);
			
			addBitmapToCache(imageUrl, bitmap);
			
			Logs.v("从文件缓存获取图片");
			return;
		}
		//检查文件缓存
		
		
		if (cancelPotentialWork(imageUrl, imageView)) {
			BitmapWorkerTask task = new BitmapWorkerTask(imageView); //网络异步获取图片，同时向异步传入imageView

			AsyncDrawable asyncDrawable = new AsyncDrawable(res,BitmapFactory.decodeResource(res, resId), task);
			imageView.setImageDrawable(asyncDrawable);

			task.execute(imageUrl);
		}
	}

	public static boolean cancelPotentialWork(String imageUrl,
			ImageView imageView) {
		final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

		if (bitmapWorkerTask != null) {
			final String bitmapData = bitmapWorkerTask.data;
			if (!bitmapData.equals(imageUrl)) {
				// Cancel previous task
				bitmapWorkerTask.cancel(true);
			} else {
				// The same work is already in progress
				return false;
			}
		}
		// No task associated with the ImageView, or an existing task was
		// cancelled
		return true;
	}

	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		if (imageView != null) {
			final Drawable drawable = imageView.getDrawable();
			if (drawable instanceof AsyncDrawable) {
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}

	/**
	 * 创建一个专用的Drawable的子类来储存返回工作任务的引用。在这种情况下，当任务完成时BitmapDrawable会被使用
	 * 
	 */
	static class AsyncDrawable extends BitmapDrawable {
		private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

		public AsyncDrawable(Resources res, Bitmap bitmap,BitmapWorkerTask bitmapWorkerTask) {
			super(res, bitmap);
			bitmapWorkerTaskReference = new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
		}

		public BitmapWorkerTask getBitmapWorkerTask() {
			return (BitmapWorkerTask) bitmapWorkerTaskReference.get();
		}
	}

	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
		private final WeakReference<ImageView> imageViewReference;// 弱引用
																	// ，特点当内存不够时，自动回收
		private String data = "";

		public BitmapWorkerTask(ImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(String... params) {
			data = params[0];
			return downLoadPicture(data);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}

			if (imageViewReference != null && bitmap != null) {
				final ImageView imageView = (ImageView) imageViewReference.get();
				final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
				if (this == bitmapWorkerTask && imageView != null) {
					imageView.setImageBitmap(bitmap);
					
					writeFile(data,bitmap);//写图片文件到文件缓存
					addBitmapToCache(data, bitmap);
				}
			}
		}
	}

	
	/**
	 * 网络下载图片
	 * 
	 * @param url
	 */
	public Bitmap downLoadPicture(String httpUrl) {
		Logs.v("从网络获取图片");
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
