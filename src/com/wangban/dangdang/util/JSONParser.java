package com.wangban.dangdang.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.entity.User;

/**
 * Created by wangban 2016-6-28 ÉÏÎç11:10:21
 */
public class JSONParser {

	public static User parseUser(JSONObject uObject) throws JSONException {
		User user = new User();
		user.setEmail(uObject.getString("email"));
		user.setEmailVerify(uObject.getBoolean("emailVerify"));
		user.setEmailVerifyCode(uObject.getString("emailVerifyCode"));
		user.setId(uObject.getInt("id"));
		user.setLastLoginIp(uObject.getString("lastLoginIp"));
		user.setLastLoginTime(uObject.getLong("lastLoginTime"));
		user.setPassword(uObject.getString("password"));
		user.setUserIntegral(uObject.getInt("userIntegral"));
		user.setNickname(uObject.getString("nickname"));
		return user;
	}

	public static List<Address> parseAddress(JSONArray jsonArray)
			throws JSONException {
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			Address address = new Address();
			address.setFull_address(obj.getString("full_address"));
			address.setId(obj.getInt("id"));
			address.setIs_default(obj.getInt("is_default"));
			address.setFull_address(obj.getString("mobile"));
			address.setPhone(obj.getString("phone"));
			address.setPostalCode(obj.getString("postalCode"));
			address.setReceiveName(obj.getString("receiveName"));
			address.setUserId(obj.getInt("userId"));
			addresses.add(address);
		}
		return addresses;
	}

	public static Address parseDefaultAddress(JSONObject addObject)
			throws JSONException {

		Address address = new Address();
		address.setFull_address(addObject.getString("full_address"));
		address.setId(addObject.getInt("id"));
		address.setIs_default(addObject.getInt("is_default"));
		address.setMobile(addObject.getString("mobile"));
		address.setPhone(addObject.getString("phone"));
		address.setPostalCode(addObject.getString("full_address"));
		address.setReceiveName(addObject.getString("receiveName"));
		address.setUserId(addObject.getInt("userId"));
		return address;
	}

}
