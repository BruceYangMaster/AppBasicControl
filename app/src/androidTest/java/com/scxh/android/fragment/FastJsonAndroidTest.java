package com.scxh.android.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.scxh.android1503.dataparser.json.MerchantInfo;
import com.scxh.android1503.dataparser.json.MerchantRoot;
import com.scxh.android1503.dataparser.json.PageInfo;
import com.scxh.android1503.dataparser.json.UserBean;
import com.scxh.android1503.dataparser.json.UserRoot;
import com.scxh.android1503.http.MerchantBean;
import com.scxh.android1503.util.Logs;

/**
 * 1.什么JSON 2.JSON 规则 3.如何生成json字符串 4.解析json字符串
 * 
 * 注: 1. 转义符 \n \" \t \' \\ 2. android目录结构 : assets 读取文件，遍历文件 3. 乱码处理 4.
 * 字节流inputStream转字符串
 */
public class FastJsonAndroidTest extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void parserUserByFastJson() throws IOException {
		InputStream is = getContext().getAssets().open("user");
		String jsonStr = readIt(is);

		UserRoot userRoot = JSONObject.parseObject(jsonStr, UserRoot.class);
		UserBean user = userRoot.getUser();
		
		Logs.v("message :"+userRoot.getMessage()+",id :"+user.getId() + ", userName :"+user.getUserName()+",passWord :"+user.getPassword()+", email :"+user.getEmail());
	}

	public void parserMerchantByFastJson() throws IOException{
		InputStream is = getContext().getAssets().open("around");
		String jsonStr = readIt(is);
		Logs.v("jsonStr :"+jsonStr);
		
		MerchantRoot merchantRoot = JSONObject.parseObject(jsonStr, MerchantRoot.class);
		MerchantInfo info = merchantRoot.getInfo();
		ArrayList<MerchantBean>  list = info.getMerchantKey();
		for(MerchantBean merchant :list){
			Logs.v("name :"+merchant.getName()+",Coupon "+merchant.getCoupon());
		}
		
	}
	
	
	public void parserMerchantByGson() throws IOException{
		InputStream is = getContext().getAssets().open("around");
		String jsonStr = readIt(is);
		
		Gson gson = new Gson();
		
		MerchantRoot merchantRoot = gson.fromJson(jsonStr, MerchantRoot.class);
		
		MerchantInfo info = merchantRoot.getInfo();
		ArrayList<MerchantBean>  list = info.getMerchantKey();
		
		PageInfo pageInfo = info.getPageInfo();
		
		for(MerchantBean merchant :list){
			Logs.v("name :"+merchant.getName()+",Coupon "+merchant.getCoupon());
		}
	}
	
	/**
	 * 将InputStream转换成String返回
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public String readIt(InputStream stream) throws IOException,
			UnsupportedEncodingException {
		Reader reader = new InputStreamReader(stream, "UTF-8");
		// 创建包装流
		BufferedReader br = new BufferedReader(reader);
		// 定义String类型用于储存单行数据
		String line = null;
		// 创建StringBuffer对象用于存储所有数据
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		br.close();
		reader.close();

		return sb.toString();

	}

}
