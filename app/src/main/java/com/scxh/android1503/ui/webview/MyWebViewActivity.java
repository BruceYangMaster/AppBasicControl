package com.scxh.android1503.ui.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.gridview.GridViewActivity;
import com.scxh.android1503.util.Constances;
import com.scxh.android1503.util.Logs;

import java.io.File;
/**
 * 当我们加载Html时候，会在我们data/应用package下生成database与cache两个文件夹:
我们请求的Url记录是保存在webviewCache.db里，而url的内容是保存在webviewCache文件夹下.
WebView中存在着两种缓存：网页数据缓存（存储打开过的页面及资源）、H5缓存（即AppCache）。


一、网页缓存

1、缓存构成
/data/data/package_name/cache/
/data/data/package_name/database/webview.db
/data/data/package_name/database/webviewCache.db


 

综合可以得知 webview 会将我们浏览过的网页url已经网页文件(css、图片、js等)保存到数据库表中



缓存模式(5种)
LOAD_CACHE_ONLY:  不使用网络，只读取本地缓存数据
LOAD_DEFAULT:  根据cache-control决定是否从网络上取数据。
LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
如：www.taobao.com的cache-control为no-cache，在模式LOAD_DEFAULT下，无论如何都会从网络上取数据，如果没有网络，就会出现错误页面；在LOAD_CACHE_ELSE_NETWORK模式下，无论是否有网络，只要本地有缓存，都使用缓存。本地没有缓存时才从网络上获取。
www.360.com.cn的cache-control为max-age=60，在两种模式下都使用本地缓存数据。


总结：根据以上两种模式，建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT，无网络时，使用LOAD_CACHE_ELSE_NETWORK。
 *
 */
public class MyWebViewActivity extends Activity {
	private static final String APP_CACAHE_DIRNAME = "/webcache";
	private WebView mWebView;
	private String url = Constances.BASE_URL+"/app/weding";
//	private String url = "http://m.dianhua.cn/detail/31ccb426119d3c9eaa794df686c58636121d38bc?apikey=jFaWGVHdFVhekZYWTBWV1ZHSkZOVlJWY&app=com.yulore.yellowsdk_ios&uid=355136051337627";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview_main_layout);

		mWebView = (WebView) findViewById(R.id.my_webview);

		mWebView.loadUrl(url);

		/** 第一步设置JavaScript可用 */
		WebSettings setting = mWebView.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置 缓存模式
		/** 第三步 */
		mWebView.addJavascriptInterface(new WebViewJavaScript(),
				"musicServiceInterfaceName");
		
		/**使用加载URL在webView内部跳转*/
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView webview, String url) {
				webview.loadUrl(url);

				return true;
			}
		});
	}

	/**
	 * 第二步 定义交互接类名"musicServiceInterfaceName"和方法
	 * 交互接类名"musicServiceInterfaceName"和方法名"playMusic"在html页面javaScript代码中引用 例如:
	 * window.musicServiceInterfaceName.playMusic
	 */
	class WebViewJavaScript {
		public void playMusic() {
			// 播放音乐
			Toast.makeText(MyWebViewActivity.this, "html代码调用原生应用代码方法",
					Toast.LENGTH_SHORT).show();
		}

		public void startGridViewHttp() {
			startActivity(new Intent(MyWebViewActivity.this,
					GridViewActivity.class));
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		clearWebViewCache();
	}

	/**
	 * 清除WebView缓存
	 */
	public void clearWebViewCache() {

		// 清理Webview缓存数据库
		try {
			deleteDatabase("webview.db");
			deleteDatabase("webviewCache.db");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// WebView 缓存文件
		File appCacheDir = new File(getFilesDir().getAbsolutePath()+ APP_CACAHE_DIRNAME);
		Logs.e("appCacheDir path=" + appCacheDir.getAbsolutePath());

		File webviewCacheDir = new File(getCacheDir().getAbsolutePath()+ "/webviewCache");
		Logs.e("webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

		// 删除webview 缓存目录
		if (webviewCacheDir.exists()) {
			deleteFile(webviewCacheDir);
		}
		// 删除webview 缓存 缓存目录
		if (appCacheDir.exists()) {
			deleteFile(appCacheDir);
		}
	}

	/**
	 * 递归删除 文件/文件夹
	 * 
	 * @param file
	 */
	public void deleteFile(File file) {

		Logs.i("delete file path=" + file.getAbsolutePath());

		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		} else {
			Logs.e("delete file no exists " + file.getAbsolutePath());
		}
	}
}
