package com.wangban.dangdang.presenter.impl;

import java.util.List;

import android.util.Log;

import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.model.AddressModelImpl;
import com.wangban.dangdang.model.IAddressModel;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.presenter.IAddressPresenter;
import com.wangban.dangdang.view.IAddressView;

/**
 * Created by wangban 2016-6-28 ÏÂÎç4:46:38
 */
public class AddressPresenterImpl implements IAddressPresenter {
	private IAddressView view;
	private IAddressModel model;

	public AddressPresenterImpl(IAddressView view) {
		this.view = view;
		model = new AddressModelImpl();
	}

	@Override
	public void loadAddress(Address address) {
		model.AddAddress(address, new IModelCallback() {
			@Override
			public void findData(Object object) {
				view.addAddressSuccess();
			}

			@Override
			public void missData(Object object) {
				String message = (String) object;
				view.addAddressFail(message);

			}

		});

	}

	@Override
	public void listAddress() {
		model.getAddress(new IModelCallback() {

			@Override
			public void findData(Object object) {
				List<Address> addresses = (List<Address>) object;
				view.setData(addresses);
				view.showData();
			}

			@Override
			public void missData(Object object) {
				view.setData(null);

			}

		});

	}

	@Override
	public void setAddDefault(int id) {
		model.setAddDefault(id, new IModelCallback() {
			@Override
			public void findData(Object object) {
				//Log.i("supergirl", "setAddDefault");
				listAddress();
			}

			@Override
			public void missData(Object object) {
				

			}

		});

	}

}
