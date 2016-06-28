package com.wangban.dangdang.presenter;

public interface ICartPresenter {
	void loadTotalPrice();
	void loadModifyNum(int id,int num);
	void loadDelete(int id);
}
