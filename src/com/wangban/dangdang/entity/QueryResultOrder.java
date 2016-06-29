package com.wangban.dangdang.entity;

import java.util.List;

/**
 * Created by wangban 2016-6-29 ÏÂÎç7:08:10
 */
public class QueryResultOrder {
	private int code;
	private List<Order> data;

	public QueryResultOrder() {
		super();
	}

	public QueryResultOrder(int code, List<Order> data) {
		super();
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Order> getData() {
		return data;
	}

	public void setData(List<Order> data) {
		this.data = data;
	}

}
