package com.wangban.dangdang.model;

import com.wangban.dangdang.entity.Book;

public interface ICartModel {
	boolean addBook(Book book);

	double getTotalPrice();

	void getdelete(int id);

	void modifyNum(int id,int num);
}
