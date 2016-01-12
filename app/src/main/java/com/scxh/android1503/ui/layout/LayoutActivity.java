package com.scxh.android1503.ui.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android1503.ui.layout.CodeLayoutActivity;
import com.scxh.android1503.ui.layout.FramelayoutActivity;
import com.scxh.android1503.ui.layout.GridlayoutActivity;
import com.scxh.android1503.ui.layout.LinearlayoutActivity;
import com.scxh.android1503.ui.layout.RelativelayoutActivity;
import com.scxh.android1503.ui.layout.TablelayoutActivity;

public class LayoutActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<Map<String, Object>> data = getData();

		setListAdapter(new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));

	}

	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		addItem(data, "线型布局", LinearlayoutActivity.class);
		addItem(data, "相对布局", RelativelayoutActivity.class);
		addItem(data, "单帧布局", FramelayoutActivity.class);
		addItem(data, "网络布局", GridlayoutActivity.class);
		addItem(data, "表格布局", TablelayoutActivity.class);
		addItem(data, "代码布局", CodeLayoutActivity.class);
		
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
		Map<String, Object> map = (Map<String, Object>) l .getItemAtPosition(position);

		Intent intent = (Intent) map.get("intent");
		startActivity(intent);

	}
}
