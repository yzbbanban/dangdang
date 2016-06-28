package com.wangban.dangdang.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.wangban.dangdang.app.DangApplication;

import android.util.Log;

public class Cart implements Serializable{
	private List<CartItem> items = new ArrayList<CartItem>();

	public List<CartItem> getCartItems() {
		return items;
	}

	public void setCartItems(List<CartItem> items) {
		this.items = items;
	}

	/**
	 * 执行添加购物车方法，添加到CartFargment中
	 * 
	 * @param book
	 * @return
	 */
	public boolean buy(Book book) {
		if (items.size() > 1) {
			for (int i = 0; i < items.size(); i++) {
				CartItem item = items.get(i);
				if (item.getBook().equals(book)) {
					item.setCount(item.getCount() + 1);
					saveCart();
					return false;
				}
			}
		}
		CartItem item = new CartItem(book, 1);
		items.add(item);
		saveCart();
		return true;
	}

	/**
	 * 删除集合中的数据
	 */
	public void delete(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getBook().getId() == id) {
				items.remove(i);
				saveCart();
				return;
			}
		}
		saveCart();
	}

	/**
	 * 修改集合中的数量count
	 */
	public void modifyNum(int id, int num) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getBook().getId() == id) {
				items.get(i).setCount(num);
				saveCart();
				return;
			}
		}
		saveCart();
	}

	/**
	 * 获取总价格
	 */
	public double getTotalPrice() {
		double totalPrice = 0.0;
		for (int i = 0; i < items.size(); i++) {
			int count = items.get(i).getCount();
			double price = items.get(i).getBook().getDangPrice();
			totalPrice = totalPrice + count * price;
		}
	//	Log.i("supergirl", "getTotalPrice" + totalPrice);
		return totalPrice;
	}

	/**
	 * 保存购物车信息
	 */
	public void saveCart() {
		try {
			File file = new File(DangApplication.getContext().getCacheDir(),
					"CART.INFO");
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file));
			oos.writeObject(this);
		//	Log.i("supergirl", "oos");
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取购物车信息
	 */
	public Cart readCart() {
		try {
			File file = new File(DangApplication.getContext().getCacheDir(),
					"CART.INFO");
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			Cart cart = (Cart) ois.readObject();
			if (cart == null) {
				return new Cart();
			}
			return cart;
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new Cart();
	}
}
