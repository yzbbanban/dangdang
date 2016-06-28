package com.wangban.dangdang.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DownloadManager.Request;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.util.CommonRequest;
import com.wangban.dangdang.util.JSONParser;

/**
 * Created by wangban 2016-6-28 ÏÂÎç4:52:18
 */
public class AddressModelImpl implements IAddressModel {

	@Override
	public void AddAddress(final Address address, final IModelCallback callback) {
		String url = GlobalConsts.URL_USER_ADD_ADDRESS;
		Log.i("supergirl", "model: " + address.toString());
		CommonRequest request = new CommonRequest(Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject object = new JSONObject(response);
							// Log.i("supergirl",
							// "code: "+object.getInt("code"));
							if (object.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {
								callback.findData(null);
							} else {
								callback.missData(object.getString("error_msg"));
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("address.receiveName", address.getReceiveName());
				map.put("address.full_address", address.getFull_address());
				map.put("address.postalCode", address.getPostalCode());
				map.put("address.mobile", address.getMobile());
				map.put("address.phone", address.getPhone());
				return map;
			}

		};
		DangApplication.getQueue().add(request);
	}

	@Override
	public void getAddress(final IModelCallback callback) {
		String url = GlobalConsts.URL_USER_LIST_ADDRESS;

		CommonRequest request = new CommonRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if (jsonObject.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {

						JSONArray jsonArray = jsonObject.getJSONArray("data");

						List<Address> addresses = JSONParser
								.parseAddress(jsonArray);

						callback.findData(addresses);
					} else {
						callback.missData(null);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callback.missData(null);

			}
		});
		DangApplication.getQueue().add(request);
	}

	@Override
	public void setAddDefault(int id, IModelCallback callback) {
		String url = GlobalConsts.URL_USER_SET_ADDRESS_DEFAULT + "?id=" + id;
		CommonRequest request = new CommonRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject object = new JSONObject(response);
					if (object.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS) {
						
					}else {
						object.getString("error_msg");
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		});
		DangApplication.getQueue().add(request);
	}
}
