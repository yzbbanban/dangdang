package com.wangban.dangdang.app;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wangban.dangdang.entity.Cart;
import com.wangban.dangdang.entity.User;

import android.app.Application;
import android.content.Context;

public class DangApplication extends Application {
	private static DangApplication context;
	private static RequestQueue queue;
	private static Cart cart;
	private static User user;

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		context = this;
		queue = Volley.newRequestQueue(this);
		cart = new Cart();
		cart = cart.readCart();
	}

	public static RequestQueue getQueue() {
		return queue;
	}

	public static Cart getCart() {
		return cart;
	}

	public static DangApplication getContext() {
		return context;
	}

	public static User getUser() {
		return user;
	}
}
