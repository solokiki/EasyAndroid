package com.lonn.core.utils;

public class ClassUtil {
	
	/**
	 * 获取一个对象的类名
	 * @return String
	 */
	public static String getClassName(Object obj) {
		if (null != obj) {
			try {
				String className;
				if (obj instanceof Class<?>) {
					className = ((Class<?>) obj).getName();
				} else {
					className = obj.getClass().getName();
				}

				String[] classNames = className.split("\\.");
				return classNames[classNames.length - 1];
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "";
	}
	
}
