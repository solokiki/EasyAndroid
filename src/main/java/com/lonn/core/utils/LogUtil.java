package com.lonn.core.utils;

import android.util.Log;

public class LogUtil {
	
	private static boolean isOpenLog;
	private static boolean isInit;
	
	public static void init(boolean openLog){
		isOpenLog = openLog;
		isInit = true;
	}
	
	public static void e(Object obj, String message) {
		e(ClassUtil.getClassName(obj), message);
	}
	
	public static void e(String tag, String message) {
		if (checkInit() && isOpenLog) {
			Log.e(tag, message);
		}
	}
	
	public static void w(Object obj, String message) {
		w(ClassUtil.getClassName(obj), message);
	}
	
	public static void w(String tag, String message) {
		if (checkInit() && isOpenLog) {
			Log.w(tag, message);
		}
	}
	
	public static void syso(Object obj, String message) {
		syso(ClassUtil.getClassName(obj), message);
	}
	
	public static void syso(String tag, String message) {
		if (checkInit() && isOpenLog) {
			System.out.println(tag+"---------->" + message);
		}
	}
	
	private static boolean checkInit(){
		if(!isInit){
			Log.e(ClassUtil.getClassName(LogUtil.class), "LogUtil is not init, you shoud init at first.");
			return false;
		}
		return true;
	}
	
}
