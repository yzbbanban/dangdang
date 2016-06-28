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
	 * ִ����ӹ��ﳵ��������ӵ�CartFargment��
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
	 * ɾ�������е�����
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
	 * �޸ļ����е�����count
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
	 * ��ȡ�ܼ۸�
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
	 * ���湺�ﳵ��Ϣ
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
	 * ��ȡ���ﳵ��Ϣ
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
