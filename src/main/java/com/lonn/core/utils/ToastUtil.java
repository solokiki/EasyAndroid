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

    private ToastUtil(Bulider bulider){
        gravity = bulider.gravity;
        textSize = bulider.textSize;
    }

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

    public static class Bulider {
        private int gravity = Gravity.BOTTOM;
        private int textSize = 14;

        public Bulider(){

        }

        public Bulider gravity(int gravity){
            this.gravity = gravity;
            return this;
        }

        public Bulider textSize(int textSize){
            this.textSize = textSize;
            return this;
        }

        public ToastUtil bulid(){
            return new ToastUtil(this);
        }

    }

}
