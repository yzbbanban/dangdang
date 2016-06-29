package com.wangban.dangdang.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;
import com.wangban.dangdang.adapter.CartInfoAdapter;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.entity.CartItem;
import com.wangban.dangdang.presenter.CartInfoPresenterImpl;
import com.wangban.dangdang.presenter.ICartInfoPresenter;
import com.wangban.dangdang.view.ICartInfoView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartInfoActivity extends Activity implements ICartInfoView {
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

	private Intent intent;
	private List<CartItem> items;
	private CartInfoAdapter adapter;
	private ICartInfoPresenter presenter;

	public CartInfoActivity() {
		presenter = new CartInfoPresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_info);
		x.view().inject(this);
		setDate();
		setListeners();
	}

	private void setListeners() {
		// TODO Auto-generated method stub

	}

	private void setDate() {
		items = DangApplication.getCart().getCartItems();
		adapter = new CartInfoAdapter(this, items);
		lvCartInfo.setAdapter(adapter);
		listViewAnim();
		presenter.loadDefaultAddress();
		Log.i("supergirl", "cartInfoView");
	}

	@Override
	public void upDataDefaultAddress(Address address) {
		String name = address.getReceiveName();
		String phone = address.getPhone();
		String adds = address.getFull_address();
		String text = "收货人：<font color='red'>" + name
				+ "</font><br/>收货电话：<font color='red'>" + phone
				+ "</font><br/>收货地址：<font color='red'>" + adds + "</font>";
		tvCartInfo.setText(Html.fromHtml(text));

	}

	private void listViewAnim() {
		LayoutAnimationController c = new LayoutAnimationController(
				AnimationUtils.loadAnimation(this, R.anim.anim_item_listview));
		c.setDelay(0.3f);
		c.setOrder(LayoutAnimationController.ORDER_NORMAL);
		lvCartInfo.setLayoutAnimation(c);
	}

}
