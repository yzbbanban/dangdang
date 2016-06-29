package com.wangban.dangdang.presenter.impl;

import java.util.List;

import android.util.Log;

import com.wangban.dangdang.entity.OrderItem;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IOrderModel;
import com.wangban.dangdang.model.impl.OrderModelImpl;
import com.wangban.dangdang.presenter.IOrderPresenter;
import com.wangban.dangdang.view.IOrderView;

/**
 * Created by wangban 2016-6-29 ÏÂÎç6:47:26
 */
public class OrderPresenterImpl implements IOrderPresenter {
	private IOrderView view;
	private IOrderModel model;

	public OrderPresenterImpl(IOrderView view) {
		this.view = view;
		this.model = new OrderModelImpl();
	}

	@Override
	public void loadOrder() {
//		Log.i("supergirl", "order_presenter");
		model.getOrder(new IModelCallback() {
			@Override
			public void findData(Object object) {
				List<OrderItem> items = (List<OrderItem>) object;
				view.setData(items);
			}

			@Override
			public void missData(Object object) {
				// TODO Auto-generated method stub

			}

		});

	}

}
