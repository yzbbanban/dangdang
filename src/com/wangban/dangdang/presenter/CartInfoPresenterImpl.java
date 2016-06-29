package com.wangban.dangdang.presenter;

import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.model.CartInfoModelImpl;
import com.wangban.dangdang.model.ICartInfoModel;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.view.ICartInfoView;

/**
 * Created by wangban 2016-6-29 ÉÏÎç11:55:47
 */
public class CartInfoPresenterImpl implements ICartInfoPresenter {
	private ICartInfoView view;
	private ICartInfoModel model;

	public CartInfoPresenterImpl(ICartInfoView view) {
		this.view = view;
		model = new CartInfoModelImpl();
	}

	@Override
	public void loadDefaultAddress() {
		model.getDefaultAddress(new IModelCallback() {

			@Override
			public void missData(Object object) {
				
			}

			@Override
			public void findData(Object object) {
				Address address = (Address) object;
				view.upDataDefaultAddress(address);
			}
		});

	}
}
