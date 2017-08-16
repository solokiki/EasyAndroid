package com.lonn.core.utils;

public class VoidUtil {
	public static String getCallerMethodName(int index) {
		StackTraceElement[] temp = Thread.currentThread().getStackTrace();
		StackTraceElement a = (StackTraceElement) temp[index];
		return a.getMethodName();
	}
}
