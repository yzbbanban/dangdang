package com.wangban.dangdang.presenter;

import com.wangban.dangdang.entity.User;
import com.wangban.dangdang.model.IModelCallback;

/**
 *Created by wangban 2016-6-27 ионГ11:39:08
 */
public interface IRegistPresenter {
	void loadCodeImage();
	public void regist(User user, String code);
}
