package com.lonn.core.utils;

import android.content.Context;
import android.text.TextUtils;

public class DeviceUtil {

    private static final int DeviceUtil_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

    public static String getDeviceId(Context context){
        String deviceId = AppUtil.getIMEI(context);

        if(TextUtils.isEmpty(deviceId)){
            deviceId = MacIdUtil.getMac(context);
        }

        return deviceId;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

}
