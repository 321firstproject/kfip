package com.tlcx.library.utils;

import android.content.Context;

/**
 * 获取屏幕相关数据工具类
 * Created by victor on 2016/9/20 23:09.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class ScreenUtils {
    /**
     * 用于获取状态栏的高度。
     * @param context  使用Resource对象获取（推荐这种方式）
     * @return 返回状态栏高度的像素值
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 根据手机的分辨率，从dp单位转换为px（像素）
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale + 0.5);
    }

    /**
     * 根据手机的分辨率，从px单位转换为dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context,float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale + 0.5);
    }

    /**
     * 根据手机的分辨率，从px单位转换为px（像素）
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context,float sp){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}
