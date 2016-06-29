package com.wangban.dangdang.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartInfoActivity extends Activity {
	@ViewInject(R.id.lv_cart_info)
	private ListView lvCartInfo;
	@ViewInject(R.id.tv_cart_info)
	private TextView tvCartInfo;
	@ViewInject(R.id.tv_total_price_count)
	private TextView tvTotalPriceCount;
	@ViewInject(R.id.btn_edit_default_address)
	private Button btnEditDefaultAddress;
	@ViewInject(R.id.btn_cart_info_submit)
	private Button btnCartInfoSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_info);
		x.view().inject(this);
	}
	
}
