package com.wangban.dangdang.model;

import com.wangban.dangdang.entity.User;

/**
 * Created by wangban 2016-6-27 ионГ11:40:07
 */
public interface IUserModel {
	void getCodeImage(IModelCallback callback);

	void regist(User user, String code, IModelCallback callback);

	void login(String email, String password, IModelCallback callback);
	void loginWithoutPWD(String token, IModelCallback callback);
}
