package com.wangban.dangdang.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.wangban.dangdang.R;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Order;
import com.wangban.dangdang.entity.OrderItem;
import com.wangban.dangdang.util.BitmapCache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wangban 2016-6-29 下午5:36:48
 */
public class OrderAdapter extends BaseAdapter {
	private Context context;
	private List<OrderItem> items;
	private LayoutInflater layoutInflater;

	public OrderAdapter(Context context, List<OrderItem> items) {
		super();
		this.context = context;
		this.items = items;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public OrderItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		OrderItem item = getItem(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater
					.inflate(R.layout.item_order_book, null);

			holder.tvBookOrderNumber = (TextView) convertView
					.findViewById(R.id.tv_book_order_number);
			holder.tvBookOrderPrice = (TextView) convertView
					.findViewById(R.id.tv_book_order_price);
			holder.tvBookOrderId = (TextView) convertView
					.findViewById(R.id.tv_book_order_id);
			holder.tvBookOrderBookId = (TextView) convertView
					.findViewById(R.id.tv_book_order_book_id);
			holder.tvBookOrderAmount = (TextView) convertView
					.findViewById(R.id.tv_book_order_amount);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		holder.tvBookOrderNumber.setText("数量：" + item.getProductNum());
		holder.tvBookOrderPrice.setText("单价：￥" + item.getDangPrice());
		holder.tvBookOrderId.setText("订单号：" + item.getOrderId());
		holder.tvBookOrderBookId.setText("图书号" + item.getProductId());
		holder.tvBookOrderAmount.setText("总价" + item.getAmount());

		return convertView;
	}

	class ViewHolder {
		Button btnFtiOrder;
		Button btnCancleOrder;
		Button btnSumbitOrder;
		TextView tvBookOrderNumber;
		TextView tvBookOrderPrice;
		TextView tvBookOrderId;
		TextView tvBookOrderBookId;
		TextView tvBookOrderAmount;

	}
}
