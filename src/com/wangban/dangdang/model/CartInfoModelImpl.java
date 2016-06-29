package com.wangban.dangdang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.JsonParser;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.util.CommonRequest;
import com.wangban.dangdang.util.JSONParser;

/**
 * Created by wangban 2016-6-29 ÉÏÎç11:54:50
 */
public class CartInfoModelImpl implements ICartInfoModel {

	@Override
	public void getDefaultAddress(final IModelCallback callback) {
		String url = GlobalConsts.URL_LOAD_DEFAULT_ADDRESS;
		CommonRequest request = new CommonRequest(url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject object = new JSONObject(response);
							if (object.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {
								JSONObject addObject = object
										.getJSONObject("data");
								Address address = JSONParser
										.parseDefaultAddress(addObject);

								callback.findData(address);
							} else {
								callback.missData(null);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						callback.missData(null);
					}
				});
		DangApplication.getQueue().add(request);
	}
}
