package com.scxh.android1503.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;

public class ArrayAdapterActivity extends Activity implements
		OnItemClickListener {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.adapter_arrayadapter_layout);
		mListView = (ListView) findViewById(R.id.array_adapter_listview);

		String[] array = { "张三", "李四", "王二", "麻子", "赵钱" };

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// R.layout.adapter_array_item1_layout, array);

//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, array);

		MyArrayBaseAdapter adapter = new MyArrayBaseAdapter(this, array);
		
		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(this);



	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView text = (TextView) view;

		Toast.makeText(ArrayAdapterActivity.this,
				"position " + position + " ," + text.getText(),
				Toast.LENGTH_SHORT).show();

	}

	public class MyArrayBaseAdapter extends BaseAdapter {
		private String[] arrays;
		private Context context;
		public MyArrayBaseAdapter(Context context,String[] array) {
			this.context = context;
			this.arrays = array;
			
		}

		@Override
		public int getCount() {
			return arrays.length;
		}

		@Override
		public Object getItem(int position) {
			return arrays[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView titleTxt = new TextView(context);
			titleTxt.setText((String)getItem(position));
			return titleTxt;
		}

	}
}
