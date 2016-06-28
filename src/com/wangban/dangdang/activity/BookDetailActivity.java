package com.wangban.dangdang.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.entity.Cart;
import com.wangban.dangdang.entity.CartItem;
import com.wangban.dangdang.presenter.IBookDetialPresenter;
import com.wangban.dangdang.presenter.impl.BookDetialImpl;
import com.wangban.dangdang.util.BitmapCache;
import com.wangban.dangdang.util.BlurBitmapUtil;
import com.wangban.dangdang.view.IBookDetialView;

import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetailActivity extends Activity implements IBookDetialView,
		OnClickListener {
	private Book book;
	@ViewInject(R.id.iv_book_detial)
	private ImageView ivPic;
	@ViewInject(R.id.iv_book_detial_background)
	private ImageView ivBackground;

	@ViewInject(R.id.tv_book_detial_author)
	private TextView tvAuthor;

	@ViewInject(R.id.tv_book_detial_type)
	private TextView tvType;
	@ViewInject(R.id.tv_book_detial_name)
	private TextView tvName;
	@ViewInject(R.id.tv_book_detial_price)
	private TextView tvPrice;
	@ViewInject(R.id.tv_book_detial_publishedTime)
	private TextView tvPublishedTime;
	@ViewInject(R.id.tv_book_detial_publishing)
	private TextView tvPublishing;
	@ViewInject(R.id.tv_describe)
	private TextView tvDescribe;
	@ViewInject(R.id.btn_add_cart)
	private Button btnAddCart;
	@ViewInject(R.id.btn_book_detial_collect)
	private Button btnCollect;
	private ImageLoader imageLoader;

	private BitmapCache bitmapCache;

	private IBookDetialPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
		bitmapCache = new BitmapCache();
		imageLoader = new ImageLoader(DangApplication.getQueue(), bitmapCache);
		x.view().inject(this);
		setDate();
		setListeners();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	private void setDate() {
		presenter = new BookDetialImpl(this);
		String s = "\n\n" + getResources().getString(R.string.content);

		book = (Book) (getIntent().getSerializableExtra("books"));
		tvAuthor.setText("作者：" + book.getAuthor());
		tvType.setText("作者描述：" + book.getAuthor_summary());
		tvName.setText(book.getProductName());
		tvPrice.setText(+book.getDangPrice() + "￥");
		tvPublishedTime.setText("出版时间：" + book.getPublishedTime());
		tvPublishing.setText("出版社：" + book.getPublishing());
		tvDescribe.setText(book.getDescription() + s);
		// Log.i("supergirl", "lalallalal"+book.getDescription());

		ImageListener imageListener = ImageLoader.getImageListener(ivPic,
				R.drawable.touxiang, R.drawable.ic_launcher);
		String picUrl = "http://45.78.24.178:8080/dang/productImages/"
				+ book.getProduct_pic();
		imageLoader.get(picUrl, imageListener);

		ImageRequest imageRequest = new ImageRequest(picUrl,
				new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap response) {
						Bitmap b = BlurBitmapUtil
								.createBlurBitmap(response, 10);
						ivBackground.setImageBitmap(b);

					}
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

						ivBackground.setImageResource(R.drawable.touxiang);

					}
				});
		DangApplication.getQueue().add(imageRequest);

		// Bitmap bm=bitmapCache.getBitmap(picUrl);
		// Log.i("supergirl", "bookdetialactivity"+bm);
		// ivBackground.setImageBitmap(bm);

	}

	private void setListeners() {
		btnAddCart.setOnClickListener(this);
		btnCollect.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add_cart:
			// Toast.makeText(this, "添加购物车", Toast.LENGTH_SHORT).show();

			presenter.addBookToCart(book);
			btnAddCart.setEnabled(false);

			break;
		case R.id.btn_book_detial_collect:
			Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

	@Override
	public void addCartSuccess() {
		Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
		btnAddCart.setText("已添加购物车");
		// Log.i("supergirl", DangApplication.getCart().getCartItems().get(0)
		// .getBook().toString());

	}

	@Override
	public void addCartFail() {
		Toast.makeText(this, "添加失败，请检查网络", Toast.LENGTH_SHORT).show();

	}
}
