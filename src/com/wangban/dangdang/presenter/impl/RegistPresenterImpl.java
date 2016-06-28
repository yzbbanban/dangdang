package com.wangban.dangdang.presenter.impl;

import android.graphics.Bitmap;
import android.util.Log;

import com.wangban.dangdang.entity.User;
import com.wangban.dangdang.model.IModelCallback;
import com.wangban.dangdang.model.IUserModel;
import com.wangban.dangdang.model.impl.UserModelImpl;
import com.wangban.dangdang.presenter.IRegistPresenter;
import com.wangban.dangdang.view.IRegistView;

/**
 * Created by wangban 2016-6-27 ÉÏÎç11:44:00
 */
public class RegistPresenterImpl implements IRegistPresenter {
	private IRegistView view;
	private IUserModel model;

	public RegistPresenterImpl(IRegistView view) {
		this.view = view;
		// Log.i("supergirl", "registPresenter");
		model = new UserModelImpl();
	}

	@Override
	public void loadCodeImage() {
		model.getCodeImage(new IModelCallback() {
			@Override
			public void findData(Object object) {
				Bitmap bitmap = (Bitmap) object;
				// Log.i("supergirl", "presenter: " + bitmap.toString());
				view.showCodeImage(bitmap);
			}

			@Override
			public void missData(Object object) {
				// TODO Auto-generated method stub
				
			}
		});

	}


	@Override
	public void regist(User user, String code) {
		Log.i("supergirl", user.toString());
		model.regist(user, code, new IModelCallback() {
			
			@Override
			public void findData(Object object) {
				Log.i("supergirl", "regist presenter");
				view.registSuccess();
			}

			@Override
			public void missData(Object object) {
				view.registFailure();
				
			}
		});
		
	}

}
