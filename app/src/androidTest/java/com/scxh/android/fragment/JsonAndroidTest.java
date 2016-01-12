package com.scxh.android.fragment;

import android.content.res.AssetManager;
import android.test.AndroidTestCase;

import com.scxh.android1503.util.HttpConnectionUtil;
import com.scxh.android1503.util.HttpConnectionUtil.HttpCallBack;
import com.scxh.android1503.util.HttpConnectionUtil.Method;
import com.scxh.android1503.util.Logs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
/**
 *  1.什么JSON
 *  2.JSON 规则
 *  3.如何生成json字符串
 *  4.解析json字符串
 *
 *  注:  1. 转义符   \n  \"  \t  \'   \\
 *      2. android目录结构 :  assets   读取文件，遍历文件
 *      3. 乱码处理
 *      4. 字节流inputStream转字符串
 */
public class JsonAndroidTest extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testjsonStr() throws JSONException, IOException {
		/**
		 * {"userName":"张三","passWord":123456};
		 * {"student":{"name":"小明","age":18,"id":112,"sex":"男"}} 
		 * {"students": [{"name":"小明","age":18,"id":112,"sex":"男"},{"name":"小王","age":17,"id":102,"sex":"女"} ] }
		 */

		String jsonStr = "{\"userName\":\"张三\",\"passWord\":123456}";

		JSONObject jsonObject = new JSONObject(jsonStr);
		String userName = jsonObject.getString("userName");
		int passWord = jsonObject.getInt("passWord");

		Logs.v("userName :" + userName + "   , passWord :" + passWord);
	}

	public void testjsonStundentstr() throws JSONException {
		String jsonStr = "{\"student\":{\"name\":\"小明\",\"age\":18,\"id\":112,\"sex\":\"男\"}}";
		JSONObject jsonObject = new JSONObject(jsonStr);

		JSONObject jsonStudent = jsonObject.getJSONObject("student");
		String name = jsonStudent.getString("name");
		int age = jsonStudent.getInt("age");

		Logs.v("name :" + name + ", age :" + age);
	}

	public void testJsonArray() throws JSONException {
		String str = "{\"students\":[{\"name\":\"小明\",\"age\":18,\"id\":112,\"sex\":\"男\"},{\"name\":\"小王\",\"age\":17,\"id\":102,\"sex\":\"女\"}]}";

		JSONObject jsonObject = new JSONObject(str);
		JSONArray ja = jsonObject.getJSONArray("students");

		int length = ja.length();
		for (int i = 0; i < length; i++) {
			JSONObject jobject = ja.getJSONObject(i);
			String name = jobject.getString("name");
			int age = jobject.getInt("age");
			Logs.v("name :" + name + ", age :" + age);
		}
	}

	public void readAssetsFile() throws IOException, JSONException {
		InputStream is = getContext().getAssets().open("students.txt");
		String str = readIt(is);
		Logs.v("str :"+str);
		
		JSONObject jsonObject = new JSONObject(str);
		JSONArray ja = jsonObject.getJSONArray("students");

		int length = ja.length();
		for (int i = 0; i < length; i++) {
			JSONObject jobject = ja.getJSONObject(i);
			String name = jobject.getString("name");
			int age = jobject.getInt("age");
			Logs.v("name :" + name + ", age :" + age);
		}
	}

	public void listAssetFile() throws IOException{
		 AssetManager  assetManager = getContext().getAssets();
		 String[] list = assetManager.list("");
		 for(String file:list){
			 Logs.v("file :"+file);
		 }
	}
	
	public void readRawFile() throws UnsupportedEncodingException, IOException{
		/*InputStream is = getContext().getResources().openRawResource(R.raw.jsonstr);
		String str = readIt(is);
		Logs.v("str :"+str);*/
	}
	
	public void readHttpFile(){
		
		HttpConnectionUtil connect = new HttpConnectionUtil();
		connect.asyncTaskHttp("http://192.168.1.156/json/students.txt", Method.GET, new HttpCallBack() {
			
			@Override
			public void returnMessage(String message) {
				Logs.v("message :"+message);
			}
		});
	}
	
	public void buildJsonObject() throws JSONException{
		//{"username":"张三","password":123456}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "张三");
		jsonObject.put("password", "123456");
		
		Logs.v("生成Json字符串         "+jsonObject.toString());
		
	}
	
	public void buildJsonArrayObject() throws JSONException{
		//{"students": [{"name":"小明","age":18,"id":112,"sex":"男"},{"name":"小王","age":17,"id":102,"sex":"女"} ] }
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("name", "小明");
		jsonObject1.put("age", 18);
		jsonObject1.put("id", 112);
		jsonObject1.put("sex", "男");
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("name", "小王");
		jsonObject2.put("age", 17);
		jsonObject2.put("id", 102);
		jsonObject2.put("sex", "女");
		
		JSONArray jsonArray= new JSONArray();
		jsonArray.put(0, jsonObject1);
		jsonArray.put(1, jsonObject2);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("students", jsonArray);
		
		Logs.v("生成Json字符串         "+jsonObject.toString());
		
	}
	
	public void buildJsonByHashMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "小明");
		map.put("password", "123456");
		
		//{"username":"张三","password":123456}
		
		JSONObject jsonObject = new JSONObject(map);
		Logs.v("生成Json字符串         "+jsonObject.toString());
		
	}
	
	/**
	 * 将InputStream转换成String返回
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public String readIt(InputStream stream) throws IOException,UnsupportedEncodingException {
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
	
	public void parserUserJson() throws IOException, JSONException{
		InputStream is = getContext().getAssets().open("user");
		String jsonStr = readIt(is);
		
		JSONObject rootJson = new JSONObject(jsonStr);
		
		String message = rootJson.getString("message");
		JSONObject userJson = rootJson.getJSONObject("user");
		
		int id = userJson.getInt("id");
		String userName = userJson.getString("userName");
		String passWord = userJson.getString("password");
		String email = userJson.getString("email");
		
		Logs.v("id :"+id + ", userName :"+userName+",passWord :"+passWord+", email :"+email);
	}

	
}
