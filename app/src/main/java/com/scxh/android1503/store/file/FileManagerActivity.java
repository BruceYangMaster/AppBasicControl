package com.scxh.android1503.store.file;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1503.R;

public class FileManagerActivity extends Activity implements
		OnItemClickListener {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.file_manger_layout);
		mListView = (ListView) findViewById(R.id.file_listview);
		mListView.setOnItemClickListener(this);

		File root = Environment.getExternalStorageDirectory();
		ArrayList<File> dataList = new ArrayList<File>();

		File[] fileList = root.listFiles();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				dataList.add(file);
			}
		}

		FileAdapter adapter = new FileAdapter(this);
		mListView.setAdapter(adapter);

		adapter.setData(dataList);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		FileAdapter adapter = (FileAdapter) parent.getAdapter();
		File parantFile = (File) adapter.getItem(position);

		if (parantFile.isDirectory()) {

			// parantFile.getParentFile();
			ArrayList<File> dataList = new ArrayList<File>();

			File[] fileList = parantFile.listFiles();
			if (fileList != null) {
				for (int i = 0; i < fileList.length; i++) {
					File file = fileList[i];
					dataList.add(file);
				}
			}
			adapter.setData(dataList);
		}else{
			if(parantFile.getName().endsWith("mp3")){
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setType("audio/mp3");
				startActivity(intent);
			}
		}
	}

	public class FileAdapter extends BaseAdapter {
		private ArrayList<File> dataList = new ArrayList<File>();
		private LayoutInflater layoutInflater;

		public FileAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
		}

		public void setData(ArrayList<File> dataList) {
			this.dataList = dataList;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView fileTxt;
			if (convertView == null) {
				convertView = layoutInflater.inflate(
						R.layout.file_manager_item_layout, null);

				fileTxt = (TextView) convertView
						.findViewById(R.id.file_manger_txt);
				convertView.setTag(fileTxt);
			} else {
				fileTxt = (TextView) convertView.getTag();
			}

			File file = (File) getItem(position);
			if (file.isDirectory()) {
				// 显示目录图片
			} else {
				// 显示文件图片
			}

			fileTxt.setText(file.getName());

			return convertView;
		}

	}

}
