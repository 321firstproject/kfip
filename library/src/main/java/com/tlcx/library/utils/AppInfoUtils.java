package com.tlcx.library.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 手机信息工具类
 * Created by victor on 2016/9/26 17:40.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class AppInfoUtils {

    /**
     * 获取版本名
     */
    public static String getVersonName(Context context){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo ;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
        return "1.0";
    }
    /**
     * 获取版本号
     */
    public static int getVersonCode(Context context){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo ;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
        return 1;
    }
}
