package com.tlcx.library.utils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

/**
 * 图片加载工具类
 * Created by victor on 2016/10/10 17:30.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class FrescoUtils {
    /*
    *  清理缓存
    */
    public static void clearCacheImage(){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
    }
}
