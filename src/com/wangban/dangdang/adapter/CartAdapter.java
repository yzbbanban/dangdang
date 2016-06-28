package com.wangban.dangdang.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.wangban.dangdang.R;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.CartItem;
import com.wangban.dangdang.presenter.ICartPresenter;
import com.wangban.dangdang.util.BitmapCache;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CartAdapter extends BaseAdapter {
	private Context context;
	private List<CartItem> items;
	private ListView listView;
	private LayoutInflater layoutInflater;
	private ImageLoader imageLoader;
	private ICartPresenter cartPresenter;
	private ViewHolder holder;
	private boolean show = false;

	public void setPresenter(ICartPresenter cartPresenter) {
		this.cartPresenter = cartPresenter;
	}

	public CartAdapter(Context context, List<CartItem> items, ListView listView) {
		super();
		this.context = context;
		this.items = items;
		this.listView = listView;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		CartItem item = items.get(position);

		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_cart, null);
			holder = new ViewHolder();
			holder.iv_cart_book = (ImageView) convertView
					.findViewById(R.id.iv_cart_book);
			holder.tv_cart_book_name = (TextView) convertView
					.findViewById(R.id.tv_cart_book_name);
			holder.tv_cart_book_price = (TextView) convertView
					.findViewById(R.id.tv_cart_book_price);
			holder.tv_cart_book_count = (TextView) convertView
					.findViewById(R.id.tv_cart_book_count);
			holder.btn_m_Number = (Button) convertView
					.findViewById(R.id.btn_m_Number);
			holder.tv_cart_book_total_count = (TextView) convertView
					.findViewById(R.id.tv_cart_book_total_count);
			holder.btn_p_Number = (Button) convertView
					.findViewById(R.id.btn_p_Number);

			holder.iv_cart_delete = (ImageView) convertView
					.findViewById(R.id.iv_cart_delete);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		ImageListener imageListener = ImageLoader.getImageListener(
				holder.iv_cart_book, R.drawable.touxiang,
				R.drawable.ic_launcher);
		String picUrl = "http://45.78.24.178:8080/dang/productImages/"
				+ item.getBook().getProduct_pic();
		imageLoader.get(picUrl, imageListener);
		holder.tv_cart_book_name.setText(item.getBook().getProductName());

		holder.tv_cart_book_price.setText(item.getBook().getDangPrice() + "");
		// holder.tv_cart_book_price.setTag("price" + position);

		holder.tv_cart_book_count.setText("X" + item.getCount() + "");
		// holder.tv_cart_book_count.setTag("count" + position);

		holder.tv_cart_book_total_count.setText(item.getCount() + "");
		// holder.tv_cart_book_total_count.setTag("total_count" + position);
		
		holder.iv_cart_delete.setTag("delete" + position);
		/**
		 * 减少订单的订购数量
		 */
		holder.btn_m_Number.setOnClickListener(new OnRemoveListenrt(position));
		/**
		 * 增加订单的订购数量
		 */
		holder.btn_p_Number.setOnClickListener(new OnAddListener(position));

		if (!show) {
			holder.iv_cart_delete.setScaleX(0);
			holder.iv_cart_delete.setScaleY(0);
		} else {
			holder.iv_cart_delete.setScaleX(1);
			holder.iv_cart_delete.setScaleY(1);
		}

		/**
		 * 删除当前数据 cartPresenter.loadModifyNum(position);
		 * 
		 * notifyDataSetChanged();
		 */
		holder.iv_cart_delete
				.setOnClickListener(new OnDeleteListener(position));

		return convertView;
	}

	class ViewHolder {
		ImageView iv_cart_book;
		TextView tv_cart_book_name;
		TextView tv_cart_book_price;
		TextView tv_cart_book_count;
		Button btn_m_Number;
		TextView tv_cart_book_total_count;
		Button btn_p_Number;
		ImageView iv_cart_delete;
	}

	public void setDelete() {
		int count = getCount() - 1;
		if (show) {
			for (int i = 0; i <= count; i++) {
				final ImageView iv = (ImageView) listView
						.findViewWithTag("delete" + i);
				if (iv != null) {
					iv.setVisibility(View.GONE);
				} 

			}
			show = false;
		} else {
			for (int i = 0; i <= count; i++) {
				final ImageView iv = (ImageView) listView
						.findViewWithTag("delete" + i);
				if (iv != null) {
					iv.setVisibility(View.VISIBLE);
					ObjectAnimator anim = ObjectAnimator.ofFloat(iv, "image",
							0f, 1f);
					anim.addUpdateListener(new AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							float val = (Float) animation.getAnimatedValue();
							iv.setScaleX(val);
							iv.setScaleY(val);

						}
					});
					anim.setDuration(500);
					anim.start();
				} 

			}
			show = true;
		}
	}

	class OnRemoveListenrt implements OnClickListener {
		private int position;
		private int num = Integer.parseInt(holder.tv_cart_book_total_count
				.getText().toString());

		public OnRemoveListenrt(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// Toast.makeText(context, "减少", Toast.LENGTH_SHORT).show();
			num = num == 1 ? num : num - 1;
			cartPresenter.loadModifyNum(getItem(position).getBook().getId(),
					num);
			notifyDataSetChanged();
			loadTotalPrice();
		}
	}

	class OnAddListener implements OnClickListener {
		private int position;
		private int num = Integer.parseInt(holder.tv_cart_book_total_count
				.getText().toString());

		public OnAddListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			num++;
			cartPresenter.loadModifyNum(getItem(position).getBook().getId(),
					num);
			notifyDataSetChanged();
			loadTotalPrice();
		}
	}

	class OnDeleteListener implements OnClickListener {
		private int position;

		public OnDeleteListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// items.remove(position);
			items.get(position);
			cartPresenter.loadDelete(getItem(position).getBook().getId());
			notifyDataSetChanged();
			loadTotalPrice();

		}

	}

	private void loadTotalPrice() {
		cartPresenter.loadTotalPrice();
	}
}
