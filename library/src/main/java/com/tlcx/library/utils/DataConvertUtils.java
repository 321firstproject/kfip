package com.tlcx.library.utils;


import com.orhanobut.logger.Logger;

/**
 * 数据转换工具类
 * Created by victor on 2016/9/27 15:09.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class DataConvertUtils {

    /**
     * 字符串转整型
     */
    public static int String2Int(String str) {
        int type = 0;
        try {
            type = Integer.valueOf(str);
        } catch (Exception e) {
            Logger.e(e.toString());
        }
        return type;
    }

    /**
     * 字符串转长整型
     */
    public static long String2Long(String str) {
        long type = 0;
        try {
            type = Long.valueOf(str);
        } catch (Exception e) {
            Logger.e(e.toString());
        }
        return type;
    }

    /**
     * 字符串转双精度浮点型
     */
    public static double String2Double(String str) {
        double type = 0;
        try {
            type = Double.valueOf(str);
        } catch (Exception e) {
            Logger.e(e.toString());
        }
        return type;
    }
}
