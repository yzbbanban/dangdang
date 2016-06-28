package com.wangban.dangdang.presenter.impl;


import java.io.Serializable;

import com.wangban.dangdang.model.ICartModel;
import com.wangban.dangdang.model.impl.CartModelImpl;
import com.wangban.dangdang.presenter.ICartPresenter;
import com.wangban.dangdang.view.ICartView;

public class CartPresenterImpl implements Serializable, ICartPresenter{
	private ICartView view;
	private ICartModel model;
	
	
	public CartPresenterImpl(ICartView view) {
		this.view = view;
		this.model=new CartModelImpl();
	}

	@Override
	public void loadTotalPrice() {
		double totalPrice=model.getTotalPrice();
		//Log.i("supergirl", "loadTotalPrice: "+totalPrice);
		view.updateTotalPrice(totalPrice);
		if (totalPrice==0.0) {
			view.setVisible();
		}
	}

	@Override
	public void loadModifyNum(int id,int num) {
		model.modifyNum(id,num);
		
	}

	@Override
	public void loadDelete(int id) {
		model.getdelete(id);
		
	}

}
