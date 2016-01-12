package com.scxh.android1503.fragment.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;
import com.scxh.android1503.util.Logs;

public class ImageLoaderMainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private String[] mImageUrls = Constances.imageThumbUrls;
    private ImageLoaerFragmentAdapter imageLoaerFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_loader_main_layout);
        mViewPager = (ViewPager) findViewById(R.id.imageloader_viewpager);

        imageLoaerFragmentAdapter = new ImageLoaerFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(imageLoaerFragmentAdapter);
        mViewPager.setOffscreenPageLimit(2); /**设置ViewPager预加载页数默认是加预加载1页*/
        imageLoaerFragmentAdapter.setDataList(mImageUrls);
    }

    public class ImageLoaerFragmentAdapter extends FragmentStatePagerAdapter {
        private String[] dataList = new String[]{};

        public ImageLoaerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setDataList(String[] list) {
            dataList = list;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            Logs.e("getItem>>>>>>>>>>>>>>>>>>position :"+position);
            return ImageLoaderFragment.newInstance(dataList[position]);
        }

        @Override
        public int getCount() {
            return dataList.length;
        }
    }
}
