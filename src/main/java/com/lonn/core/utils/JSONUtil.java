package com.lonn.core.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {

	public static String getStringFromJSON(String json, String key) {
		String value = null;
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			value = jso.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static int getIntFromJSON(String json, String key) {
		int value = -1;
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			String s_value = jso.getString(key);
			if(! StringUtil.isEmpty(s_value) && StringUtil.matches(StringUtil.REGEX_NUMERIC, s_value)){
				value = Integer.parseInt(s_value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static double getDoubleFromJSON(String json, String key) {
		double value = -1;
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			String s_value = jso.getString(key);
			if(! StringUtil.isEmpty(s_value) && StringUtil.matches(StringUtil.REGEX_NUMERIC, s_value)){
				value = Double.parseDouble(s_value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static boolean getBooleanFromJSON(String json, String key) {
		boolean value = false;
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			value = jso.getBoolean(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static boolean contain(String json, String key){
		JSONObject jso = null;
		try {
			jso = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(jso == null){
			return false;
		}else{
			return jso.has(key);
		}
	}
	
}
