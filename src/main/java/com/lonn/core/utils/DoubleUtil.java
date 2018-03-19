package com.lonn.core.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class DoubleUtil {

    private static final int DEFAULT_SCALE = 2;

    /**
     * 四舍五入
     * @param value 原始值
     * @param digits 小数点后几位
     * @return 四舍五入后的值
     */
    public static String formatToString(double value, int digits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(digits);
        return nf.format(value);
    }

    /**
     * 截取小数点后指定位数
     * @Description: TODO
     * @param value
     * @param digits
     * @return double
     * @author lonn chen
     * @date 2018年1月30日 上午11:16:08
     */
    public static double formatToDouble(double value, int digits){
        BigDecimal b1 = new BigDecimal(Double.toString(value));
        return b1.setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        return add(v1, v2, -1);
    }

    public static double add(double v1,double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        if(scale < 0){
            scale = DEFAULT_SCALE;
        }

        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        return sub(v1, v2, -1);
    }

    public static double sub(double v1,double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        if(scale < 0){
            scale = DEFAULT_SCALE;
        }

        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        return mul(v1, v2, -1);
    }

    public static double mul(double v1,double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        if(scale < 0){
            scale = DEFAULT_SCALE;
        }

        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2){
        return div(v1, v2, -1);
    }

    public static double div(double v1,double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        if(scale < 0){
            scale = DEFAULT_SCALE;
        }

        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 求余
     * @Description: TODO
     * @param v1
     * @param v2
     * @return double
     * @author lonn chen
     * @date 2018年1月30日 下午1:16:50
     */
    public static double rem(double v1, double v2){
        return rem(v1, v2, -1);
    }

    public static double rem(double v1,double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        if(scale < 0){
            scale = DEFAULT_SCALE;
        }

        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}

