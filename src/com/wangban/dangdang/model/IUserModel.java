package com.wangban.dangdang.model;

import com.wangban.dangdang.entity.User;

/**
 *Created by wangban 2016-6-27 ионГ11:40:07
 */
public interface IUserModel {
	void getCodeImage(IModelCallback callback);
	void regist( User user,  String code,  IModelCallback callback);
	
}
