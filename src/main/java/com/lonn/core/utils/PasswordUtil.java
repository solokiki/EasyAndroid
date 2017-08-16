package com.lonn.core.utils;

public class PasswordUtil {
	
	public static String encryptPwd(String pwd){
		String seed = getSeed(pwd);
		return MD5Util.getMD5String(seed + pwd) + ":" + seed;
	}
	
	public static String encryptPwd(String seed, String pwd){
		return MD5Util.getMD5String(seed + pwd) + ":" + seed;
	}
	
	public static String getSeed(String pwd){
		return MD5Util.getMD5String(pwd).substring(0, 2);
	}
}
