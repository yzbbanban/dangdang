package com.wangban.dangdang.presenter.impl;

import java.util.List;

import android.util.Log;

import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IStoreModel;
import com.wangban.dangdang.model.impl.StoreModelImpl;
import com.wangban.dangdang.presenter.IStorePresenter;
import com.wangban.dangdang.view.IStoreView;

public class StorePresenterImpl implements IStorePresenter {
	private IStoreView view;
	private IStoreModel model;

	public StorePresenterImpl(IStoreView view) {
		this.view = view;
		this.model = new StoreModelImpl();
	}

	@Override
	public void loadRecommondBookData() {
		model.loadRecommendBooks(new IModelCallback() {

			@Override
			public void findData(Object object) {
				List<Book> books = (List<Book>) object;
				view.showRecommendBookList(books);

			}

			@Override
			public void missData(Object object) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void loadNewBookData() {
		model.loadNewBooks(new IModelCallback() {

			@Override
			public void findData(Object object) {
				List<Book> books = (List<Book>) object;
				view.showNewBookList(books);

			}

			@Override
			public void missData(Object object) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void loadHotBookData() {
		model.loadHotBooks(new IModelCallback() {

			@Override
			public void findData(Object object) {
				List<Book> books = (List<Book>) object;
				view.showHotBookList(books);

			}

			@Override
			public void missData(Object object) {
				// TODO Auto-generated method stub
				
			}
		});

	}

}
