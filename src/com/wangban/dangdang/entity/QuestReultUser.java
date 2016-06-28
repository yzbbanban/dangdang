package com.wangban.dangdang.entity;

/**
 * Created by wangban 2016-6-28 ионГ11:08:49
 */
public class QuestReultUser {
	private int code;
	private String user;
	private String token;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "QuestReultUser [code=" + code + ", user=" + user + ", token="
				+ token + "]";
	}

}
