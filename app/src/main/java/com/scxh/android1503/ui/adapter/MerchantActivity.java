package com.scxh.android1503.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.http.MerchantBean;
import com.scxh.android1503.util.Constances;
import com.scxh.android1503.util.HttpConnectionUtil;
import com.scxh.android1503.util.HttpConnectionUtil.HttpCallBack;
import com.scxh.android1503.util.HttpConnectionUtil.Method;
import com.scxh.android1503.util.Logs;
import com.warmtel.imagecache.AsyncMemoryFileCacheImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MerchantActivity extends Activity {
	private ListView mListView;
	private MerchantAdapter mMerchantAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_merchant_layout);
		mListView = (ListView) findViewById(R.id.merchant_listview);

		mMerchantAdapter = new MerchantAdapter(this);
		mListView.setAdapter(mMerchantAdapter);

		getJsonStrByNet();

	}

	public void getJsonStrByNet() {
		/** 从网络获取请求json数据 */
		new HttpConnectionUtil().asyncTaskHttp(
				Constances.BASE_URL+"/app/merchant?jsonName=around", Method.GET,
				new HttpCallBack() {
					@Override
					public void returnMessage(String message) {
						Logs.v("message :" + message);
						ArrayList<MerchantBean> merchantList = parseJsonToMerchantList(message);
						mMerchantAdapter.setListData(merchantList);

					}
				});
	}

	/**
	 * 解析Json字符串, 构造ListView数据源
	 *
	 * @return
	 */
	public ArrayList<MerchantBean> parseJsonToMerchantList(String message) {
		ArrayList<MerchantBean> merchantList = new ArrayList<MerchantBean>();
		try {
			JSONObject jsonRoot = new JSONObject(message);
			JSONObject jsonInfo = jsonRoot.getJSONObject("info");
			JSONArray jsonMerchatArray = jsonInfo.getJSONArray("merchantKey");
			int length = jsonMerchatArray.length();

			for (int i = 0; i < length; i++) {
				JSONObject jsonItem = jsonMerchatArray.getJSONObject(i);
				String name = jsonItem.getString("name");
				String coupon = jsonItem.getString("coupon");
				String location = jsonItem.getString("location");
				String distance = jsonItem.getString("distance");
				String picUrl = jsonItem.getString("picUrl");
				String couponType = jsonItem.getString("couponType"); // 券
				String cardType = jsonItem.getString("cardType"); // 卡
				String groupType = jsonItem.getString("groupType"); // 团

				MerchantBean merchant = new MerchantBean();
				merchant.setName(name);
				merchant.setCoupon(coupon);
				merchant.setLocation(location);
				merchant.setDistance(distance);
				merchant.setPicUrl(picUrl);
				merchant.setCardType(cardType);
				merchant.setCouponType(couponType);
				merchant.setGroupType(groupType);

				merchantList.add(merchant);

				Logs.v("name :" + name + ",coupon :" + coupon);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return merchantList;
	}

	public class MerchantAdapter extends BaseAdapter {
		private ArrayList<MerchantBean> merchantList = new ArrayList<MerchantBean>();
		private LayoutInflater layoutInflater;
		private Context context;
		public MerchantAdapter(Context context) {
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
		}

		public void setListData(ArrayList<MerchantBean> list) {
			this.merchantList = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return merchantList.size();
		}

		@Override
		public Object getItem(int position) {
			return merchantList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			final ViewHodler holder;
			if (convertView == null) {
				v = layoutInflater.inflate(R.layout.view_merchat_item_layout,
						null);

				holder = new ViewHodler();
				holder.iconImg = (ImageView) v
						.findViewById(R.id.merchant_icon_img);
				holder.nameTxt = (TextView) v
						.findViewById(R.id.merchant_name_txt);
				holder.couponTxt = (TextView) v
						.findViewById(R.id.merchant_coupon_txt);
				holder.loactionTxt = (TextView) v
						.findViewById(R.id.merchant_loaction_txt);
				holder.distanceTxt = (TextView) v
						.findViewById(R.id.merchant_distance_txt);
				holder.cardImg = (ImageView) v
						.findViewById(R.id.merchant_card_img);
				holder.groupImg = (ImageView) v
						.findViewById(R.id.merchant_group_img);
				holder.conponImg = (ImageView) v
						.findViewById(R.id.merchant_counp_img);

				v.setTag(holder);
			} else {
				v = convertView;
				holder = (ViewHodler) v.getTag();
			}

			MerchantBean merchant = (MerchantBean) getItem(position);

			AsyncMemoryFileCacheImageLoader.getInstance(context).loadBitmap(getResources(), merchant.getPicUrl(),  holder.iconImg);

			holder.nameTxt.setText(merchant.getName());
			holder.couponTxt.setText(merchant.getCoupon());
			holder.loactionTxt.setText(merchant.getLocation());
			holder.distanceTxt.setText(merchant.getDistance());

			if (merchant.getCardType().equalsIgnoreCase("YES")) {
				holder.cardImg.setVisibility(View.VISIBLE);
			} else {
				holder.cardImg.setVisibility(View.GONE);
			}

			if (merchant.getGroupType().equalsIgnoreCase("YES")) {
				holder.groupImg.setVisibility(View.VISIBLE);
			} else {
				holder.groupImg.setVisibility(View.GONE);
			}

			if (merchant.getCouponType().equalsIgnoreCase("YES")) {
				holder.conponImg.setVisibility(View.VISIBLE);
			} else {
				holder.conponImg.setVisibility(View.GONE);
			}
			return v;
		}

		public class ViewHodler {
			ImageView iconImg; // 图标
			TextView nameTxt; // 标题
			TextView couponTxt; // 打折信息
			TextView loactionTxt; // 地址
			TextView distanceTxt; // 距离
			ImageView cardImg; // 卡
			ImageView groupImg; // 团
			ImageView conponImg; // 券
		}
	}


}
