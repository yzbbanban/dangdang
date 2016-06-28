package com.wangban.dangdang.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;
import com.wangban.dangdang.entity.User;
import com.wangban.dangdang.presenter.IRegistPresenter;
import com.wangban.dangdang.presenter.impl.RegistPresenterImpl;
import com.wangban.dangdang.view.IRegistView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends Activity implements IRegistView,
		OnClickListener {
	@ViewInject(R.id.iv_regist_code)
	private ImageView ivRegistCode;

	@ViewInject(R.id.et_regist_email)
	private EditText etRegisttEmail;
	@ViewInject(R.id.et_regist_password)
	private EditText etRegistPassword;
	@ViewInject(R.id.et_regist_realname)
	private EditText etRegistRealname;
	@ViewInject(R.id.et_regist_code)
	private EditText etRegistCode;
	@ViewInject(R.id.btn_regist)
	private Button btnRegist;
	@ViewInject(R.id.ibtn_regist_back)
	private ImageButton ibtnRegistBack;
	private IRegistPresenter presenter;

	public RegistActivity() {
		presenter = new RegistPresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		x.view().inject(this);
		// Log.i("supergirl", "onCreate");
		presenter.loadCodeImage();
		setListeners();
	}

	private void setData() {
		User user = new User();
		user.setEmail(etRegisttEmail.getText().toString());
		user.setPassword(etRegistPassword.getText().toString());
		user.setNickname(etRegistRealname.getText().toString());
		String code = etRegistCode.getText().toString();
		//Log.i("supergirl", user.toString());
		presenter.regist(user, code);
	}

	private void setListeners() {
		ivRegistCode.setOnClickListener(this);
		btnRegist.setOnClickListener(this);
	}

	@Override
	public void showCodeImage(Bitmap bitmap) {
		if (bitmap != null) {
			// Log.i("supergirl", "view" + bitmap.toString());
			ivRegistCode.setImageBitmap(bitmap);
		} else {
			ivRegistCode.setImageResource(R.drawable.touxiang);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_regist_code:
			presenter.loadCodeImage();
			Toast.makeText(RegistActivity.this, "¸üÐÂÍ¼Æ¬", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btn_regist:
			setData();
			
		case R.id.ibtn_regist_back:
			finish();
			overridePendingTransition(R.anim.zoom_exit, R.anim.zoom_enter);
		default:
			break;
		}

	}

	@Override
	public void registSuccess() {
		Toast.makeText(this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
		finish();
		overridePendingTransition(R.anim.zoom_exit, R.anim.zoom_enter);
	}

	@Override
	public void registFailure() {
		Toast.makeText(this, "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
		
	}
}
