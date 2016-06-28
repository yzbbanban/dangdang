package com.wangban.dangdang.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.wangban.dangdang.app.DangApplication;

/**
 * Created by wangban 2016-6-27 下午4:03:30
 */
public class CommonRequest extends StringRequest {
	public static String JSESSIONID = null;

	public CommonRequest(int method, String url,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	public CommonRequest(String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(url, listener, errorListener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取消息头Set-cookie
	 */
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		Map<String, String> res = response.headers;
		String senssionid = res.get("Set-Cookie");
		if (senssionid != null) {
			JSESSIONID = senssionid.split(";")[0];
		}
		return super.parseNetworkResponse(response);
	}

	/**
	 * 向服务器发送消息
	 */

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = super.getHeaders();
		if (headers == null || headers.equals(Collections.emptyMap())) {
			headers = new HashMap<String, String>();
		}
		if (JSESSIONID != null) {
			headers.put("Cookie", JSESSIONID);
		}
		// 将cart传入服务器
		SharedPreferences pref = DangApplication.getContext()
				.getSharedPreferences("cart", Context.MODE_PRIVATE);
		String cart = pref.getString("cart", null);
		if (cart != null) {
			String cookie = headers.get("Cookie");
			headers.put("Cookie", cookie == null ? cart : cookie + ", cart="
					+ cart);
		}
		return headers;
	}

}
