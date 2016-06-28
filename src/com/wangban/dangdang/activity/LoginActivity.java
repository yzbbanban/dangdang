package com.wangban.dangdang.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;
import com.wangban.dangdang.consts.GlobalConsts;
import com.wangban.dangdang.entity.User;
import com.wangban.dangdang.presenter.ILoginPresenter;
import com.wangban.dangdang.presenter.impl.LoginPresenterImpl;
import com.wangban.dangdang.view.ILoginView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity implements ILoginView,
		OnClickListener {
	@ViewInject(R.id.ibtn_new_user)
	private Button btnNewUser;
	@ViewInject(R.id.btn_login_back)
	private Button btnLoginBack;
	@ViewInject(R.id.btn_login)
	private Button btnLogin;
	@ViewInject(R.id.et_login_email)
	private EditText etLoginEmail;
	@ViewInject(R.id.et_login_password)
	private EditText etLoginPassword;

	private Intent intent;
	private ILoginPresenter presenter;

	public LoginActivity() {
		presenter = new LoginPresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		x.view().inject(this);

		setListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);

		return true;
	}

	private void setListeners() {
		btnNewUser.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnLoginBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibtn_new_user:
			intent = new Intent(LoginActivity.this, RegistActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_login:
			setData();
			
			break;
		case R.id.btn_login_back:
			finish();
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		default:
			break;
		}
	}

	private void setData() {
		String email = etLoginEmail.getText().toString();
		String password = etLoginPassword.getText().toString();
		if ("".equals(email) || "".equals(password)) {
			Toast.makeText(this, "«Î ‰»Î√‹¬Î", Toast.LENGTH_SHORT).show();
		}
		presenter.login(email, password);
	}

	@Override
	public void LoginSuccess() {
		Toast.makeText(this, "µ«¬º≥…π¶", Toast.LENGTH_SHORT).show();
		setResult(GlobalConsts.RESULT_OK);
		finish();
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}

	@Override
	public void LoginFailure() {
		Toast.makeText(this, "µ«¬º ß∞‹£¨√‹¬ÎªÚ”√ªß√˚¥ÌŒÛ", Toast.LENGTH_SHORT).show();
		
	}
}
