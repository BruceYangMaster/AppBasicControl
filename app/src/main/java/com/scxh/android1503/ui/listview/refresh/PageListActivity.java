package com.scxh.android1503.ui.listview.refresh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;
import com.scxh.android1503.util.HttpConnectionUtil;
import com.scxh.android1503.util.HttpConnectionUtil.HttpCallBack;
import com.scxh.android1503.util.HttpConnectionUtil.Method;
import com.scxh.android1503.util.Logs;
import com.warmtel.android.xlistview.XListView;
import com.warmtel.android.xlistview.XListView.IXListViewListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PageListActivity extends Activity {
	private XListView mListView;
	private ProgressBar mProgressBar;
	private PageListAdapter adapter;
	private int currentPage = 1;
	private int mPageCount; // 总页数
	private SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_list_view_layout);
		mListView = (XListView) findViewById(R.id.common_listview);
		mProgressBar = (ProgressBar) findViewById(R.id.common_progressbar);
		adapter = new PageListAdapter(this);
		mListView.setAdapter(adapter);

		mListView.setEmptyView(mProgressBar);// 当前listview适配器数据为空的时候显示此View(mProgressBar)

		setDataByHttpConnectionUtil(currentPage);

		mListView.setPullLoadEnable(true);  //控制上拉加载更多是否可用  true可用 false不可用
		mListView.setPullRefreshEnable(true);  //控制下拉刷新是否可用 true可用 false不可用

		mListView.setXListViewListener(new IXListViewListener() {

			/**
			 * 下拉刷新
			 */
			@Override
			public void onRefresh() {
				Logs.v("下拉刷新>>>>");
				currentPage = 1;
				setDataByHttpConnectionUtil(currentPage);
			}

			/**
			 * 上拉加载更多
			 */
			@Override
			public void onLoadMore() {
				Logs.v("上拉加载更多>>>>");
				if (++currentPage > mPageCount) {
					currentPage = mPageCount;
					// 当加载到最后一页时，隐藏加载更多
					mListView.setPullLoadEnable(false);
					Toast.makeText(PageListActivity.this, "已加载到最后一页", Toast.LENGTH_SHORT).show();
				} else {
					setDataByHttpConnectionUtil(currentPage);
				}
			}
		});

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(PageListActivity.this,"position :"+position,Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 从网络异步获取数据，刷新适配器
	 * @param pageNo
	 */
	public void setDataByHttpConnectionUtil(int pageNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageNo", String.valueOf(pageNo));
		params.put("pageSize", "20");
		new HttpConnectionUtil().asyncTaskHttps(
				Constances.BASE_URL+"/app/page", Method.GET, params,
				new HttpCallBack() {

					@Override
					public void returnMessage(String message) {
						Gson gson = new Gson();
						PagerBean pager = gson.fromJson(message, PagerBean.class);
						ArrayList<MsgBean> listmsg = (ArrayList<MsgBean>) pager.getContent();
						mPageCount = pager.getPagecount();
						setXListViewResponseEnd(listmsg);
					}
				});
	}

	public void setXListViewResponseEnd(ArrayList<MsgBean> listmsg){
		adapter.setListData(currentPage, listmsg);
		mListView.setRefreshTime(format.format(new Date(System.currentTimeMillis())));
		mListView.stopRefresh(); //停止刷新
		mListView.stopLoadMore(); //停止加载更多
	}

	public class PageListAdapter extends BaseAdapter {
		private ArrayList<MsgBean> adpaterList = new ArrayList<MsgBean>();
		private LayoutInflater layoutInflater;

		public PageListAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
		}

		public void setListData(int currentPager,ArrayList<MsgBean> list) {
			if(currentPager == 1){
				this.adpaterList = list;
			}else{
				this.adpaterList.addAll(list);
			}
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			return adpaterList.size();
		}

		@Override
		public Object getItem(int position) {
			return adpaterList.get(position);
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

			MsgBean msg = (MsgBean) getItem(position);

			TextView msgTxt = (TextView) v;
			msgTxt.setText(msg.getMsg());

			return v;
		}
	}

}
