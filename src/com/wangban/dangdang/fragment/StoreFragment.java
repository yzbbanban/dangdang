package com.wangban.dangdang.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.activity.BookDetailActivity;
import com.wangban.dangdang.adapter.StoreAdapter;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.presenter.IStorePresenter;
import com.wangban.dangdang.presenter.impl.StorePresenterImpl;
import com.wangban.dangdang.view.IStoreView;

import android.content.Intent;
import android.graphics.Paint.Join;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

public class StoreFragment extends Fragment implements IStoreView {
	private StoreAdapter recommondAdapter;
	private StoreAdapter newAdapter;
	private StoreAdapter hotAdapter;
	@ViewInject(R.id.gv_store_recommend_book)
	private GridView gvRecommend;
	@ViewInject(R.id.gv_store_new_book)
	private GridView gvNew;
	@ViewInject(R.id.gv_store_hot_book)
	private GridView gvHot;
	private List<Book> recommendBooks;
	private List<Book> newBooks;
	private List<Book> hotBooks;
	private IStorePresenter presenter;
	@ViewInject(R.id.et_search_store_book)
	private EditText etSearch;
	public StoreFragment() {
		presenter = new StorePresenterImpl(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.store_fragment, null);
		x.view().inject(this,view);
		etSearch.clearFocus();
		presenter.loadRecommondBookData();
		presenter.loadNewBookData();
		presenter.loadHotBookData();
		setListeners();
		return view;
	}

	private void setListeners() {
		gvRecommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Log.i("supergirl", "1111");
				Book book = recommendBooks.get(position);
				Intent intent = new Intent(getActivity(),
						BookDetailActivity.class);
				intent.putExtra("books", book);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.zoom_enter,
						R.anim.zoom_exit);
			}
		});
		gvNew.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Book book = newBooks.get(position);
				Intent intent = new Intent(getActivity(),
						BookDetailActivity.class);
				intent.putExtra("books", book);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.zoom_enter,
						R.anim.zoom_exit);
			}
		});
		gvHot.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Book book = hotBooks.get(position);
				Intent intent = new Intent(getActivity(),
						BookDetailActivity.class);
				intent.putExtra("books", book);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.zoom_enter,
						R.anim.zoom_exit);
			}
		});

	}


	@Override
	public void showRecommendBookList(List<Book> books) {
		this.recommendBooks = books;
		recommondAdapter = new StoreAdapter(getActivity(), books);
		gvRecommend.setAdapter(recommondAdapter);
	}

	@Override
	public void showNewBookList(List<Book> books) {
		this.newBooks = books;
		newAdapter = new StoreAdapter(getActivity(), books);
		gvNew.setAdapter(newAdapter);
	}

	@Override
	public void showHotBookList(List<Book> books) {
		this.hotBooks = books;
		hotAdapter = new StoreAdapter(getActivity(), books);
		gvHot.setAdapter(hotAdapter);
	}
}
