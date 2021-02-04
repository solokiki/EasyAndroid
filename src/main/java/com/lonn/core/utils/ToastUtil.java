package com.lonn.core.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast;

    private static int gravity = Gravity.BOTTOM;
    private static int textSize = 14;

    public static void shortMsg(Context context, String content){
        toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        setStyle(toast);
        toast.setText(content);
        toast.show();

    }

    public static void shortMsg(Context context, int resID){
        String content = context.getResources().getString(resID);
        toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        setStyle(toast);
        toast.setText(content);
        toast.show();

    }

    public static void longMsg(Context context, String content){
        toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        setStyle(toast);
        toast.setText(content);
        toast.show();

    }

    public static void longMsg(Context context, int resID){
        String content = context.getResources().getString(resID);
        toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        setStyle(toast);
        toast.setText(content);
        toast.show();

    }

    private static void setStyle(Toast toast){
        toast.setGravity(gravity, 0, 0);
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        messageTextView.setTextSize(textSize);
    }

}
