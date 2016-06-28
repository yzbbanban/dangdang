package com.wangban.dangdang.model;

import com.wangban.dangdang.entity.Address;

/**
 *Created by wangban 2016-6-28 обнГ4:41:56
 */
public interface IAddressModel {
	void getAddress(IModelCallback callback);
	void AddAddress(Address address,IModelCallback callback);
}
