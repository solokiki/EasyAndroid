package com.lonn.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

public class AppUtil {

    /**
     * 检查手机网络连接
     *
     * @return
     */
    public static boolean checkNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;
        }

        // //mobile 3G Data Network
        // State mobile =
        // cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        // //wifi
        // State wifi =
        // cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

    }

    /**
     * 返回GPS开启状态
     *
     * @param context
     * @return
     */
    public static boolean checkGPS(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean GPS_status = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return GPS_status;
    }

    /**
     * 获取APP名称
     * @param context
     * @return
     */
    public static String getAPPName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用版本号
     *
     * @return
     */
    public static int getAPPVersionCode(Context context) {
        int myVersion = 0;
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            myVersion = packInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        LogUtil.syso(AppUtil.class, "当前安装的版本号：" + myVersion);
        return myVersion;
    }

    /**
     * 获取应用版本名称
     *
     * @return
     */
    public static String getAPPVersionName(Context context) {
        String versionName = "";
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        LogUtil.syso(AppUtil.class, "当前安装的版本名：" + versionName);
        return versionName;
    }

    /**
     * 获取SDK版本号
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String sDeviceId = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            sDeviceId = tm.getDeviceId();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return sDeviceId;
    }

    /**
     * 获取手机型号
     *
     * @return String
     * @Description: TODO
     * @author lonn chen
     * @date 2017年2月22日 上午10:55:13
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机版本号
     *
     * @return String
     * @Description: TODO
     * @author lonn chen
     * @date 2017年2月22日 上午10:55:37
     */
    public static String getRelease() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取字符串资源
     *
     * @param resourceID 字符串资源的ID
     * @return String 字符串
     * @author lonn
     * @date 2015-06-03
     */
    public static String getString(Context context, int resourceID) {
        try {
            return context.getString(resourceID);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 拨号
     *
     * @param context
     * @param number
     */
    public static void dial(Context context, String number) {
        Class<TelephonyManager> c = TelephonyManager.class;
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[]) null);
            getITelephonyMethod.setAccessible(true);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Object iTelephony;
            iTelephony = (Object) getITelephonyMethod.invoke(tManager, (Object[]) null);
            Method dial = iTelephony.getClass().getDeclaredMethod("dial", String.class);
            dial.invoke(iTelephony, number);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return boolean
     * @Description: 是否有SD卡
     * @author long chen
     * @date 2016年7月27日 下午4:29:43
     */
    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 下载服务是否开启
     *
     * @param context
     * @return boolean
     * @Description: TODO
     * @author lonn chen
     * @date 2017年2月22日 上午10:54:22
     */
    public static boolean isDownloadServiceOpened(Context context) {
        try {
            int state = context.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");

            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

}
