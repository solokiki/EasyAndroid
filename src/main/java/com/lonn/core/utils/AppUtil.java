package com.lonn.core.utils;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.lonn.core.app.AppActivities;
import com.tbruyelle.rxpermissions3.Permission;

import java.util.List;

public class AppUtil {

    private static final int AppUtil_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

    /**
     * 检查手机网络连接
     *
     * @return
     */
    public static boolean checkNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            }
        }

        return false;


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
     * 转化html代码
     * @param source
     * @return
     */
    public static String fromHtml(String source) {
        if(TextUtils.isEmpty(source)){
            return "";
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(source,Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            return Html.fromHtml(source).toString();

        }
    }

    /**
     * 直接拨号
     * 已废弃，请使用callPhoneDial()替代
     */
    @Deprecated
    public static void dial(Context context, String number) {
        callPhoneDial(number, context);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhoneDial(String phoneNum, Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }


    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(FragmentActivity activity, String phoneNum) {

        PermissionsUtil.checkPermission(activity, new PermissionsUtil.PermissionListener() {
            @Override
            public void onGranted() {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + phoneNum);
                intent.setData(data);
                activity.startActivity(intent);
            }

            @Override
            public void onDenied(List<Permission> deniedPermission) {
                PermissionsUtil.showPermissionsDialog(activity, deniedPermission.get(0), false, false);
            }
        }, Manifest.permission.CALL_PHONE);

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

    /**
     * 获取Color
     * @param context
     * @param id
     * @return
     */
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static Drawable getDrawable(Context context, int id){
        return ContextCompat.getDrawable(context, id);
    }

    /**
     * 退出APP
     */
    public static void exitApp() {
        try {
            AppActivities.finishAllActivities();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyText(Context context, String text){
        //获取剪贴版
        ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        //第一个参数只是一个标记，随便传入。
        //第二个参数是要复制到剪贴版的内容
        ClipData clip = ClipData.newPlainText("simple text", text);
        //传入clipdata对象.
        clipboard.setPrimaryClip(clip);
    }

}
