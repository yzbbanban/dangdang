package com.wangban.dangdang.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.adapter.CartAdapter;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.entity.CartItem;
import com.wangban.dangdang.presenter.ICartPresenter;
import com.wangban.dangdang.presenter.impl.CartPresenterImpl;
import com.wangban.dangdang.view.ICartView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	private Book book;

	private CartAdapter adapter;
	private List<CartItem> items;

	private ICartPresenter cartPresenter;

	public CartFragment() {
		cartPresenter = new CartPresenterImpl(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.cart_fragment, null);
		x.view().inject(this, view);
		setData();
		// ��ʾ�����ء�ɾ��������
		btn_editor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				adapter.setDelete();
				adapter.notifyDataSetChanged();
			}
		});
		return view;
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
	}

	@Override
	public void updateTotalPrice(double totalPrice) {
		// Log.i("supergirl", "updateView: "+totalPrice);
		tv_cart_total.setText(totalPrice + "��");
	}
	@Override
	public void setVisible(){
		tvNoBook.setVisibility(View.VISIBLE);
	} 
}
