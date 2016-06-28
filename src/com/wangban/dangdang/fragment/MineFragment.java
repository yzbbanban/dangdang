package com.wangban.dangdang.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.activity.AddressActivity;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MineFragment extends Fragment implements IMineView,
		OnClickListener {
	@ViewInject(R.id.login_photo)
	private CircleImageView ivLoginPhoto;
	@ViewInject(R.id.tv_mine_name)
	private TextView tvMineName;
	@ViewInject(R.id.ll_my_collect)
	private LinearLayout llMyCollect;
	@ViewInject(R.id.ll_my_address)
	private LinearLayout llMyAddress;
	@ViewInject(R.id.ll_my_order)
	private LinearLayout llMyOrder;
	@ViewInject(R.id.ll_my_settings)
	private LinearLayout llMySettings;
	@ViewInject(R.id.ll_my_exit)
	private LinearLayout llMyExit;

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
		llMyCollect.setOnClickListener(this);
		llMyAddress.setOnClickListener(this);
		llMyOrder.setOnClickListener(this);
		llMySettings.setOnClickListener(this);
		llMyExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_photo:
			if (tvMineName.getText().toString().equals("ö©ö©")) {
				intent = new Intent(getActivity(), LoginActivity.class);
				startActivityForResult(intent, GlobalConsts.RESULT_OK);
			}else {
				Toast.makeText(getActivity(), "ÄúÒÑµÇÂ½", Toast.LENGTH_SHORT).show();	
			}
			break;
		case R.id.ll_my_address:
			intent=new Intent(getActivity(),AddressActivity.class);
			startActivity(intent);
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
			tvMineName.setText("ö©ö©");
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
