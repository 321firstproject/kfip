package com.tlcx.kfip.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * 获取应用信息
 * Created by victor on 2016/10/10 15:50.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class AppInfoUtils {
    /**
     * 判断 新版本 是否可用
     * @param context     上下文对象
     * @param versionCode 新的版本号
     * @return
     */
    public static boolean isNewVersionAvailable(Context context, long versionCode) {
        long code = getVersionCode(context);
        return (versionCode > code);
    }

    public static String getVsersionName(Context context) {
        try {
            PackageInfo pi = getPackageInfo(context);
            return pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 返回当前包名
     *
     * @return
     */
    public static String getCurrentPkgName(Context context) {
        try {
            PackageInfo pi = getPackageInfo(context);
            return pi.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long getVersionCode(Context context) {
        try {
            return getPackageInfo(context).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static PackageInfo getPackageInfo(Context context) throws Exception {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
        return pi;
    }

    /**
     * 获得manifase中得metadata数据
     * @param context
     * @param key 主键
     * @return 内容
     * @throws PackageManager.NameNotFoundException
     */
    public static String getMetaDate(Context context, String key) throws PackageManager.NameNotFoundException {
        ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
        return appInfo.metaData.getString(key).trim();

    }

    /**
     * 获得mac地址
     */
    public static String getMacAddress(Context context) {
        try {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            return wm.getConnectionInfo().getMacAddress();
        } catch (Exception e) {}
        return null;
    }

    /**
     * 获得IMEI
     */
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            return TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
        } catch (Exception e) {}
        return null;
    }
}
