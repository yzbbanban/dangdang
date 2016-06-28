package com.wangban.dangdang.model.impl;

import android.util.Log;

import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.entity.Cart;
import com.wangban.dangdang.model.ICartModel;

public class CartModelImpl implements ICartModel{
	private Cart cart;
	
	public CartModelImpl() {
		this.cart=DangApplication.getCart();
	}
	/**
	 * 添加书籍
	 */
	@Override
	public boolean addBook(Book book) {
		boolean c=cart.buy(book);
		//Log.i("supergirl","buy"+cart.getCartItems().get(0).getBook().getAuthor());
		return c;
	}
	/**
	 * 总钱数
	 */
	@Override
	public double getTotalPrice() {
		double totalPrice=cart.getTotalPrice();
		//Log.i("supergirl", "getTotalPrice"+totalPrice);
		return totalPrice;
	}
	@Override
	public void getdelete(int id) {
		cart.delete(id);
	}
	
	@Override
	public void modifyNum(int id,int num) {
		cart.modifyNum(id,num);
	}

}
