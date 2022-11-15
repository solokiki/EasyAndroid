package com.lonn.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {

	private static final int SharedPreferencesUtil_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

	// 文件名
	private static final String FILE_NAME = "share_data";
	
	
	public static void putString(Context context, String key, String value){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		Editor editor=sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static String getString(Context context, String key, String defaultValue){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		return sp.getString(key, defaultValue);
	}
	
	public static void putBoolean(Context context, String key, boolean value){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		Editor editor=sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public static boolean getBoolean(Context context, String key, boolean defaultValue){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		return sp.getBoolean(key, defaultValue);
	}
	
	public static void putInt(Context context, String key, int value){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		Editor editor=sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public static int getInt(Context context, String key, int defaultValue){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		return sp.getInt(key, defaultValue);
	}
	
	public static void putFloat(Context context, String key, float value){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		Editor editor=sp.edit();
		editor.putFloat(key, value);
		editor.commit();
	}
	
	public static float getFloat(Context context, String key, float defaultValue){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		return sp.getFloat(key, defaultValue);
	}
	
	public static void putLong(Context context, String key, long value){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		Editor editor=sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	public static long getLong(Context context, String key, long defaultValue){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		return sp.getLong(key, defaultValue);
	}
	
	/**
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void put(Context context, String key, Object value){
		if(value instanceof String){
			putString(context, key, (String)value);
		}else if(value instanceof Integer){
			putInt(context, key, (Integer)value);
		}else if(value instanceof Boolean){
			putBoolean(context, key, (Boolean)value);
		}else if(value instanceof Float){
			putFloat(context, key, (Float)value);
		}else if(value instanceof Long){
			putLong(context, key, (Long)value);
		}
	}
	
	/**
	 * 
	 * @param context
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Object get(Context context, String key, Object defaultValue){
		if(defaultValue instanceof String){
			return getString(context, key, (String)defaultValue);
		}else if(defaultValue instanceof Integer){
			return getInt(context, key, (Integer)defaultValue);
		}else if(defaultValue instanceof Boolean){
			return getBoolean(context, key, (Boolean)defaultValue);
		}else if(defaultValue instanceof Float){
			return getFloat(context, key, (Float)defaultValue);
		}else if(defaultValue instanceof Long){
			return getLong(context, key, (Long)defaultValue);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		Editor editor=sp.edit();
		editor.remove(key);
		editor.commit();
	}
	
	/**
	 * 
	 * @param context
	 */
    public static void clear(Context context){  
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);  
        Editor editor = sp.edit();
        editor.clear();  
        editor.commit();  
    }
	
    /**
     * 
     * @param context
     * @param key
     * @return
     */
	public static boolean contains(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
		return sp.contains(key);
	}
	
}
