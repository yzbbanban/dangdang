package com.wangban.dangdang.view;

import java.util.List;

import com.wangban.dangdang.entity.Address;

/**
 * Created by wangban 2016-6-28 обнГ4:38:03
 */
public interface IAddressView {
	void setData(List<Address> addresses);

	void showData();
}
