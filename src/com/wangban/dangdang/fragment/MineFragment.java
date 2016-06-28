package com.wangban.dangdang.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.activity.LoginActivity;
import com.wangban.dangdang.ui.CircleImageView;
import com.wangban.dangdang.view.IRegistView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MineFragment extends Fragment implements OnClickListener {
	@ViewInject(R.id.login_photo)
	private CircleImageView ivLoginPhoto;
	private Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mine_fragment, null);
		x.view().inject(this, view);
		setListeners();
		return view;
	}

	private void setListeners() {
		ivLoginPhoto.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_photo:
			intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
