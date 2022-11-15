package com.lonn.core.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.fragment.app.FragmentActivity;

import com.lonn.core.app.AppActivities;
import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class PermissionsUtil {

    private static final int PermissionsUtil_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

    // 所有需要授权的权限
    public static final String[] NEED_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA};

    // 必须授权的权限，这些权限如果有一个没有授权，则APP不可用
    public static final String[] MUST_HAVE_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final String MSG_ALL = "至少需要SD卡读写权限、定位权限才能正常使用哦";
    private static final String MSG_SDCARD = "必需获取存储空间才能正常使用哦";
    private static final String MSG_LOCATION = "需要访问您的位置，以帮助您获取周围可供应的商品信息。";
    private static final String MSG_CAMERA = "必需调用摄像头才能正常使用哦";
    private static final String MSG_CALL_PHONE = "必需同意电话权限才能正常使用哦";

    /**
     * 权限检查。检查通过不做任何操作，不通过显示默认对话框（去设置，或者关闭应用）
     *
     * @param activity      Activity
     * @param s_permissions 需要检查的权限
     */
    public static void checkPermission(FragmentActivity activity, String... s_permissions) {
        checkPermission(activity, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<Permission> deniedPermission) {
                if (deniedPermission != null && deniedPermission.size() > 0) {
                    PermissionsUtil.showPermissionsDialog(activity, deniedPermission.get(0), false, true);
                }
            }
        }, s_permissions);
    }

    /**
     * 权限检查。检查结果回调由调用者自行处理
     *
     * @param activity      Activity
     * @param mListener     检查结果回调
     * @param s_permissions 需要检查的权限
     */
    public static void checkPermission(FragmentActivity activity, PermissionListener mListener, String... s_permissions) {
        List<Permission> deniedPermissionList = new ArrayList<>();
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        permissions.requestEach(s_permissions)
                .subscribe(new Consumer<Permission>() {
                               @Override
                               public void accept(Permission permission) throws Exception {
                                   if (permission.granted) {
                                       // 同意

                                   } else {
                                       // 不同意
                                       deniedPermissionList.add(permission);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable t) {
                               }
                           },
                        new Action() {
                            @Override
                            public void run() {
                                analyzeLaunchPermission(mListener, deniedPermissionList);
                            }
                        });
    }

    private static void analyzeLaunchPermission(PermissionListener mListener, List<Permission> deniedPermission) {
        if (deniedPermission == null || deniedPermission.size() == 0) {
            mListener.onGranted();
        } else {
            mListener.onDenied(deniedPermission);
        }
    }

    public static void showPermissionsDialog(Activity activity, Permission permission, boolean settingFinishPage, boolean cancelFinishAPP) {
        if (activity == null || activity.isFinishing()) {
            return;
        }

        new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setMessage(getMsg(permission))
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户到设置中去进行设置
                        PermissionPageManagement.goToSetting(activity);

                        if (settingFinishPage) {
                            activity.finish();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cancelFinishAPP) {
                            AppActivities.finishAllActivities();
                        }
                    }
                })
                .create()
                .show();
    }

    private static String getMsg(Permission permission) {
        String msg = MSG_ALL;
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission.name)) {
            msg = MSG_SDCARD;
        } else if (Manifest.permission.CAMERA.equals(permission.name)) {
            msg = MSG_CAMERA;
        } else if (Manifest.permission.CALL_PHONE.equals(permission.name)) {
            msg = MSG_CALL_PHONE;
        }

        return msg;
    }

    public interface PermissionListener {
        void onGranted();

        void onDenied(List<Permission> deniedPermission);
    }

}
