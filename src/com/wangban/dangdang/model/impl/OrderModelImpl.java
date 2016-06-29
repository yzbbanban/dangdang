package com.wangban.dangdang.model.impl;

import java.util.List;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.Order;
import com.wangban.dangdang.entity.OrderItem;
import com.wangban.dangdang.entity.QueryResultOrder;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IOrderModel;
import com.wangban.dangdang.util.CommonRequest;

/**
 * Created by wangban 2016-6-29 ÏÂÎç6:23:15
 */
public class OrderModelImpl implements IOrderModel {

	@Override
	public void getOrder(final IModelCallback callback) {
		String url = GlobalConsts.URL_LOAD_ORDER;
		CommonRequest request = new CommonRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i("supergirl", "response: "+response);
				Gson gson = new Gson();
				QueryResultOrder resultOrder = gson.fromJson(response,
						QueryResultOrder.class);
				List<OrderItem> items = resultOrder.getData().get(0).getItems();
				Log.i("supergirl", "result: "+resultOrder.toString());
				callback.findData(items);

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
