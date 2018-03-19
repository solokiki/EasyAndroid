package com.lonn.core.utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 判断字符串是否为空
	 * @param content
	 * @return
	 */
	public static boolean isEmpty(String content){
		if(content == null || content.length() == 0 || "null".equals(content)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 截取某个字符串前面的字符串
	 * @param content
	 * @param tab
	 * @return
	 */
	public static String frontString(String content, String tab){
		if(content.contains(tab)){
			return content.substring(0, content.indexOf(tab));
		}else{
			return content;
		}
	}
	
	/**
	 * 截取某个字符串后面的字符串
	 * @param content
	 * @param tab
	 * @return
	 */
	public static String rearString(String content, String tab){
		if(content.contains(tab)){
			return content.substring(content.lastIndexOf(tab) + tab.length(), content.length());
		}else{
			return "";
		}
		
	}
	
	/**
	 * 截取某两个字符串中间的字符串
	 * @param content
	 * @param startTab
	 * @param endTab
	 * @return
	 */
	public static String middleString(String content, String startTab, String endTab){
		return content.substring(content.indexOf(startTab) + 1, content.indexOf(endTab));
	}

	/**
	 * 从一段字符串中获取特定规则的字符串
	 * 
	 * @param content 原文内容
	 * @param regex 正则表达式
	 *            例："\\[(.*?\\|.*?)\\]"
	 * @param rexInGroup 是否表示包含规则中的字符串  
	 * @return Arraylist<String> 所有包含此规则的字符串 没有返回null;
	 */
	public static ArrayList<String> getStrInContent(String content, String regex, boolean rexInGroup) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		int hasRex = 1;
		if (rexInGroup)
			hasRex = 0;
		ArrayList<String> list = new ArrayList<String>();
		while (matcher.find()) {
			try {
				String temp = matcher.group(hasRex);
				list.add(temp);
			} catch (Exception e) {
				return null;
			}
		}
		if (list.size()==0)
			return null;
		return list;
	}
	
	/**
	 * @Description: 生成随机字符串
	 * @param length 需要的字符串长度
	 * @return String
	 * @author long chen
	 * @date 2016年6月28日 上午10:04:06
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	
	// 手机号正则，确保1打头，后面10位数字就行
	public static final String REGEX_PHONE = "^1\\d{10}$";
	// 数字正则，不分浮点或者整数
	public static final String REGEX_NUMERIC = "^[0-9]*(.[0-9]*)?$";
	/**
	 * 检测匹配
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean matches(String regex, String str){
		return Pattern.matches(regex, str);
	}
	
}
