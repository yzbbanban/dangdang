package com.wangban.dangdang.entity;

/**
 * Created by wangban 2016-6-27 ÏÂÎç4:59:37
 */
public class User {
	private String email;
	private boolean emailVerify;
	private String emailVerifyCode;
	private int id;
	private String lastLoginIp;
	private long lastLoginTime;
	private String nickname;
	private String password;
	private int userIntegral;

	public User() {
		super();
	}

	public User(String email, boolean emailVerify, String emailVerifyCode,
			int id, String lastLoginIp, long lastLoginTime, String nickname,
			String password, int userIntegral) {
		super();
		this.email = email;
		this.emailVerify = emailVerify;
		this.emailVerifyCode = emailVerifyCode;
		this.id = id;
		this.lastLoginIp = lastLoginIp;
		this.lastLoginTime = lastLoginTime;
		this.nickname = nickname;
		this.password = password;
		this.userIntegral = userIntegral;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailVerify() {
		return emailVerify;
	}

	public void setEmailVerify(boolean emailVerify) {
		this.emailVerify = emailVerify;
	}

	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}

	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(int userIntegral) {
		this.userIntegral = userIntegral;
	}

}
