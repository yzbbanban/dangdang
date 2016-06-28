package com.wangban.dangdang.presenter.impl;

import android.util.Log;

import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IUserModel;
import com.wangban.dangdang.model.impl.UserModelImpl;
import com.wangban.dangdang.presenter.ILoginPresenter;
import com.wangban.dangdang.view.ILoginView;

/**
 * Created by wangban 2016-6-28 ÉÏÎç11:37:23
 */
public class LoginPresenterImpl implements ILoginPresenter {
	private ILoginView view;
	private IUserModel model;

	public LoginPresenterImpl(ILoginView view) {
		this.view = view;
		model = new UserModelImpl();
	}

	@Override
	public void login(String email, String password) {
		model.login(email, password, new IModelCallback() {
			@Override
			public void findData(Object object) {
				//Log.i("supergirl", "login presenter");
				view.LoginSuccess();
			}

			@Override
			public void missData(Object object) {
				view.LoginFailure();
				
			}
		});

	}

}
