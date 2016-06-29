package com.wangban.dangdang.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.activity.CartInfoActivity;
import com.wangban.dangdang.adapter.CartAdapter;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.entity.CartItem;
import com.wangban.dangdang.presenter.ICartPresenter;
import com.wangban.dangdang.presenter.impl.CartPresenterImpl;
import com.wangban.dangdang.view.ICartView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartFragment extends Fragment implements ICartView {
	@ViewInject(R.id.lv_cart)
	private ListView listView;
	@ViewInject(R.id.tv_no_book)
	private TextView tvNoBook;
	@ViewInject(R.id.tv_cart_total)
	private TextView tv_cart_total;
	@ViewInject(R.id.btn_editor)
	private Button btn_editor;
	@ViewInject(R.id.btn_cart_submit)
	private Button btnCartSubmit;

	private Book book;

	private CartAdapter adapter;
	private List<CartItem> items;

	private ICartPresenter cartPresenter;
	private Intent intent;

	public CartFragment() {
		cartPresenter = new CartPresenterImpl(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.cart_fragment, null);
		x.view().inject(this, view);
		setData();
		// ÏÔÊ¾»òÒþ²Ø¡°É¾³ý¡±°´¼ü
		setListeners();

		return view;
	}

	private void setListeners() {
		btn_editor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				adapter.setDelete();
				adapter.notifyDataSetChanged();
			}
		});

		btnCartSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent(CartFragment.this.getActivity(),
						CartInfoActivity.class);
				startActivity(intent);
			}
		});

	}

	private void setData() {
		items = DangApplication.getCart().getCartItems();
	}

	@Override
	public void onResume() {

		if (items != null) {
			tvNoBook.setVisibility(View.INVISIBLE);
			setAdapter();
		}

		super.onResume();
	}

	private void setAdapter() {
		cartPresenter.loadTotalPrice();
		adapter = new CartAdapter(getActivity(), items, listView);
		adapter.setPresenter(cartPresenter);
		listView.setAdapter(adapter);
		listViewAnim();
	}

	@Override
	public void updateTotalPrice(double totalPrice) {
		// Log.i("supergirl", "updateView: "+totalPrice);
		tv_cart_total.setText(totalPrice + "£¤");
	}

	@Override
	public void setVisible() {
		tvNoBook.setVisibility(View.VISIBLE);
	}
	
	private void listViewAnim() {
		LayoutAnimationController c = new LayoutAnimationController(
				AnimationUtils.loadAnimation(getActivity(), R.anim.anim_item_listview));
		c.setDelay(0.3f);
		c.setOrder(LayoutAnimationController.ORDER_NORMAL);
		listView.setLayoutAnimation(c);
	}
	
}
