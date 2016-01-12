package com.scxh.android1503.store.db;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1503.R;

public class MyCursorActivity extends ListActivity {
	private SQLiteDatabase mDb;
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mListView = getListView();
		
		UserDBHelper dbHelper = new UserDBHelper(this);
		mDb = dbHelper.getReadableDatabase();
		Cursor cursor = mDb.query(DataColumns.TearchTable.TABLE_NAME, null, null, null,null, null, null);
		
		MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
		mListView.setAdapter(adapter);
	}
	
	public class MyCursorAdapter extends CursorAdapter{
		private LayoutInflater layoutInflater;
		public MyCursorAdapter(Context context, Cursor c) {
			super(context, c, true);
			layoutInflater =LayoutInflater.from(context);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return layoutInflater.inflate(R.layout.db_item1_layout, null);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView nameTxt = (TextView) view.findViewById(R.id.db_item_name);
			TextView sexTxt = (TextView) view.findViewById(R.id.db_item_sex);
			
			String name = cursor.getString(cursor.getColumnIndex(DataColumns.TearchTable.COLUMN_NAME_NAME));
			String sex = cursor.getString(cursor.getColumnIndex(DataColumns.TearchTable.COLUMN_NAME_SEX));
			
			nameTxt.setText(name);
			sexTxt.setText(sex);
			
		}
		
	}
	
	
}
