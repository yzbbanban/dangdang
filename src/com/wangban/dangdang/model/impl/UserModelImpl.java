package com.wangban.dangdang.model.impl;

//import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.User;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IUserModel;
import com.wangban.dangdang.util.CommonRequest;

/**
 * Created by wangban 2016-6-27 …œŒÁ11:54:46
 */
public class UserModelImpl implements IUserModel {

	@Override
	public void getCodeImage(final IModelCallback callback) {
		String url = GlobalConsts.URL_LOAD_CODE_IMAGE;
		ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// Log.i("supergirl", "model: " + response.toString());
				callback.findData(response);
			}
		}, 130, 50, Config.ARGB_8888, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("supergirl", "eror: " + error);
			}
		}) {
			/**
			 * …Ë÷√cookie
			 */
			@Override
			protected Response<Bitmap> parseNetworkResponse(
					NetworkResponse response) {
				Map<String, String> headers = response.headers;
				String sessionid = headers.get("Set-Cookie");
				if (sessionid != null) {
					CommonRequest.JSESSIONID = sessionid.split(";")[0];
				}
				return super.parseNetworkResponse(response);
			}
		};
		DangApplication.getQueue().add(request);
	}

	@Override
	public void regist(final User user, final String code,
			final IModelCallback callback) {
		Log.i("supergirl", "model: "+user.toString());
		String url = GlobalConsts.URL_USER_REGIST;
		CommonRequest request = new CommonRequest(Request.Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							//Log.i("supergirl", "response" + response);
							JSONObject object = new JSONObject(response);
							if (object.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {
								callback.findData(null);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("user.email", user.getEmail());
				map.put("user.nickname", user.getNickname());
				map.put("user.password", user.getPassword());
				map.put("number", code);
				return map;
			}
		};
		DangApplication.getQueue().add(request);

	}

}