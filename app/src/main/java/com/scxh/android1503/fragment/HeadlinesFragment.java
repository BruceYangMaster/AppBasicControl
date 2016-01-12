package com.scxh.android1503.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1503.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadlinesFragment extends Fragment implements AdapterView.OnItemClickListener{
    private String[] arrays = {"新闻","娱乐","科技"};
    private ListView mListView;
    private OnHeadlineSelectedListener mOnHeadlineSelectedListener;
    /**
     * 定义接口
     */
    public interface OnHeadlineSelectedListener{
        public void onArticleSelected(String msg);
    }
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if(context instanceof OnHeadlineSelectedListener){
            mOnHeadlineSelectedListener = (OnHeadlineSelectedListener)context;
        }else{
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
    public static Fragment newInstance(){
        HeadlinesFragment headlinesFragment = new HeadlinesFragment();
        return headlinesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_headlines_layout, container, false);
        mListView = (ListView) v.findViewById(R.id.headline_listview);
        HeadlineAdapter adapter = new HeadlineAdapter(getActivity());
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        adapter.setData(arrays);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HeadlineAdapter adapter = (HeadlineAdapter) parent.getAdapter();
        String item = (String) adapter.getItem(position);
//        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        if(mOnHeadlineSelectedListener != null) {
            mOnHeadlineSelectedListener.onArticleSelected(item);
        }
    }


    public class HeadlineAdapter extends BaseAdapter{
        private String[] arrays = new String[]{};
        private LayoutInflater inflater;
        public HeadlineAdapter(Context context){
            inflater = LayoutInflater.from(context);
        }
        public void setData(String[] arrays){
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
            if (convertView == null){
                convertView = inflater.inflate(android.R.layout.simple_list_item_1,null);
            }
            TextView textView = (TextView)convertView;
            textView.setText((String) getItem(position));

            return convertView;
        }
    }


}
