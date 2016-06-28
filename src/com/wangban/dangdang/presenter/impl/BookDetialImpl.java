package com.wangban.dangdang.presenter.impl;

import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.model.ICartModel;
import com.wangban.dangdang.model.impl.CartModelImpl;
import com.wangban.dangdang.presenter.IBookDetialPresenter;
import com.wangban.dangdang.view.IBookDetialView;

public class BookDetialImpl implements IBookDetialPresenter{
	private IBookDetialView view;
	private ICartModel model;
	
	public BookDetialImpl(IBookDetialView view) {
		this.view = view;
		this.model=new CartModelImpl();
	}

	@Override
	public void addBookToCart(Book book) {
		model.addBook(book);
		view.addCartSuccess();
	}

}
