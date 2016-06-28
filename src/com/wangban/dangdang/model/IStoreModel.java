package com.wangban.dangdang.model;

public interface IStoreModel {
	void loadRecommendBooks(IModelCallback callback);
    void loadHotBooks(IModelCallback callback );
    void loadNewBooks(IModelCallback callback );
}
