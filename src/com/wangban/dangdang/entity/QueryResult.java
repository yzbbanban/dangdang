package com.wangban.dangdang.entity;

import java.util.List;

public class QueryResult {
	private int code;
	private List<Book> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<Book> getData() {
		return data;
	}
	public void setData(List<Book> data) {
		this.data = data;
	}
	
}
