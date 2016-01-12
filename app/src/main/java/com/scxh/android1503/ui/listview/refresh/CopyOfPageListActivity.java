package com.scxh.android1503.ui.listview.refresh;//package com.scxh.android1503.ui.listview.refresh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.scxh.android1503.R;
import com.scxh.android1503.util.HttpConnectionUtil;
import com.scxh.android1503.util.HttpConnectionUtil.HttpCallBack;
import com.scxh.android1503.util.HttpConnectionUtil.Method;
import com.scxh.android1503.util.Logs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CopyOfPageListActivity extends Activity implements OnScrollListener{
	private ListView mListView;
	private ProgressBar mProgressBar;
	private PageListAdapter adapter;
	private int currentPage = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_list_view_layout);
		mListView = (ListView) findViewById(R.id.common_listview);
		mProgressBar = (ProgressBar) findViewById(R.id.common_progressbar);

		adapter = new PageListAdapter(this);
		mListView.setAdapter(adapter);

		mListView.setEmptyView(mProgressBar);//当前listview适配器数据为空的时候显示此View(mProgressBar)
		mListView.setOnScrollListener(this);

		setDataByAsyncHttpClient(currentPage);
	}

	public void setDataByHttpConnectionUtil() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageNo", "1");
		params.put("pageSize", "20");
		new HttpConnectionUtil().asyncTaskHttps(
				"http://192.168.1.145/app/page", Method.GET, params,
				new HttpCallBack() {

					@Override
					public void returnMessage(String message) {
						ArrayList<String> list = new ArrayList<String>();
						try {
							JSONObject response = new JSONObject(message);
							JSONArray contentJson = response
									.getJSONArray("content");
							for (int i = 0; i < contentJson.length(); i++) {
								JSONObject msgObject = contentJson
										.getJSONObject(i);
								String msg = msgObject.getString("msg");
								Logs.v("msg :" + msg);
								list.add(msg);
							}

							adapter.setListData(list);
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				});
	}

	public void setDataByAsyncHttpClient(int pageNo) {
		RequestParams params = new RequestParams();
		params.put("pageNo", String.valueOf(pageNo));
		params.put("pageSize", "20");

//		AsyncHttpClient client = new AsyncHttpClient();
//		client.get("http://192.168.1.145/app/page", params,
//				new JsonHttpResponseHandler() {
//					@Override
//					public void onSuccess(int statusCode, Header[] headers,JSONObject response) {
//						Logs.v("statusCode :" + statusCode);
//						ArrayList<String> list = new ArrayList<String>();
//						try {
//							JSONArray contentJson = response.getJSONArray("content");
//							for (int i = 0; i < contentJson.length(); i++) {
//								JSONObject msgObject = contentJson.getJSONObject(i);
//								String msg = msgObject.getString("msg");
//								Logs.v("msg :" + msg);
//								list.add(msg);
//							}
//							adapter.addListData(list);
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
//					}
//
//					@Override
//					public void onFailure(int statusCode, Header[] headers,
//							Throwable throwable, JSONObject errorResponse) {
//						Logs.v("onFailure >>>>>>>>>>>>>statusCode ：  "+statusCode);
//						Toast.makeText(CopyOfPageListActivity.this, statusCode+"服务器出错！", Toast.LENGTH_SHORT).show();
//					}
//
//				});
	}

	public class PageListAdapter extends BaseAdapter {
		private ArrayList<String> list = new ArrayList<String>();
		private LayoutInflater layoutInflater;

		public PageListAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
		}

		public void setListData(ArrayList<String> list) {
			this.list = list;
			notifyDataSetChanged();
		}
		public void addListData(ArrayList<String> list){
			this.list.addAll(list);
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
			if (convertView == null) {
				v = layoutInflater.inflate(android.R.layout.simple_list_item_1,
						null);
			} else {
				v = convertView;
			}

			String msg = (String) getItem(position);

			TextView msgTxt = (TextView) v;
			msgTxt.setText(msg);

			return v;
		}
	}

	private boolean isLastItemFlag = false;
	/**
	 * 当Listview滚动状态改变时执行
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		Logs.v("onScrollStateChanged >>>>scrollState : "+scrollState);
		if(isLastItemFlag && SCROLL_STATE_IDLE == scrollState){
			//加载下一页数据

//			if(++currentPage>)

			setDataByAsyncHttpClient(++currentPage);
			isLastItemFlag = false;
		}
	}
	/**
	 * 当Listview滚动时执行onscroll方法，
	 * firstVisibleItem：表示界面显示第一个数据项序号
	 * visibleItemCount:表示界面可见数据项个数，
	 * totalItemCount：表示listview适配器总记录数
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
		Logs.e("onScroll (firstVisibleItem + visibleItemCount) :"+(firstVisibleItem + visibleItemCount) + " ,totalItemCount :"+totalItemCount);
		if(((firstVisibleItem + visibleItemCount) == totalItemCount)  && totalItemCount > 0){
			isLastItemFlag = true;
		}
	}
}
