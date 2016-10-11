package com.tlcx.kfip.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by victor on 2016/10/10 11:10.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class Directorys {
    public static final String APP_NAME = "kfip";
    //SD卡根目录
    public static String SDCARD = Environment.getExternalStorageDirectory().toString();

    //程序根目录
    public static final String ROOT = SDCARD + File.separator + APP_NAME + File.separator;
    //临时位置
    public static final String TEMP = ROOT + "temp" + File.separator;

    //log位置
    public static final String LOG = ROOT + "log" + File.separator;

    //缓存位置
    public static final String CACHE = ROOT + "cache" + File.separator;

    //下载
    public static final String DOWNLOAD = ROOT + "download" + File.separator;

    //拍照的图片的临时路径
    public static final String TAKE_PHOTO_TEMP = Directorys.TEMP + "temp.jpg";
    //对图片剪裁过后的临时路径
    public static final String AFTER_CROP_TEMP = Directorys.TEMP + "crop.jpg";

    /**
     * 创建SD卡目录
     */
    public static void makeDirectoriesIngoreMedia() {
        makeDirectoryIngoreMedia(Directorys.TEMP);
        makeDirectoryIngoreMedia(Directorys.LOG);
        makeDirectoryIngoreMedia(Directorys.CACHE);
        makeDirectoryIngoreMedia(Directorys.DOWNLOAD);
    }

    /**
     * 创建文件夹
     */
    public static void makeDirectoryIngoreMedia(String dirName) {
        try {
            new File(dirName).mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
