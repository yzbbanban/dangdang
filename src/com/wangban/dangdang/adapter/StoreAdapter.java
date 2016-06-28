package com.wangban.dangdang.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.wangban.dangdang.R;
import com.wangban.dangdang.app.DangApplication;
import com.wangban.dangdang.entity.Book;
import com.wangban.dangdang.util.BitmapCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends BaseAdapter  {
	private Context context;
	private List<Book> books;
	private LayoutInflater layoutInflater;
	private ImageLoader imageLoader;
	public StoreAdapter(Context context, List<Book> books) {
		super();
		this.context = context;
		this.books = books;
		this.layoutInflater=LayoutInflater.from(context);
		BitmapCache bitmapCache=new BitmapCache();
		imageLoader = new ImageLoader(DangApplication.getQueue(), bitmapCache);
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Book getItem(int position) {
		return books.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Book book=books.get(position);
		ViewHolder holder;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=layoutInflater.inflate(R.layout.item_store, null);
			holder.iv=(ImageView) convertView.findViewById(R.id.iv_store_book);
			holder.tv=(TextView) convertView.findViewById(R.id.tv_store_book_name);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		
		holder.tv.setText(book.getProductName());
		ImageListener imageListener = ImageLoader.getImageListener(holder.iv,
				R.drawable.touxiang, R.drawable.ic_launcher);
		String picUrl="http://45.78.24.178:8080/dang/productImages/"+book.getProduct_pic();
		imageLoader.get(picUrl, imageListener);
		
		
		return convertView;
	}
	class ViewHolder{
		ImageView iv;
		TextView tv;
	}
	
}
