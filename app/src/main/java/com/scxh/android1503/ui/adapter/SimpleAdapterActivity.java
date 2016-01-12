package com.scxh.android1503.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.warmtel.expandtab.ExpandPopTabView;
import com.warmtel.expandtab.KeyValueBean;
import com.warmtel.expandtab.PopOneListView;
import com.warmtel.expandtab.PopTwoListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleAdapterActivity extends Activity implements OnItemClickListener{
	private ListView mListView;
	private ExpandPopTabView mExpandPopTabView;
	private List<KeyValueBean> mPriceLists; //价格
	private List<KeyValueBean> mSortLists;  //排序
	private List<KeyValueBean> mFavorLists; //优惠

	private List<KeyValueBean> mParentLists = new ArrayList<>();//父区域
	private List<ArrayList<KeyValueBean>> mChildrenListLists = new ArrayList<>();//子区
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_simple_adapter_layout);

		mListView = (ListView) findViewById(R.id.simple_adapter_listview);
		mExpandPopTabView = (ExpandPopTabView) findViewById(R.id.expand_pop_tabview);

		ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
		setData(listData);
		
		String[] from = { "icon", "title", "content" };
		int[] to = { R.id.simple_icon_img, R.id.simple_title_txt,R.id.simple_content_txt };
		SimpleAdapter adapter = new SimpleAdapter(this, listData,
				R.layout.adapter_simple_item1_layout, from, to);

		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(this);

		setData();

		addItem(mExpandPopTabView, mPriceLists, "4000元/m^2", "价格");
		addItem(mExpandPopTabView, mSortLists, "", "排序");
		addItem(mExpandPopTabView, mPriceLists, "", "距离");

//		addItem(mExpandPopTabView, mParentLists,mChildrenListLists, "武侯区","红牌楼","区域");
		addItem(mExpandPopTabView, mParentLists,mChildrenListLists, "","","区域");
	}
	public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> lists, String defaultSelect, String defaultShowText) {
		PopOneListView popOneListView = new PopOneListView(this);
		popOneListView.setDefaultSelectByValue(defaultSelect);
		//popViewOne.setDefaultSelectByKey(defaultSelect);
		popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener() {
			@Override
			public void getValue(String key, String value) {
				//弹出框选项点击选中回调方法
				Toast.makeText(SimpleAdapterActivity.this,""+value,Toast.LENGTH_SHORT).show();
			}
		});
		expandTabView.addItemToExpandTab(defaultShowText, popOneListView);

	}

	public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> parentLists,
						List<ArrayList<KeyValueBean>> childrenListLists, String defaultParentSelect, String defaultChildSelect, String defaultShowText) {
		PopTwoListView popTwoListView = new PopTwoListView(this);
//		popTwoListView.setDefaultSelectByValue(defaultParentSelect, defaultChildSelect);
		//distanceView.setDefaultSelectByKey(defaultParent, defaultChild);
		popTwoListView.setCallBackAndData(expandTabView, parentLists, childrenListLists, new PopTwoListView.OnSelectListener() {
			@Override
			public void getValue(String showText, String parentKey, String childrenKey) {
				//弹出框选项点击选中回调方法
				Toast.makeText(SimpleAdapterActivity.this,""+showText,Toast.LENGTH_SHORT).show();
			}
		});
		expandTabView.addItemToExpandTab(defaultShowText, popTwoListView);
	}

	public void setData(){
		mPriceLists = new ArrayList<>();
		KeyValueBean keyValueBean = new KeyValueBean();
		keyValueBean.setKey("1");
		keyValueBean.setValue("不限");
		mPriceLists.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("2");
		keyValueBean.setValue("4000元/m^2");
		mPriceLists.add(keyValueBean);


		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("3");
		keyValueBean.setValue("5050元/m^2");
		mPriceLists.add(keyValueBean);


		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("4");
		keyValueBean.setValue("6000元/m^2");
		mPriceLists.add(keyValueBean);
//==============================================

		mSortLists = new ArrayList<>();
		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("1");
		keyValueBean.setValue("销量");
		mSortLists.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("2");
		keyValueBean.setValue("价格");
		mSortLists.add(keyValueBean);


		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("3");
		keyValueBean.setValue("评价");
		mSortLists.add(keyValueBean);


		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("4");
		keyValueBean.setValue("搜索");
		mSortLists.add(keyValueBean);



		mFavorLists = mPriceLists;

		//===================
		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("11");
		keyValueBean.setValue("武侯区");
		mParentLists.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("12");
		keyValueBean.setValue("青羊区");
		mParentLists.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("13");
		keyValueBean.setValue("金牛区");
		mParentLists.add(keyValueBean);


		ArrayList whList = new ArrayList<KeyValueBean>();
		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("111");
		keyValueBean.setValue("高升桥");
		whList.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("112");
		keyValueBean.setValue("红牌楼");
		whList.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("113");
		keyValueBean.setValue("玉林");
		whList.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("114");
		keyValueBean.setValue("双楠");
		whList.add(keyValueBean);


		ArrayList qyList = new ArrayList<KeyValueBean>();
		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("211");
		keyValueBean.setValue("宽窄巷子");
		qyList.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("212");
		keyValueBean.setValue("春熙路");
		qyList.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("313");
		keyValueBean.setValue("青羊宫");
		qyList.add(keyValueBean);

		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("214");
		keyValueBean.setValue("天府广场");
		qyList.add(keyValueBean);


		ArrayList chList = new ArrayList<KeyValueBean>();
		keyValueBean = new KeyValueBean();
		keyValueBean.setKey("311");
		keyValueBean.setValue("音乐公园");
		chList.add(keyValueBean);


		mChildrenListLists.add(whList);
		mChildrenListLists.add(qyList);
		mChildrenListLists.add(chList);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mExpandPopTabView != null){
			mExpandPopTabView.onExpandPopView();
		}
	}

	public void setData(ArrayList<HashMap<String, Object>> listData){
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list1);
		item.put("title", "1【多店通用】乡村基");
		item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
		listData.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list2);
		item.put("title", "2【多店通用】廖记棒棒鸡");
		item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list3);
		item.put("title", "3【5店通用】九锅一堂");
		item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list4);
		item.put("title", "4【幸福大道】囧囧串串");
		item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(item);
		
		
		item.put("icon", R.drawable.list1);
		item.put("title", "5【多店通用】乡村基");
		item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
		listData.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list2);
		item.put("title", "6【多店通用】廖记棒棒鸡");
		item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list3);
		item.put("title", "7【5店通用】九锅一堂");
		item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.list4);
		item.put("title", "8【幸福大道】囧囧串串");
		item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
		listData.add(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		LinearLayout v = (LinearLayout)view;
//		TextView titleTxt = (TextView) v.findViewById(R.id.simple_title_txt);
		
		TextView titleTxt = (TextView) ((LinearLayout)v.getChildAt(1)).getChildAt(0);
		
//		Toast.makeText(this, titleTxt.getText(), Toast.LENGTH_SHORT).show();
		
		SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();
		HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItem(position);
		String content = (String) item.get("content");
		
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}
}
