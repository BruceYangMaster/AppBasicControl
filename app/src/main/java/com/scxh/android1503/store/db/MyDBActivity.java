package com.scxh.android1503.store.db;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class MyDBActivity extends Activity {
	private Button mInsertBtn;
	private Button mFindBtn;
	private ListView mListView;
	private EditText mUserNameEdit;
	private SQLiteDatabase mDb;
	private TearchListViewAdapter mTearchListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.db_listview_layout);
		mInsertBtn = (Button) findViewById(R.id.db_insert_btn);
		mFindBtn = (Button) findViewById(R.id.db_find_btn);
		mListView = (ListView) findViewById(R.id.db_showtearch_listview);
		mUserNameEdit = (EditText) findViewById(R.id.db_username_eidt);
		UserDBHelper dbHelper = new UserDBHelper(MyDBActivity.this);
		mDb = dbHelper.getReadableDatabase();

		mTearchListViewAdapter = new TearchListViewAdapter(this);
		mListView.setAdapter(mTearchListViewAdapter);
		
		mInsertBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = mUserNameEdit.getText().toString();
				addTearch(name, "男");
			}
		});

		mFindBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ArrayList<Tearch> tearchs = findTearchLists();
				if(tearchs.size() > 0 ){
					mTearchListViewAdapter.setData(tearchs);
				}else{
					Toast.makeText(MyDBActivity.this, "没有查询到数据", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public class TearchListViewAdapter extends BaseAdapter {
		private ArrayList<Tearch> listData = new ArrayList<Tearch>();
		private LayoutInflater layoutInflater;
		public TearchListViewAdapter(Context context){
			layoutInflater = LayoutInflater.from(context);
		}
		
		public void setData(ArrayList<Tearch> listData) {
			this.listData = listData;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			return listData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return listData.get(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if(convertView == null){
				convertView = layoutInflater.inflate(R.layout.db_item1_layout, null);
				viewHolder = new ViewHolder();
				viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.db_item_name);
				viewHolder.sexTxt = (TextView) convertView.findViewById(R.id.db_item_sex);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			Tearch tearch = (Tearch) getItem(position);
			
			viewHolder.nameTxt.setText(tearch.getName());
			viewHolder.sexTxt.setText(tearch.getSex());
			
			return convertView;
		}
		
		public class ViewHolder{
			TextView nameTxt;
			TextView sexTxt;
		}

	}

	/**
	 * 查询所有教师数据
	 * @return
	 */
	public ArrayList<Tearch> findTearchLists(){
		ArrayList<Tearch> dataList = new ArrayList<Tearch>();
		Cursor c = mDb.query(DataColumns.TearchTable.TABLE_NAME, null, null, null, null, null,null);
		if (c != null && c.getCount() > 0) {

			while (c.moveToNext()) {
				String name = c.getString(c.getColumnIndex(DataColumns.TearchTable.COLUMN_NAME_NAME));
				String sex = c.getString(c.getColumnIndex(DataColumns.TearchTable.COLUMN_NAME_SEX));

				Logs.v("name :" + name + " sex :" + sex);

				Tearch tearch = new Tearch();
				tearch.setName(name);
				tearch.setSex(sex);

				dataList.add(tearch);
			}
		}
		
		return dataList;
	}
	/**
	 * 添加一名教师数据
	 * @param name
	 * @param sex
	 */
	public void addTearch(String name, String sex) {
		ContentValues values = new ContentValues();
		values.put(DataColumns.TearchTable.COLUMN_NAME_NAME, name);
		values.put(DataColumns.TearchTable.COLUMN_NAME_SEX, sex);

		mDb.insert(DataColumns.TearchTable.TABLE_NAME, null, values);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDb == null) {
			mDb.close();
			mDb = null;
		}
	}
}
