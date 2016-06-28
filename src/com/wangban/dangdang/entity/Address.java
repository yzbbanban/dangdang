package com.wangban.dangdang.entity;

/**
 * Created by wangban 2016-6-28 ÏÂÎç1:32:25
 */
public class Address {

	private String full_address;
	private int id;
	private int is_default;
	private String mobile;
	private String phone;
	private String postalCode;
	private String receiveName;
	private int userId;

	public String getFull_address() {
		return full_address;
	}

	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIs_default() {
		return is_default;
	}

	public void setIs_default(int is_default) {
		this.is_default = is_default;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Address [full_address=" + full_address + ", id=" + id
				+ ", is_default=" + is_default + ", mobile=" + mobile
				+ ", phone=" + phone + ", postalCode=" + postalCode
				+ ", receiveName=" + receiveName + ", userId=" + userId + "]";
	}

}
