package com.wangban.dangdang.consts;



public class GlobalConsts {
	public static final String BASEURL="http://45.78.24.178:8080/dang/";
	public static final String URL_LOAD_RECOMMEND_BOOK_LIST = BASEURL+"main/getrecommend.action";
	public static final String URL_LOAD_NEW_BOOK_LIST = BASEURL+"main/getnew.action";
	public static final String URL_LOAD_HOT_BOOK_LIST = BASEURL+"main/gethot.action";
	public static final String URL_LOAD_CODE_IMAGE = BASEURL+"user/getImage.action";
	public static final String URL_USER_REGIST = BASEURL + "user/register.action";
	public static final String URL_USER_LOGIN = BASEURL + "/user/login.action";
	public static final String URL_USER_LOGINEITHOUTPWD = BASEURL + "/user/loginWithoutPwd.action";
	public static final String URL_USER_ADD_ADDRESS = BASEURL + "/order/saveAddress.action";
	public static final int RESPONSE_CODE_SUCCESS = 1001;
	public static final int RESULT_OK = 100;
}