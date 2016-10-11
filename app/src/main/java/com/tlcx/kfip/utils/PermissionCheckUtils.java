package com.tlcx.kfip.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 检测应用是否具有某权限
 * Created by victor on 2016/10/10 11:40.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class PermissionCheckUtils {
    private static final String PACKAGE_NAME="com.tlcx.kfip";

    //拍照权限
    public static boolean canUseCamera(Context context){
        PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.CAMERA", PACKAGE_NAME));
        return permission;
    }

}
