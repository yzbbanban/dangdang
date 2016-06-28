package com.wangban.dangdang.util;

import org.json.JSONException;
import org.json.JSONObject;

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


}
