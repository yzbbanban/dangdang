package com.wangban.dangdang.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.ibtn_new_user)
	private Button btnNewUser;
	@ViewInject(R.id.ibtn_login_back)
	private ImageButton ibtnLoginBack;
	private Intent intent;
	
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

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibtn_new_user:
			intent = new Intent(LoginActivity.this, RegistActivity.class);
			startActivity(intent);
			break;
		case R.id.ibtn_login_back:
			finish();
			overridePendingTransition(R.anim.zoom_exit, R.anim.zoom_enter);
			break;
		default:
			break;
		}

	}

}
