package com.wangban.dangdang.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.entity.QueryResult;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IStoreModel;

public class StoreModelImpl implements IStoreModel {
	

	public StoreModelImpl() {
		
	}

	@Override
	public void loadRecommendBooks(final IModelCallback callback) {
		findBooks(GlobalConsts.URL_LOAD_RECOMMEND_BOOK_LIST, callback);
	}

	@Override
	public void loadHotBooks(final IModelCallback callback) {

		findBooks(GlobalConsts.URL_LOAD_NEW_BOOK_LIST, callback);
	}

	@Override
	public void loadNewBooks(final IModelCallback callback) {
		findBooks(GlobalConsts.URL_LOAD_HOT_BOOK_LIST, callback);
	}

	// TODO带封装方法
	private void findBooks(String url, final IModelCallback callback) {
		StringRequest request = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						List<Book> books = new ArrayList<Book>();
						Gson gson = new Gson();
						QueryResult result = gson.fromJson(response,
								QueryResult.class);
						books = result.getData();
						// Log.i("supergirl", books.toString());

						callback.findData(books);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
		DangApplication.getQueue().add(request);
	}

}
