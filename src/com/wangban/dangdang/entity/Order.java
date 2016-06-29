package com.wangban.dangdang.entity;

import java.util.List;

/**
 * Created by wangban 2016-6-29 ÏÂÎç4:22:27
 */
public class Order {
	private Address address;
	private int id;
	private List<OrderItem> items;
	private String orderDesc;
	private long orderTime;
	private int status;
	private double totalPrice;
	private int userId;

	public Order() {
		super();
	}

	public Order(Address address, int id, List<OrderItem> items,
			String orderDesc, long orderTime, int status, double totalPrice,
			int userId) {
		super();
		this.address = address;
		this.id = id;
		this.items = items;
		this.orderDesc = orderDesc;
		this.orderTime = orderTime;
		this.status = status;
		this.totalPrice = totalPrice;
		this.userId = userId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
