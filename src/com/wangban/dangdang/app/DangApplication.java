package com.wangban.dangdang.app;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wangban.dangdang.entity.Cart;
import com.wangban.dangdang.entity.User;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class DangApplication extends Application {
	private static DangApplication context;
	private static RequestQueue queue;
	private static Cart cart;
	private User user;
	private String token;

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

	public void saveUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getToken() {
		SharedPreferences pref = this.getSharedPreferences("token",
				MODE_PRIVATE);
		String token = pref.getString("token", "");
		return token;
	}

	public void saveToken(String token) {
		this.token = token;
		SharedPreferences pref = this.getSharedPreferences("token",
				MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("token", token);
		editor.commit();
	}

}
