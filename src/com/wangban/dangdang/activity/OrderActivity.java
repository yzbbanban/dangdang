package com.wangban.dangdang.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.adapter.OrderAdapter;
import com.wangban.dangdang.entity.OrderItem;
import com.wangban.dangdang.presenter.IOrderPresenter;
import com.wangban.dangdang.presenter.impl.OrderPresenterImpl;
import com.wangban.dangdang.view.IOrderView;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OrderActivity extends Activity implements IOrderView {
	@ViewInject(R.id.btn_order_back)
	private Button btnOrderBack;
	@ViewInject(R.id.lv_order_book)
	private ListView lvOrderBook;
	private IOrderPresenter presenter;
	private OrderAdapter adapter;
	private List<OrderItem> items;
	@ViewInject(R.id.tv_order_status)
	private TextView tvOrderStatus;
	@ViewInject(R.id.btn_cancle_order)
	private Button btnCancleOrder;
	@ViewInject(R.id.btn_fri_order)
	private Button btnFriOrder;
	@ViewInject(R.id.btn_sumbit_order)
	private Button btnSubmitOrder;

	public OrderActivity() {
		presenter = new OrderPresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		x.view().inject(this);
		presenter.loadOrder();
		setListeners();
	}

	private void setListeners() {
		btnOrderBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.zoom_exit, R.anim.zoom_enter);
			}
		});

	}

	@Override
	public void setData(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public void showData() {
		adapter = new OrderAdapter(this, items);
		lvOrderBook.setAdapter(adapter);
	}

}
