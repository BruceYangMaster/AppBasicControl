package com.scxh.android1503.fragment.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class TabTwoFragment extends ListFragment {
    private String[] array = {"新闻", "科技", "娱乐"};
    private MyListAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new MyListAdapter(getActivity());
        setListAdapter(mAdapter);
        mAdapter.setData(array);
    }

    public class MyListAdapter extends BaseAdapter {
        private String[] arrays = new String[]{};
        private LayoutInflater inflater;

        public MyListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(String[] arrays) {
            this.arrays = arrays;
            notifyDataSetChanged();
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
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView textView = (TextView) convertView;
            textView.setText((String) getItem(position));

            return convertView;
        }
    }
}
