package com.wangban.dangdang.entity;

/**
 * Created by wangban 2016-6-29 ÏÂÎç4:20:23
 */
public class OrderItem {
	private double amount;
	private double dangPrice;
	private int id;
	private int orderId;
	private int productId;
	private int productNum;
	private String productName;

	public OrderItem() {
		super();
	}

	public OrderItem(double amount, double dangPrice, int id, int orderId,
			int productId, int productNum, String productName) {
		super();
		this.amount = amount;
		this.dangPrice = dangPrice;
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productNum = productNum;
		this.productName = productName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDangPrice() {
		return dangPrice;
	}

	public void setDangPrice(double dangPrice) {
		this.dangPrice = dangPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "OrderItem [amount=" + amount + ", dangPrice=" + dangPrice
				+ ", id=" + id + ", orderId=" + orderId + ", productId="
				+ productId + ", productNum=" + productNum + ", productName="
				+ productName + "]";
	}

}
