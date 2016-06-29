package com.wangban.dangdang.adapter;

import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.wangban.dangdang.R;
import com.wangban.dangdang.adapter.AddressAdapter.ViewHolder;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.CartItem;
import com.wangban.dangdang.util.BitmapCache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wangban 2016-6-29 ÉÏÎç11:22:01
 */
public class CartInfoAdapter extends BaseAdapter {
	private Context context;
	private List<CartItem> items;
	private LayoutInflater layoutInflater;
	private ImageLoader imageLoader;

	public CartInfoAdapter(Context context, List<CartItem> items) {
		this.context = context;
		this.items = items;
		this.layoutInflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader(DangApplication.getQueue(),
				new BitmapCache());

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public CartItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CartItem item = items.get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_cart_info, null);
			holder = new ViewHolder();
			holder.iv_cart_info_book = (ImageView) convertView
					.findViewById(R.id.iv_cart_info_book);
			holder.tv_cart_info_book_name = (TextView) convertView
					.findViewById(R.id.tv_cart_info_book_name);
			holder.tv_cart_info_book_price = (TextView) convertView
					.findViewById(R.id.tv_cart_info_book_price);
			holder.tv_cart_info_book_count = (TextView) convertView
					.findViewById(R.id.tv_cart_info_book_count);
			holder.tv_cart_info_book_total_price = (TextView) convertView
					.findViewById(R.id.tv_cart_info_book_total_price);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		ImageListener imageListener = ImageLoader.getImageListener(
				holder.iv_cart_info_book, R.drawable.touxiang,
				R.drawable.ic_launcher);
		String picUrl = "http://45.78.24.178:8080/dang/productImages/"
				+ item.getBook().getProduct_pic();
		imageLoader.get(picUrl, imageListener);

		holder.tv_cart_info_book_name.setText(item.getBook().getProductName());
		holder.tv_cart_info_book_price.setText(item.getBook().getDangPrice()
				+ "£¤");
		holder.tv_cart_info_book_count.setText("x" + item.getCount());
		double totalPrice = item.getBook().getDangPrice() * item.getCount();
		holder.tv_cart_info_book_total_price.setText("£¤" + totalPrice);

		return convertView;
	}

	class ViewHolder {
		ImageView iv_cart_info_book;
		TextView tv_cart_info_book_name;
		TextView tv_cart_info_book_price;
		TextView tv_cart_info_book_count;
		TextView tv_cart_info_book_total_price;
	}
}
