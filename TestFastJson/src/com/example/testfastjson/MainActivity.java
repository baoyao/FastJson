package com.example.testfastjson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Gson Fast-json的实际应用案例
 * 
 * 网络请求权限
 * 
 * 图书列表
 * 
 */

public class MainActivity extends Activity {

	private String url = "http://apis.juhe.cn/goodbook/catalog?key=2ea66be04ab0420ce486458f5cdadfd2&dtype=json";
	private ListView lv;
	private BookListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		getData();
	}

	private void getData() {
		//
		StringRequest request = new StringRequest(url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						Log.v("tt", "onResponse " + s);
						// 解析json数据
						try {
							dealData(s);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Log.v("tt", "onErrorResponse " + volleyError);
					}
				});

		// 将请求加入Volley请求队列
		Volley.newRequestQueue(getApplicationContext()).add(request);
	}

	private void dealData(String s) throws JSONException {

		/**
		 * Fast-json用法
		 * 
		 */
		// JSONObject object = new JSONObject(s);
		//
		// ArrayList<Book> books = (ArrayList<Book>)
		// JSON.parseArray(object.getString("result"),Book.class);
		//
		// adapter = new BookListAdapter(this,books);
		// lv.setAdapter(adapter);

		/**
		 * Gson 用法
		 */
		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Book>>() {
		}.getType();

		JSONObject object = new JSONObject(s);

		ArrayList<Book> books = gson.fromJson(object.getString("result"),
				listType);

		if (books != null) {
			adapter = new BookListAdapter(this, books);
			lv.setAdapter(adapter);
		} else {
			setLocalData();
		}
	}

	private void setLocalData() {
		/** Gson */
		// Gson gson=new Gson();
		// Type type=new TypeToken<ArrayList<Book>>(){}.getType();
		// ArrayList<Book> list=gson.fromJson(buildJson(), type);

		/** FastJson */
		ArrayList<Book> list = new ArrayList<Book>();
		list = (ArrayList<Book>) JSON.parseArray(buildJson(), Book.class);

		adapter = new BookListAdapter(this, list);
		lv.setAdapter(adapter);
	}

	private String buildJson() {

		List<Book> list = new ArrayList<Book>();

		for (int i = 0; i < 10; i++) {
			Book book = new Book();
			book.setId("id_" + i);
			book.setCatalog("log_" + i);
			list.add(book);
		}

		String json = JSONArray.toJSONString(list);

		return json;

	}
}
