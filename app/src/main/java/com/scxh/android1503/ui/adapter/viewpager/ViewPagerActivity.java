package com.scxh.android1503.ui.adapter.viewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class ViewPagerActivity extends Activity {
	private ViewPager mViewPager;
	private ImageView mPagerOneImg, mPagerTwoImg, mPagerThreeImg;

	/**
	 * 请求更新显示的View。
	 */
	protected static final int MSG_UPDATE_IMAGE = 1;
	/**
	 * 请求暂停轮播。
	 */
	protected static final int MSG_KEEP_SILENT = 2;
	/**
	 * 请求恢复轮播。
	 */
	protected static final int MSG_BREAK_SILENT = 3;
	/**
	 * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
	 * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
	 */
	protected static final int MSG_PAGE_CHANGED = 4;

	// 轮播间隔时间
	protected static final long MSG_DELAY = 2000;

	private int currentItem = 0;

	private Handler mHandler = new Handler() {
		boolean flag = true;
		public void handleMessage(Message msg) {
			// 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
			if (mHandler.hasMessages(MSG_UPDATE_IMAGE) && !flag) {
				mHandler.removeMessages(MSG_UPDATE_IMAGE);
				flag = false;
			}
			
			Logs.v("handleMessage >>>>msg.what   :"+msg.what);
			switch (msg.what) {
			case MSG_UPDATE_IMAGE:
				currentItem++;
				mViewPager.setCurrentItem(currentItem);
				// 准备下次播放
				mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
				break;
			case MSG_KEEP_SILENT:
				// 只要不发送消息就暂停了
				break;
			case MSG_BREAK_SILENT:
				mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
				break;
			case MSG_PAGE_CHANGED:
				// 记录当前的页号，避免播放的时候页面显示不正确。
				currentItem = msg.arg1;
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.adapter_viewpager_layout);

		mViewPager = (ViewPager) findViewById(R.id.pager_view);
		mPagerOneImg = (ImageView) findViewById(R.id.pager_one_image);
		mPagerTwoImg = (ImageView) findViewById(R.id.pager_two_image);
		mPagerThreeImg = (ImageView) findViewById(R.id.pager_three_image);

		LayoutInflater inflater = LayoutInflater.from(this);
		ArrayList<View> data = new ArrayList<View>();
		View v1 = inflater.inflate(R.layout.item_pager_view1_layout, null);
		View v2 = inflater.inflate(R.layout.item_pager_view2_layout, null);
		View v3 = inflater.inflate(R.layout.item_pager_view3_layout, null);
		data.add(v1);
		data.add(v2);
		data.add(v3);

		MyPagerAdapter adapter = new MyPagerAdapter();
		mViewPager.setAdapter(adapter);

		adapter.setData(data);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int postion) {
				Log.i("tag", "onPageSelected  " + postion);
				switch (postion % 3) {
				case 0:
					mPagerOneImg.setImageResource(R.drawable.page_now);
					mPagerTwoImg.setImageResource(R.drawable.page);
					mPagerThreeImg.setImageResource(R.drawable.page);
					break;
				case 1:
					mPagerOneImg.setImageResource(R.drawable.page);
					mPagerTwoImg.setImageResource(R.drawable.page_now);
					mPagerThreeImg.setImageResource(R.drawable.page);
					break;
				case 2:
					mPagerOneImg.setImageResource(R.drawable.page);
					mPagerTwoImg.setImageResource(R.drawable.page);
					mPagerThreeImg.setImageResource(R.drawable.page_now);
					break;
				}

				mHandler.sendMessage(Message.obtain(mHandler, MSG_PAGE_CHANGED,postion, 0));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int postion) {
				Logs.v("onPageScrollStateChanged >>>>> "+postion);
//				switch (postion) {
//				case ViewPager.SCROLL_STATE_DRAGGING:
//					mHandler.sendEmptyMessage(MSG_KEEP_SILENT);
//					break;
//				case ViewPager.SCROLL_STATE_IDLE:
//					mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
//					break;
//				default:
//					break;
//				}
			}
		});

		/**
		 * 2147483647 / 2 = 1073741820 - 1
		 * 设置ViewPager的当前项为一个比较大的数，以便一开始就可以左右循环滑动
		 */
		int n = Integer.MAX_VALUE / 2 % data.size();
		int currentPager = Integer.MAX_VALUE / 2 - n;

		mViewPager.setCurrentItem(currentPager);

		mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
	}

	/**
	 * ViewPager循环滑动
	 * 
	 */
	public class MyPagerAdapter extends PagerAdapter {
		private ArrayList<View> data = new ArrayList<View>();
	
		public void setData(ArrayList<View> data) {
			this.data = data;
			notifyDataSetChanged();
		}
	
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}
	
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
	
		}
	
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position = position % data.size(); // 0%3=0,1%3=1,2%3=2,3%3=0,4%3=1,5%3=2,6%3=0,........
	
			View v = data.get(position);
			if (v.getParent() != null) { //判断v对象有没有父View
				container.removeView(v);
			}
	
			container.addView(v);
	
			return v;
		}
	
	}

	/**
	 * ViewPager不循环滑动
	 * 
	 */
	public class MyPagerAdapters extends PagerAdapter {
		private ArrayList<View> data = new ArrayList<View>();
	
		public void setData(ArrayList<View> data) {
			this.data = data;
			notifyDataSetChanged();
		}
	
		@Override
		public int getCount() {
			return data.size();
		}
	
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(data.get(position));
		}
	
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View v = data.get(position);
			container.addView(v);
			return v;
		}
	}
}
