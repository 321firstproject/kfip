package com.tlcx.kfip.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 常量类
 * Created by victor on 2016/9/22 17:51.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class Constants {
    //默认屏幕尺寸
    public static final int BASE_SCREEN_WIDTH = 720;
    public static final int BASE_SCREEN_HEIGHT = 1280;
    public static int screenWidth, screenHeight;
    /**
     * 调试模式，默认是调试模式
     */
    public static final boolean DEBUG = true;

    /**
     * 单页加载的数据条数
     */
    public static final int LIST_LOAD_COUNT = 16;

    static {
        //创建sd卡中项目目录
        Directorys.makeDirectoriesIngoreMedia();
    }
    /**
     * 初始化数据
     */
    public static void initValue(Context context) {
        initScreenSize(context);
    }

    /**
     * 初始化屏幕大小
     */
    private static void initScreenSize(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        if (screenWidth == 0) {
            screenWidth = BASE_SCREEN_WIDTH;
        }
        if (screenHeight == 0) {
            screenHeight = BASE_SCREEN_HEIGHT;
        }
    }
    /**
     * 是否已登录
     */
    public static boolean hasLogin(){
        return true;
    }

}
