package com.scxh.android1503;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android1503.asynctask.AsyncTaskActivity;
import com.scxh.android1503.asynctask.GridViewAsyncTaskActivity;
import com.scxh.android1503.asynctask.LoadPictureActivity;
import com.scxh.android1503.event.EventActivity;
import com.scxh.android1503.event.SaveStateActivity;
import com.scxh.android1503.fragment.MainFragmentActivity;
import com.scxh.android1503.fragment.life.LifeMainActivity;
import com.scxh.android1503.fragment.parmeter.ArgmentsMainActivity;
import com.scxh.android1503.fragment.parmeter.ParamentFramgentToActivity;
import com.scxh.android1503.fragment.stack.StackMainActivity;
import com.scxh.android1503.fragment.subfragment.ListFragmentMainActivity;
import com.scxh.android1503.fragment.tab.RelpaceMainActivity;
import com.scxh.android1503.fragment.tab.TabMainActivity;
import com.scxh.android1503.fragment.viewpager.ImageLoaderMainActivity;
import com.scxh.android1503.fragment.viewpager.ViewPagerMainActivity;
import com.scxh.android1503.handler.HandlerActivity;
import com.scxh.android1503.http.HttpConnectActivity;
import com.scxh.android1503.http.cache.HttpCacheActivity;
import com.scxh.android1503.http.volley.VolleyMainActivity;
import com.scxh.android1503.intent.ListIntentActivity;
import com.scxh.android1503.mp3.Mp3Activity;
import com.scxh.android1503.mp3.Mp3ListActivity;
import com.scxh.android1503.mp3.MusicPlayerList;
import com.scxh.android1503.notification.NotificationActivity;
import com.scxh.android1503.receive.MyBroadCastActivity;
import com.scxh.android1503.service.MyBinderActivity;
import com.scxh.android1503.service.TestServcieActivity;
import com.scxh.android1503.slidemenu.SlideMenuMainActivity;
import com.scxh.android1503.store.db.MyCursorActivity;
import com.scxh.android1503.store.db.MyDBActivity;
import com.scxh.android1503.store.file.FileManagerActivity;
import com.scxh.android1503.store.preferce.SharePreferenceActivity;
import com.scxh.android1503.ui.adapter.ArrayAdapterActivity;
import com.scxh.android1503.ui.adapter.MerchantActivity;
import com.scxh.android1503.ui.adapter.MessageListViewActivity;
import com.scxh.android1503.ui.adapter.MyMessageAdapterActivity;
import com.scxh.android1503.ui.adapter.MySimpleAdapterActivity;
import com.scxh.android1503.ui.adapter.SimpleAdapterActivity;
import com.scxh.android1503.ui.adapter.gridview.GridViewActivity;
import com.scxh.android1503.ui.adapter.spinner.SpinnerActivity;
import com.scxh.android1503.ui.adapter.viewpager.ViewPagerActivity;
import com.scxh.android1503.ui.custom.CustomMainActivity;
import com.scxh.android1503.ui.dialog.DialogActivity;
import com.scxh.android1503.ui.layout.LayoutActivity;
import com.scxh.android1503.ui.listview.refresh.PageListActivity;
import com.scxh.android1503.ui.menu.MenuActionBarActivity;
import com.scxh.android1503.ui.menu.MenuContextActivity;
import com.scxh.android1503.ui.menu.MenuOptionActivity;
import com.scxh.android1503.ui.popwindow.PopWindowActivity;
import com.scxh.android1503.ui.progressbar.ProgressBarActivity;
import com.scxh.android1503.ui.scroll.ScroListViewOneActivity;
import com.scxh.android1503.ui.shape.ShapeActivity;
import com.scxh.android1503.ui.tab.MyRadioTabActivity;
import com.scxh.android1503.ui.tab.MyTabActivity;
import com.scxh.android1503.ui.webview.MyWebViewActivity;
import com.scxh.android1503.ui.widget.ButtonActivity;
import com.scxh.android1503.ui.widget.CheckBoxActivity;
import com.scxh.android1503.ui.widget.EditTextActivity;
import com.scxh.android1503.ui.widget.ImageViewActvity;
import com.scxh.android1503.ui.widget.LogingActivity;
import com.scxh.android1503.ui.widget.RadioActivity;
import com.scxh.android1503.ui.widget.TextViewActivity;
import com.scxh.android1503.ui.widget.ToggleSwitchButonActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		ActionBar bar = getActionBar();
//		bar.hide();
		
		Log.v("tag", "MainActivity onCreate >>>>>>>>");
		List<Map<String, Object>> data = getData();
		String[] str = new String[] { "title" };
		
		
		setListAdapter(new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_1, str,
				new int[] { android.R.id.text1 }));
		
		
		Log.v("tag", "data.size() :"+data.size());
		
		setSelection(data.size()-1);
		
		
		
	}

	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		addItem(data, "布局学习", LayoutActivity.class);
		addItem(data, "控件TextView", TextViewActivity.class);
		addItem(data, "控件Button", ButtonActivity.class);
		addItem(data, "控件EditText和生命周期", EditTextActivity.class);
		addItem(data, "控件ImageView", ImageViewActvity.class);
		addItem(data, "Android事件学习", EventActivity.class);
		addItem(data, "单选控件", RadioActivity.class);
		addItem(data, "多选控件", CheckBoxActivity.class);
		addItem(data, "开关控件", ToggleSwitchButonActivity.class);
		addItem(data, "Android调试工具_LogCat", LogCatActivity.class);
		addItem(data, "Android状态保存", SaveStateActivity.class);
		addItem(data, "登录 ", LogingActivity.class);
		addItem(data, "启动模式 ", com.scxh.android1503.lauchemode.FirstActivity.class);
		addItem(data, "Activity参数传递 ", com.scxh.android1503.parameter.FirstActivity.class);
		addItem(data, "Intent意图 ", ListIntentActivity.class);
		addItem(data, "ArrayAdapter适配器", ArrayAdapterActivity.class);
		addItem(data, "SimpleAdapter适配器", SimpleAdapterActivity.class);
		addItem(data, "MySimpleAdapterActivity适配器", MySimpleAdapterActivity.class);
		addItem(data, "MyMessageAdapterActivity适配器", MyMessageAdapterActivity.class);
		addItem(data, "Spinner", SpinnerActivity.class);
		addItem(data, "GridViewActivity", GridViewActivity.class);
		addItem(data, "ViewPagerActivity", ViewPagerActivity.class);
		addItem(data, "ProgressBarActivity", ProgressBarActivity.class);
		addItem(data, "DialogActivity", DialogActivity.class);
		addItem(data, "MyTabActivity", MyTabActivity.class);
		addItem(data, "RadioTabActivity", MyRadioTabActivity.class);
		addItem(data, "MenuOptionActivity", MenuOptionActivity.class);
		addItem(data, "MenuContextActivity", MenuContextActivity.class);
		addItem(data, "MenuActionBarActivity", MenuActionBarActivity.class);
		addItem(data, "ShapeActivity", ShapeActivity.class);
		addItem(data, "PopWindowActivity", PopWindowActivity.class);
		addItem(data, "HandlerActivity", HandlerActivity.class);
		addItem(data, "滚动事件ScroListViewOneActivity", ScroListViewOneActivity.class);
		addItem(data, "数据库 MyDBActivity", MyDBActivity.class);
		addItem(data, "数据库 MyCursorActivity", MyCursorActivity.class);
		addItem(data, "SharePreferenceActivity", SharePreferenceActivity.class);
		addItem(data, "FileManagerActivity", FileManagerActivity.class);
		addItem(data, "Mp3Activity", Mp3Activity.class);
		addItem(data, "Mp3ListActivity", Mp3ListActivity.class);
		addItem(data, "ServcieActivity", TestServcieActivity.class);
		addItem(data, "MusicPlayerList", MusicPlayerList.class);
		addItem(data, "MyBinderActivity", MyBinderActivity.class);
		addItem(data, "MessageListViewActivity", MessageListViewActivity.class);
		addItem(data, "MyBroadCastActivity", MyBroadCastActivity.class);
		addItem(data, "NotificationActivity", NotificationActivity.class);
		addItem(data, "AsyncTaskActivity", AsyncTaskActivity.class);
		addItem(data, "LoadPictureActivity", LoadPictureActivity.class);
		addItem(data, "GridViewAsyncTaskActivity", GridViewAsyncTaskActivity.class);
		addItem(data, "MyWebViewActivity", MyWebViewActivity.class);
		addItem(data, "网络基础HttpConnectActivity", HttpConnectActivity.class);
		addItem(data, "商家Json解析MerchantActivity", MerchantActivity.class);
		addItem(data, "分页XListView应运PageListActivity", PageListActivity.class);
		addItem(data, "MainFragmentActivity", MainFragmentActivity.class);
		addItem(data, "LifeMainActivity", LifeMainActivity.class);
		addItem(data, "ArgmentsMainActivity", ArgmentsMainActivity.class);
		addItem(data, "ParamentFramgentToActivity", ParamentFramgentToActivity.class);
		addItem(data, "StackMainActivity", StackMainActivity.class);
		addItem(data, "ListFragmentMainActivity", ListFragmentMainActivity.class);
		addItem(data, "TabMainActivity", TabMainActivity.class);
		addItem(data, "RelpaceMainActivity", RelpaceMainActivity.class);
		addItem(data, "ViewPagerMainActivity", ViewPagerMainActivity.class);
		addItem(data, "ImageLoaderMainActivity", ImageLoaderMainActivity.class);
		addItem(data, "CustomMainActivity", CustomMainActivity.class);
		addItem(data, "SlideMenuMainActivity", SlideMenuMainActivity.class);
		addItem(data, "网络字符缓存HttpCacheActivity", HttpCacheActivity.class);
		addItem(data, "VolleyMainActivity", VolleyMainActivity.class);

		return data;
	}

	public void addItem(List<Map<String, Object>> data, String name, Class<?> c) {
		addItem(data, name, new Intent(this, c));
	}

	protected void addItem(List<Map<String, Object>> data, String name,
			Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) l
				.getItemAtPosition(position);

		Intent intent = (Intent) map.get("intent");
		startActivity(intent);

	}

	@Override
	protected void onStart() {
		super.onStart();
//		Log.d("tag", "MainActivity onStart >>>>>>>>");
	}

	@Override
	protected void onResume() {
		super.onResume();
//		Log.i("tag", "MainActivity onResume >>>>>>>");
	}

	@Override
	protected void onPause() {
		super.onPause();
//		Log.w("tag", "MainActivity onPause >>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
//		Log.e("tag", "MainActivity onStop >>>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		Log.v("tag", "MainActivity onDestroy >>>>>>>>>");
	}
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK){
//			LoginAlertDialog dialog = new LoginAlertDialog(this);
//			dialog.show();
//			return true;
//		}else{
//			return super.onKeyDown(keyCode, event);
//		}
//	}
	
}
