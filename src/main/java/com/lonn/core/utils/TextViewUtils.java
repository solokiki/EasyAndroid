package com.lonn.core.utils;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

public class TextViewUtils {

    private static final int TextViewUtils_V_CODE = 6;  // 标记当前类的版本，高版本兼容低版本

    private static final int MAX_COUNT = 99;
    private static final String MAX_STRING = MAX_COUNT + "+";

    /**
     * 红点展示，当数值超过MAX_COUNT时，展示MAX_STRING
     *
     * @param textView TextView
     * @param count    数值
     */
    public static void setRedPoint(TextView textView, int count) {
        if (textView == null) {
            return;
        }
        if (count > 0 && count <= MAX_COUNT) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(String.valueOf(count));
        } else if (count > MAX_COUNT) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(MAX_STRING);
        } else {
            textView.setVisibility(View.GONE);
            textView.setText("");
        }
    }

    /**
     * 显示文本，null值展示为空字符串
     *
     * @param textView TextView
     * @param str      可能含有null值的字符串
     */
    public static void setText(TextView textView, String str) {
        if (textView == null) {
            return;
        }

        if (isNotNull(str)) {
            textView.setText(str);
        } else {
            textView.setText("");
        }

    }

    /**
     * 显示拼接文本，null值展示为空字符串
     * @param textView TextView
     * @param str1 第一段文本
     * @param str2 第二段文本
     */
    public static void setText(TextView textView, String str1, String str2) {
        if (textView == null) {
            return;
        }

        String str = "";

        if (isNotNull(str1)) {
            str += str1;
        }

        if (isNotNull(str2)) {
            str += str2;
        }

        textView.setText(str);
    }

    /**
     * 小数点前字体大，小数点后字体小。如果包含“¥”符号，该符号和小数点后的字体一样缩小显示。
     *
     * @param textView TextView
     * @param price    价格
     */
    public static void setSpannablePrice(TextView textView, String price) {
        if (textView == null || TextUtils.isEmpty(price)) {
            return;
        }
        SpannableString spannableString = new SpannableString(price);

        if (price.contains("¥")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), price.indexOf("¥"), 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (price.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), price.indexOf("."), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannableString);
    }

    /**
     * 小数点前字体大，小数点后字体小。如果包含“¥”符号和单位unitName，该符号和单位unitName都和小数点后的字体一样缩小显示。
     *
     * @param textView TextView
     * @param price    价格
     */
    public static void setSpannablePrice(TextView textView, String price, String unitName) {
        if (textView == null || TextUtils.isEmpty(price)) {
            return;
        }

        if (!TextUtils.isEmpty(price)) price = price + "/" + unitName;
        SpannableString spannableString = new SpannableString(price);

        if (price.contains("¥")) {
            spannableString.setSpan(new RelativeSizeSpan(0.8f), price.indexOf("¥"), 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (price.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.8f), price.indexOf("."), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (price.contains("/")) {
            spannableString.setSpan(new RelativeSizeSpan(0.8f), price.indexOf("/"), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), price.indexOf("/"), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannableString);
    }

    /**
     * ¥符号缩小显示
     *
     * @param textView TextView
     * @param price    价格
     */
    public static void setPrice(TextView textView, String price) {
        if (textView == null || TextUtils.isEmpty(price)) {
            return;
        }
        SpannableString spannableString = new SpannableString(price);

        if (price.contains("¥")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), price.indexOf("¥"), 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spannableString);
    }


    /**
     * 划线价格显示
     *
     * @param textView TextView
     * @param price    价格
     */
    public static void setInvalidPrice(TextView textView, String price) {
        textView.setText(price);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //中间横线
//        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);  //下划线
//        textView.getPaint().setAntiAlias(true);  // 抗锯齿
    }

    /**
     * 划线价格显示
     *
     * @param textView TextView
     * @param price    价格
     */
    public static void setInvalidPrice(TextView textView, String price, String unitName) {
        textView.setText(price + "/" + unitName);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //中间横线
        textView.getPaint().setAntiAlias(true);  // 抗锯齿
//        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);  //下划线
    }

    /**
     * 手机号展示，显示前3位和后4位，中间显示星号
     *
     * @param textView TextView
     * @param phoneNum 手机号
     */
    public static void setPhoneNum(TextView textView, String phoneNum) {
        if (isNotNull(phoneNum) && phoneNum.length() == 11) {
            StringBuilder sb = new StringBuilder();
            sb.append(phoneNum.substring(0,3));
            sb.append("****");
            sb.append(phoneNum.substring(7));
            setText(textView, sb.toString());
        } else {
            setText(textView, phoneNum);
        }
    }

    /**
     * 判断字符串是否为空或者null
     * @param str 要判断的字符串
     * @return 是否为空或者null
     */
    private static boolean isNotNull(String str) {
        return str != null && str.length() != 0 && !str.equals("null") && !str.equals("NULL");
    }

}
