package com.wangban.dangdang.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.activity.LoginActivity;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.presenter.IMinePresenter;
import com.wangban.dangdang.presenter.impl.MinePresenterImpl;
import com.wangban.dangdang.ui.CircleImageView;
import com.wangban.dangdang.view.IMineView;
import com.wangban.dangdang.view.IRegistView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MineFragment extends Fragment implements IMineView,
		OnClickListener {
	@ViewInject(R.id.login_photo)
	private CircleImageView ivLoginPhoto;
	@ViewInject(R.id.tv_mine_name)
	private TextView tvMineName;
	private Intent intent;
	private DangApplication app;
	private IMinePresenter presenter;

	public MineFragment() {
		presenter = new MinePresenterImpl(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mine_fragment, null);
		x.view().inject(this, view);
		app = DangApplication.getContext();
		String token = app.getToken();
		if (token != null) {
			presenter.LoginWithoutPWD(token);
		}
		setListeners();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}



	private void setListeners() {
		ivLoginPhoto.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_photo:
			intent = new Intent(getActivity(), LoginActivity.class);
			startActivityForResult(intent, GlobalConsts.RESULT_OK);
			break;

		default:
			break;
		}

	}

	@Override
	public void upDataUser() {
		if (app.getUser() != null) {
			if (app.getUser().getNickname() != null) {
				tvMineName.setText(app.getUser().getNickname());
				// Log.i("supergirl", app.getUser().getNickname());
			}
		} else {
			tvMineName.setText("”Ó÷«≤®∞ﬂ");
		}
		// Log.i("supergirl", "MineFragment setdata");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case GlobalConsts.RESULT_OK:
			upDataUser();
			break;

		default:
			break;
		}

	}
}
