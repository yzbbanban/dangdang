package com.wangban.dangdang.presenter.impl;

import android.util.Log;

import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IUserModel;
import com.wangban.dangdang.model.impl.UserModelImpl;
import com.wangban.dangdang.presenter.IMinePresenter;
import com.wangban.dangdang.view.IMineView;

/**
 * Created by wangban 2016-6-28 ÏÂÎç12:46:48
 */
public class MinePresenterImpl implements IMinePresenter {
	private IMineView view;
	private IUserModel model;

	public MinePresenterImpl(IMineView view) {
		this.view = view;
		model=new UserModelImpl();
	}

	@Override
	public void LoginWithoutPWD(String token) {
		model.loginWithoutPWD(token, new IModelCallback() {
			
			@Override
			public void findData(Object object) {
				view.upDataUser();
				Log.i("supergirl", "login witoutPWD finddata");
			}

			@Override
			public void missData(Object object) {
				
				
			}
		});
	}
}