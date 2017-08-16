package com.lonn.core.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	
	private static Toast toast;
	
    public static void shortMsg(Context context, String content){
    	if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
        	toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(content);
        }
        toast.show();
    }
    
    public static void shortMsg(Context context, int resID){
    	String content = context.getResources().getString(resID);
    	if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
        	toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(content);
        }
        toast.show();
    }
    
    public static void longMsg(Context context, String content){
    	if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        } else {
        	toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(content);
        }
        toast.show();
    }
    
    public static void longMsg(Context context, int resID){
    	String content = context.getResources().getString(resID);
    	if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        } else {
        	toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(content);
        }
        toast.show();
    }
    
}
