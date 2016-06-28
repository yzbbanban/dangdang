package com.wangban.dangdang.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.VolleyError;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.util.CommonRequest;

/**
 * Created by wangban 2016-6-28 ÏÂÎç4:52:18
 */
public class AddressModelImpl implements IAddressModel {

	@Override
	public void AddAddress(final Address address, final IModelCallback callback) {
		String url = GlobalConsts.URL_USER_ADD_ADDRESS;
		CommonRequest request = new CommonRequest(Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject object = new JSONObject(response);
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
	public void getAddress(IModelCallback callback) {
		
	}

}
