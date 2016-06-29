package com.wangban.dangdang.view;

import java.util.List;

import com.wangban.dangdang.entity.OrderItem;

/**
 * Created by wangban 2016-6-29 обнГ4:18:27
 */
public interface IOrderView {
	void setData(List<OrderItem> items);

	void showData();
}
