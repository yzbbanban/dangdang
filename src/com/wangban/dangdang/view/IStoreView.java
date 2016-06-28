package com.wangban.dangdang.view;

import java.util.List;

import com.wangban.dangdang.entity.Book;

public interface IStoreView extends IView{
	void showRecommendBookList(List<Book> books);
    void showNewBookList(List<Book> books);
    void showHotBookList(List<Book> books);
}	
